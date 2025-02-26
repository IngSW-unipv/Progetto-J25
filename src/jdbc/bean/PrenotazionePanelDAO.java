package jdbc.bean;

import jdbc.ConnessioneDB;
import modello.Panelista;
import modello.Utente;
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
    public boolean salvaPrenotazione(Slot slot, Panelista panelista ) {
    	
        Connection conn = ConnessioneDB.startConnection(null, "osmotech");
        PreparedStatement ps1;
        boolean queryRiuscita = true;

        try{
        	String query = "INSERT INTO osmotech.PRENOTAZIONE_PANEL VALUES (?, ?, ?)";
        	ps1 = conn.prepareStatement(query);
            ps1.setInt(1, panelista.getId());
            ps1.setInt(2, slot.getIdSlot());
            ps1.setString(3, panelista.getEmail());

           ps1.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace(); // Puoi sostituire con un logger
            queryRiuscita = false;
        }
        
        conn = ConnessioneDB.closeConnection(conn);
        
        return queryRiuscita;
    }
}
