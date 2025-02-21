package jdbc.bean;

import jdbc.ConnessioneDB;
import modello.creazionePanel.Macchinario;

import java.sql.*;
import java.util.ArrayList;

/* Script della tabella che sto usando
   CREATE TABLE MACCHINARIO(
    ID_MACCHINARIO INT AUTO_INCREMENT PRIMARY KEY,
    N_POSTI INT NOT NULL
); */


public class MacchinarioDAO implements IMacchinarioDAO {
    private Connection conn;

    public MacchinarioDAO() {
        super();
    }

    public ArrayList<Macchinario> getMacchinari() {
        // Inizializzo la connessione
        conn = ConnessioneDB.startConnection(conn, "osmotech");

        // Creazione di una lista che conterrà tutti i macchinari
        ArrayList<Macchinario> macchinari = new ArrayList<>();
        String query = "SELECT * FROM MACCHINARIO";

        // Dichiarazione delle risorse
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Creazione del Statement e dell'oggetto ResultSet
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            // Scorro i risultati della query
            while (rs.next()) {
                // Crea un nuovo oggetto Macchinario con i dati della riga corrente
                Macchinario macchinario = new Macchinario(rs.getInt("ID_MACCHINARIO"), rs.getInt("N_POSTI"));

                // Aggiungo l'oggetto alla lista
                macchinari.add(macchinario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Chiudo le risorse
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                ConnessioneDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Restituisco la lista di macchinari
        return macchinari;
    }

    public boolean addMacchinario(Macchinario macchinario) {
        conn = ConnessioneDB.startConnection(conn, "osmotech");
        PreparedStatement pstmt = null;
        String query = "INSERT INTO MACCHINARIO (N_POSTI) VALUES (?)";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, macchinario.getNumPanelisti());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Ritorna true se almeno una riga è stata inserita
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Ritorna false in caso di errore
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                ConnessioneDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
