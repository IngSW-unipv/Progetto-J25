package jdbc.bean;

import jdbc.ConnessioneDB;
import modello.Panelista;
import modello.creazionePanel.Slot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Map;

/*SCRIPT TABELLA
CREATE TABLE PRENOTAZIONE_PANEL (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ID_SLOT INT,
    EMAIL_UTENTE INT NOT NULL,
    FOREIGN KEY (ID_SLOT) REFERENCES SLOT(ID_SLOT),
    FOREIGN KEY (EMAIL_UTENTE) REFERENCES UTENTE(EMAIL)
);

 */


public class PrenotazionePanelDAO implements IPrenotazionePanelDAO{

    public boolean getPrenotazioni(Map<LocalTime, Slot> slots) {
        Connection conn = ConnessioneDB.startConnection(null, "osmotech"); // Crea la connessione al database
        String query = "SELECT EMAIL_UTENTE FROM PRENOTAZIONE_PANEL WHERE ID_SLOT = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (Slot slot : slots.values()) {
                pstmt.setInt(1, slot.getIdSlot());

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String email = rs.getString("EMAIL_UTENTE");
                        slot.aggiungiPrenotato(new Panelista(0, email, null, null, null, null, null,
                                null, null, null , null, 0));
                    }
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnessioneDB.closeConnection(conn); // Chiude la connessione
        }
    }

    @Override
    public boolean salvaPrenotazione(Slot slot, Panelista panelista) {
        Connection conn = ConnessioneDB.startConnection(null, "osmotech");
        String sql = "INSERT INTO PRENOTAZIONE_PANEL (ID_SLOT, EMAIL_UTENTE) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, slot.getIdSlot());
            stmt.setString(2, panelista.getEmail());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Puoi sostituire con un logger
            return false;
        }
    }
}
