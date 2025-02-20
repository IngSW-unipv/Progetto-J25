package jdbc.dao;

import java.sql.*;

import java.util.ArrayList;
import modello.prenotazioneInsaccatore.*;
import jdbc.ConnessioneDB;
import java.sql.Connection;
import jdbc.dao.*;
import java.time.LocalTime;


public class TurnoDAO implements ITurnoDAO{
	
	private Connection connessione;
	
	
	//METODI CRUD:

	public void add(Turno t) {
		  String sql = "INSERT INTO TURNO (idTURNO, INSACCATORE) VALUES (?, ?)";

		    try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
		        stmt.setInt(1, t.getId());   // Imposta l'ID del turno
		        stmt.setInt(2, t.getIns().getId()); // Imposta il valore dell'insaccatore

		        int affectedRows = stmt.executeUpdate();
		        if (affectedRows > 0) {
		            System.out.println("✅ Turno aggiunto con successo! ID: " + t.getId());
		        } else {
		            throw new SQLException("Errore: il turno non è stato inserito.");
		        }
		    } catch (SQLException e) {
		        throw new RuntimeException("Errore nell'aggiungere il turno.", e);
		    }		    
	}
	
	
	public Turno getTurnoById(int id) {
	    String sql = "SELECT idTURNO, INSACCATORE, DURATA, ORAINIZIO, STATO FROM TURNO WHERE idTURNO = ?";
	    
	    try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                // Estrai i dati dal ResultSet
	                int idTurno = rs.getInt("idTURNO");
	                int insaccatoreId = rs.getInt("INSACCATORE");
	                int durata = rs.getInt("DURATA");
	                LocalTime orainizio = rs.getTime("ORAINIZIO").toLocalTime();
	                boolean stato = rs.getBoolean("STATO");

	                // Recupera l'insaccatore associato (ipotizziamo un metodo getInsaccatoreById)
	                Insaccatore insaccatore = getInsaccatoreById(insaccatoreId);
	                
	                // Crea l'oggetto Turno e restituiscilo
	                Turno turno = new Turno(durata, orainizio);
	                turno.setId(idTurno);
	                turno.setIns(insaccatore);
	                turno.setStato(stato);
	                
	                return turno;
	            } else {
	                throw new RuntimeException("Turno con ID " + id + " non trovato.");
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Errore nel recupero del turno con ID " + id, e);
	    }
	}
	
	
	
	
	public ArrayList<Turno> getAll(){
		//creo la connessione:
		connessione = ConnessioneDB.startConnection(connessione, "osmotech");
		
		
		
	}
	
	
	
	

}
