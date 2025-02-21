package jdbc.dao;

import java.sql.*;

import java.util.ArrayList;
import modello.prenotazioneInsaccatore.*;
import jdbc.ConnessioneDB;
import java.sql.Connection;
import java.time.LocalTime;


public class TurnoDAO implements ITurnoDAO{
	private static final String DB = "osmotech";
	private Connection connessione;
	
	
	//METODI CRUD:
	
	
	//METODO AGGIUNGI QUERY DA OGGETTO:
	public void aggiungi(Turno t) {
		
		//mi connetto:
		connessione = ConnessioneDB.startConnection(connessione, DB);
		//preparo la query:
	    String sql = "INSERT INTO TURNO (ORA_INIZIO, DURATA, STATO) VALUES (?, ?, ?)";

	    try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
	        // Impostiamo i valori nel PreparedStatement
	        stmt.setTime(1, java.sql.Time.valueOf(t.getOrainizio()));  // Converte LocalTime in Time per il DB
	        stmt.setInt(2, t.getDurata());  // Durata del turno in minuti
	        stmt.setBoolean(3, t.isStato());  // Stato del turno (true o false)

	        // Eseguiamo la query
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        throw new RuntimeException("Errore nell'aggiungere il turno.", e);
	    }
	    //chiudo la connessione:
		 ConnessioneDB.closeConnection(connessione);
	    
	}	
	
	
	//METODO OTTIENI OGGETTO DA QUERY
	public Turno trova(int idturno) {
		
		//mi connetto:
		connessione = ConnessioneDB.startConnection(connessione, DB);
		//preparo la query:
	    String sql = "SELECT * FROM TURNO WHERE idTURNO = ?";

	    try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
	        // Impostiamo l'id del turno come parametro nella query
	        stmt.setInt(1, idturno);

	        // Eseguiamo la query
	        ResultSet rs = stmt.executeQuery();

	        // Verifichiamo se abbiamo trovato una riga corrispondente
	        if (rs.next()) {
	            // Creiamo e ritorniamo un oggetto Turno con i dati recuperati dal database
	            Turno turno = new Turno(rs.getInt("DURATA"), rs.getTime("ORA INIZIO").toLocalTime());
	            turno.setId(rs.getInt("idTURNO"));
	            turno.setStato(rs.getBoolean("STATO"));

	            return turno;
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Errore nel recuperare il turno con id " + idturno, e);
	    }

	    //chiudo la connessione:
		 ConnessioneDB.closeConnection(connessione);

	    // Se non è stato trovato alcun turno con l'id specificato, restituiamo null
	    return null;
	    
	}

	//METODO OTTIENI UNA LISTA DI TURNI DA UNA QUERY
	public ArrayList<Turno> trovaTutti(){
		//creo la connessione:
		connessione = ConnessioneDB.startConnection(connessione, "osmotech");

		 ArrayList<Turno> turni = new ArrayList<>();
		 //prepaor la query:
		 String sql = "SELECT idTURNO, ORA_INIZIO, DURATA, STATO FROM TURNO";

		    try (PreparedStatement stmt = connessione.prepareStatement(sql);
		         ResultSet rs = stmt.executeQuery()) {

		        while (rs.next()) {
		            int idTurno = rs.getInt("idTURNO");
		            LocalTime orainizio = rs.getTime("ORA_INIZIO") != null ? rs.getTime("ORA_INIZIO").toLocalTime() : null;
		            int durata = rs.getInt("DURATA");
		            boolean stato = rs.getBoolean("STATO");

		            // Creiamo un oggetto Turno con i dati recuperati
		            Turno turno = new Turno(durata, orainizio);
		            turno.setId(idTurno);
		            turno.setStato(stato);

		            // Aggiungiamo il turno alla lista
		            turni.add(turno);
		        }
		    } catch (SQLException e) {
		        throw new RuntimeException("Errore nel recuperare tutti i turni.", e);
		    }
		    
		    //chiudo la connessione:
			 ConnessioneDB.closeConnection(connessione);
		    return turni;
		
	}
	
	//METODO CHE MI AGGIORNA UN TURNO PASSATO COME PARAMETRO
	public void aggiorna(Turno t) {
		
		//mi connetto:
		connessione = ConnessioneDB.startConnection(connessione, DB);
		//preparo la query:
	    String sql = "UPDATE TURNO SET `ORA INIZIO` = ?, DURATA = ?, STATO = ? WHERE idTURNO = ?";

	    try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
	        // Impostiamo i valori per i parametri nella query
	        stmt.setTime(1, java.sql.Time.valueOf(t.getOrainizio()));  // Impostiamo l'orario di inizio
	        stmt.setInt(2, t.getDurata());  // Impostiamo la durata
	        stmt.setBoolean(3, t.isStato());  // Impostiamo lo stato del turno
	        stmt.setInt(4, t.getId());  // Impostiamo l'ID del turno per specificare quale record aggiornare

	        // Eseguiamo l'aggiornamento
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        throw new RuntimeException("Erroso nell'aggiornamento del turno con id " + t.getId(), e);
	    }
	    //chiudo la connessione:
		 ConnessioneDB.closeConnection(connessione);
	}
	
	public void rimuovi(int id) {
		//mi connetto:
		connessione = ConnessioneDB.startConnection(connessione, DB);
		//preparo la query:
		String sql = "DELETE FROM TURNO WHERE idTURNO=?";
		try (PreparedStatement stmt = connessione.prepareStatement(sql)){
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Errore nell'eliminare il turno con id " + id,e);
		}
	    //chiudo la connessione:
		 ConnessioneDB.closeConnection(connessione);
	}
	

	
	

}
