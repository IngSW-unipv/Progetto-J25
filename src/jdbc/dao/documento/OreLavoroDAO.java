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
	public double selectOreLavoro(int id, String mese) {
		
		conn = ConnessioneDB.startConnection(conn, "osmotech");
		PreparedStatement ps1;
		ResultSet rs1;
		
		double oreLavoro = 0;
		
		try {
			
			String query = "SELECT ORE FROM osmotech.ORE_LAVORATE WHERE USER_ID = ? and MESE = ?";
					
			ps1 = conn.prepareStatement(query);
			ps1.setInt(1, id);
			ps1.setString(2, mese);
			
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


	// Aggiunta metodo per aggiornare ore lavorate dai panelisti (Andres)
	public boolean aggiornaOreLavoro(int userId, String mese, double nuoveOre) {

		String query = "UPDATE osmotech.ORE_LAVORATE SET ORE = ? WHERE USER_ID = ? AND MESE = ?";

		try (Connection conn = ConnessioneDB.startConnection(null, "osmotech");
			 PreparedStatement ps = conn.prepareStatement(query)) {


				ps.setInt(1,userId);
				ps.setString(2, mese);
				ps.setDouble(3, nuoveOre);
				ps.executeUpdate();

			 } catch (SQLException e) {
				 e.printStackTrace();
				 return false;
			 }

		return true;

	}

	// Metodo che aggiunge un record se non esiste già (Andres)
	public boolean aggiungeOreLavoroSeNonEsiste(int userId, String mese, double oreIniziali) {
		Connection conn = ConnessioneDB.startConnection(null, "osmotech");
		PreparedStatement ps = null;

		try {

			// Controllo se esiste già un record per l'user e quel mese
			String checkQuery = "SELECT COUNT (*) FROM ORE_LAVORATE WHERE USER_ID = ? AND MESE = ?";
			ps = conn.prepareStatement(checkQuery);
			ps.setInt(1, userId);
			ps.setString(2, mese);

			ResultSet rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			rs.close();
			ps.close();

			if (count == 0){

				// Se non esiste, inserisci un nuovo record
				String insertQuery = "INSERT INTO ORE_LAVORATE (USER_ID, MESE, ORE) VALUES (?, ?, ?)";
				ps = conn.prepareStatement(insertQuery);
				ps.setInt(1, userId);
				ps.setString(2, mese);
				ps.setDouble(3, oreIniziali);
				ps.executeUpdate();
			}

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (ps != null) ps.close();
				ConnessioneDB.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
