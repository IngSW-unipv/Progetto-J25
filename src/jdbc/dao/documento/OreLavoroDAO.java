package jdbc.dao.documento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import jdbc.ConnessioneDB;
import modello.Panelista;
import modello.creazionePanel.Slot;

public class OreLavoroDAO implements IOreLavoroDAO {

	Connection conn;
	
	public OreLavoroDAO() {
	}
	@Override
	public double selectOreLavoro(int id) {
		
		conn = ConnessioneDB.startConnection(conn, "osmotech");
		PreparedStatement ps1;
		ResultSet rs1;
		
		double oreLavoro = 0;
		
		try {
			
			String query = "SELECT ORE FROM osmotech.ORE_LAVORATE WHERE UTENTE_ID = ?";
					
			ps1 = conn.prepareStatement(query);
			ps1.setInt(1, id);
			
			rs1 = ps1.executeQuery();
			
			while(rs1.next()) {
				
				oreLavoro = rs1.getDouble("ORE");
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		conn = ConnessioneDB.closeConnection(conn);
		
		
		return oreLavoro;
	}

	public boolean getOreLavPrenotati(Map<LocalTime, Slot> slots) {
		Connection conn = ConnessioneDB.startConnection(null, "osmotech"); // Crea la connessione al database

		// Prendo la data da un qualsiasi slot
		LocalDate dataSlot = slots.values().iterator().next().getData();
		String mese = dataSlot.format(DateTimeFormatter.ofPattern("yyyy-MM"));

		String query = "SELECT ORE FROM ORE_LAVORATE WHERE USER_ID = ? AND MESE = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			for (Slot slot : slots.values()) {
				List<Panelista> prenotati = slot.getPrenotati(); // Ottiene i prenotati nello slot

				for (Panelista panelista : prenotati) {
					pstmt.setInt(1, panelista.getId()); // Imposta USER_ID nel PreparedStatement
					pstmt.setString(2, mese); // Imposta il mese corretto

					try (ResultSet rs = pstmt.executeQuery()) {
						if (rs.next()) {
							double oreLavoro = rs.getDouble("ORE");
							panelista.setOreLavoro(oreLavoro); // Imposta le ore nel panelista
						}
					}
				}
			}
			return true;
		} catch (SQLException | NoSuchElementException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnessioneDB.closeConnection(conn); // Chiude la connessione
		}
	}




}
