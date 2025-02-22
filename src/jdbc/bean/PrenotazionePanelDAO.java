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
CREATE TABLE Prenotazione (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ID_SLOT INT,
    ID_UTENTE INT NOT NULL,
    FOREIGN KEY (ID_SLOT) REFERENCES SLOT(ID_SLOT),
    FOREIGN KEY (ID_UTENTE) REFERENCES USER(ID)
);

 */


public class PrenotazionePanelDAO implements IPrenotazionePanelDAO{

    public boolean getPrenotazioni(Map<LocalTime, Slot> slots) {
        Connection conn = ConnessioneDB.startConnection(null, "osmotech"); // Crea la connessione al database
        String query = "SELECT ID_UTENTE FROM PRENOTAZIONE_PANEL WHERE ID_SLOT = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (Slot slot : slots.values()) {
                pstmt.setInt(1, slot.getIdSlot());

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int idUtente = rs.getInt("ID_UTENTE");
                        slot.aggiungiPrenotato(new Panelista(idUtente, null, null, null, null, null, null,
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
        return false;
    }
}
