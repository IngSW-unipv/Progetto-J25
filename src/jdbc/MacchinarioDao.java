package jdbc;

import modello.creazionePanel.Macchinario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MacchinarioDao implements IMacchinarioDAO{
    Connection conn;

    public MacchinarioDao() {
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
        return false; //da sistemare<3

    }
}
