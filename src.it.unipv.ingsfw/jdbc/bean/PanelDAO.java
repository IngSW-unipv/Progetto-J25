package jdbc.bean;

import jdbc.ConnessioneDB;
import modello.Panelista;
import modello.creazionePanel.Macchinario;
import modello.creazionePanel.Panel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/*SCRIPT DELLA TABELLA CHE IDELAMENTE STO USANDO
CREATE TABLE PANEL(
    ID_PANEL INT AUTO_INCREMENT,
    DATA DATE,
    ORARIO_INIZIO TIME,
    ORARIO_FINE TIME,
    MACCHINARIO INT,
    USER1 VARCHAR(255),
    USER2 VARCHAR(255),
    USER3 VARCHAR(255),
    USER4 VARCHAR(255),
    USER5 VARCHAR(255),
    USER6 VARCHAR(255),
    STATO BOOLEAN,  -- Colonna STATO aggiunta
    NUMERO_USER INT DEFAULT 0,  -- Nuova colonna NUMERO_USER aggiunta
    PRIMARY KEY (ID_PANEL),
    FOREIGN KEY (MACCHINARIO) REFERENCES MACCHINARIO(ID_MACCHINARIO)
);
 */

public class PanelDAO implements IPanelDAO {
    private Connection conn;

    public ArrayList<Panel> getPanels(){
       conn = ConnessioneDB.startConnection(conn, "osmotech");
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Panel> panels = new ArrayList<>();
        String query = "SELECT * FROM PANEL WHERE STATO = TRUE AND ORARIO_FINE IS NULL";

        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int idPanel = rs.getInt("ID_PANEL");
                LocalDate data = rs.getDate("DATA").toLocalDate();
                LocalTime orarioInizio = rs.getTime("ORARIO_INIZIO").toLocalTime();
                int macchinario = rs.getInt("MACCHINARIO");

                // Recupera le email degli utenti (alcune possono essere NULL)
                List<Panelista> users = new ArrayList<>();
                for (int i = 1; i <= 6; i++) {
                    String email = rs.getString("USER" + i);
                    if (email != null) users.add(new Panelista(email, null, null, null, null, null,
                            null, Double.NaN));
                }

                // Il panel è attivo perché lo stiamo filtrando nella query
                Panel panel = new Panel(orarioInizio, new Macchinario(macchinario, users.size()), data);
                panel.setListaPanelisti(users);
                panels.add(panel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                ConnessioneDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return panels;
    }

    public boolean addPanel(Panel panel) {
        conn = ConnessioneDB.startConnection(conn, "osmotech");
        PreparedStatement pstmt = null;
        String query = "INSERT INTO PANEL (DATA, ORARIO_INIZIO, ORARIO_FINE, MACCHINARIO, USER1, USER2, USER3, USER4, USER5, USER6, STATO, NUMERO_USER) " +
                "VALUES (?, ?, NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, java.sql.Date.valueOf(panel.getData()));
            pstmt.setTime(2, java.sql.Time.valueOf(panel.getOrarioInizio()));
            pstmt.setInt(3, panel.getMacchinario().getId());

            // Inserisce gli utenti (se ci sono)
            List<Panelista> users = panel.getListaPanelisti();
            for (int i = 0; i < 6; i++) {
                if (i < users.size()) {
                    pstmt.setString(4 + i, users.get(i).getEmail());
                } else {
                    pstmt.setNull(4 + i, java.sql.Types.VARCHAR);
                }
            }

            pstmt.setBoolean(10, panel.getStato()); // Inserisce lo stato attivo
            // Inserisce il numero di utenti (NUMERO_USER)
            pstmt.setInt(11, users.size()); // Aggiungi il numero di utenti associati al panel

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                ConnessioneDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean chiudiPanel(int panelId, LocalTime orarioFine){
        conn = ConnessioneDB.startConnection(conn, "osmotech");
        PreparedStatement pstmt = null;
        String query = "UPDATE PANEL SET ORARIO_FINE = ?, STATO = FALSE WHERE ID_PANEL = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setTime(1, java.sql.Time.valueOf(orarioFine)); // Imposta l'orario di fine
            pstmt.setInt(2, panelId); // Identifica il panel da aggiornare

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                ConnessioneDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public boolean rimuoviUtenteDaPanel(int panelId, String emailUtente) {
        conn = ConnessioneDB.startConnection(conn, "osmotech");
        // Query per ottenere i dati del panel specifico
        String query = "SELECT USER1, USER2, USER3, USER4, USER5, USER6 FROM PANEL WHERE ID_PANEL = ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, panelId);
            rs = pstmt.executeQuery();

            // Se il panel esiste
            if (rs.next()) {
                // Verifica in quale colonna si trova l'email dell'utente
                for (int i = 1; i <= 6; i++) {
                    String userColumn = "USER" + i;  // USER1, USER2, ..., USER6
                    String emailInDb = rs.getString(userColumn);

                    if (emailUtente.equals(emailInDb)) {
                        // Se l'email corrisponde a quella da rimuovere, aggiorna la colonna a NULL
                        String updateQuery = "UPDATE PANEL SET " + userColumn + " = NULL WHERE ID_PANEL = ?";
                        try (PreparedStatement updatePstmt = conn.prepareStatement(updateQuery)) {
                            updatePstmt.setInt(1, panelId);
                            updatePstmt.executeUpdate();
                            return true;  // L'utente è stato rimosso, ritorna true
                        } catch (SQLException e) {
                            e.printStackTrace();
                            return false;  // Se c'è un errore durante l'update, ritorna false
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Se c'è un errore durante la query SELECT, ritorna false
        } finally {
            // Chiusura delle risorse (connessione, preparedStatement, resultSet)
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                ConnessioneDB.closeConnection(conn);  // Chiusura della connessione
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Se non è stato trovato l'utente o il panel non esiste, ritorna false
        return false;
    }

    public boolean aggiungiUtenteAlPanel(int panelId, String emailUtente) {
        conn = ConnessioneDB.startConnection(conn, "osmotech");

        // Query per ottenere il numero massimo di utenti e le colonne USER1, USER2, ..., USER6
        String query = "SELECT NUMERO_USER, USER1, USER2, USER3, USER4, USER5, USER6 FROM PANEL WHERE ID_PANEL = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, panelId);
            ResultSet rs = pstmt.executeQuery();

            // Se il panel esiste
            if (rs.next()) {
                int numeroUser = rs.getInt("NUMERO_USER"); // Numero massimo di utenti previsti

                // Scandisci solo fino a NUMERO_USER, ignorando USER5 e USER6 se il panel ha solo 4 utenti
                for (int i = 1; i <= numeroUser; i++) {
                    String userColumn = "USER" + i;
                    String emailInDb = rs.getString(userColumn);

                    // Se la colonna è NULL, assegna l'email dell'utente
                    if (emailInDb == null) {
                        String updateQuery = "UPDATE PANEL SET " + userColumn + " = ? WHERE ID_PANEL = ?";
                        try (PreparedStatement updatePstmt = conn.prepareStatement(updateQuery)) {
                            updatePstmt.setString(1, emailUtente);
                            updatePstmt.setInt(2, panelId);
                            updatePstmt.executeUpdate();
                            return true;  // Utente aggiunto con successo
                        }
                    }
                }
                return false;  // Nessun posto disponibile nel panel
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Chiudi la connessione al database
            ConnessioneDB.closeConnection(conn);
        }
        return false;  // Panel non trovato
    }

    /*public boolean removePanel(Panel panel){
     }*/

   /* public boolean updatePanel(Panel panel){
    }*/
}
