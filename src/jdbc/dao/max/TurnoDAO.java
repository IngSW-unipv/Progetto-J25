
package jdbc.dao.max;

import java.sql.*;

import java.util.ArrayList;
import modello.prenotazioneInsaccatore.*;
import jdbc.ConnessioneDB;
import java.sql.Connection;
import java.time.LocalTime;
import java.time.LocalDate;




//QUESTA E' LA TABLE DI RIFERIMENTO:
/*
   CREATE TABLE `TURNO` (
  `idTURNO` int NOT NULL AUTO_INCREMENT,
  `INSACCATORE` int DEFAULT NULL,
  `ORA_INIZIO` time DEFAULT NULL,
  `DURATA` int DEFAULT NULL,
  `STATO` tinyint DEFAULT NULL,
  `idGIORNO` int NOT NULL,
  PRIMARY KEY (`idTURNO`),
  KEY `fk_giorno` (`idGIORNO`),
  CONSTRAINT `fk_giorno` FOREIGN KEY (`idGIORNO`) REFERENCES `GIORNO` (`id`) ON DELETE CASCADE
)
*/

public class TurnoDAO implements ITurnoDAO{
	private static final String DB = "osmotech";
	private Connection connessione;
	
	
	//METODI CRUD:
	
	//METODO AGGIUNGI QUERY DA OGGETTO:
	public void aggiungi(Turno t) {
	    // Mi connetto al database
	    connessione = ConnessioneDB.startConnection(connessione, DB);
	    
	    // Query per l'inserimento (senza INSACCATORE e senza idTURNO)
	    String sql = "INSERT INTO TURNO (INSACCATORE, ORA_INIZIO, DURATA, STATO) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
	        // Imposto i parametri
	        // Non impostiamo idTURNO, perché è auto incrementato
	        stmt.setInt(1, t.getId()); // INSACCATORE
	        stmt.setTime(2, java.sql.Time.valueOf(t.getOrainizio())); // Converte LocalTime in SQL Time
	        stmt.setInt(3, t.getDurata()); // Durata in minuti
	        stmt.setInt(4, t.isStato() ? 1 : 0); // Converte boolean in tinyint (1 = true, 0 = false)

	        // Eseguo la query
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        throw new RuntimeException("Errore nell'aggiungere il turno.", e);
	    } finally {
	        // Chiudo la connessione
	        ConnessioneDB.closeConnection(connessione);
	        System.out.println("Aggiunta avvenuta!");
	    }
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
	            Turno turno = new Turno(rs.getInt("DURATA"), rs.getTime("ORA_INIZIO").toLocalTime());
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
		connessione = ConnessioneDB.startConnection(connessione, DB);

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
	    String sql = "UPDATE TURNO SET `ORA_INIZIO` = ?, DURATA = ?, STATO = ? WHERE idTURNO = ?";

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
	
	public void rimuovi(Turno t) {
		//mi connetto:
		connessione = ConnessioneDB.startConnection(connessione, DB);
		//preparo la query:
		String sql = "DELETE FROM TURNO WHERE idTURNO=?";
		try (PreparedStatement stmt = connessione.prepareStatement(sql)){
			stmt.setInt(1, t.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Errore nell'eliminare il turno con id " + t.getId(),e);
		}
	    //chiudo la connessione:
		 ConnessioneDB.closeConnection(connessione);
		 
		 System.out.println("Rimozione avvenuta!");
	}	
	
	
	
	//METODI UTILI: 
	
	//METODO CHE RECUPERA I TURNI PER GIORNO:
	    public ArrayList<Turno> recuperaTurniGiorno(Giorno giorno) {
	        // Creo la connessione:
	        connessione = ConnessioneDB.startConnection(connessione, DB);

	        ArrayList<Turno> turni = new ArrayList<>();
	        // Preparo la query:
	        String sql = "SELECT idTURNO, INSACCATORE, ORA_INIZIO, DURATA, STATO FROM TURNO WHERE idGIORNO = (SELECT id FROM GIORNO WHERE data = ? AND tipo = ?)";

	        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
	            stmt.setDate(1, java.sql.Date.valueOf(giorno.getData()));
	            stmt.setString(2, giorno.getTipo().name());
	            
	            try (ResultSet rs = stmt.executeQuery()) {
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
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("Errore nel recuperare i turni per il giorno.", e);
	        }

	        // Chiudo la connessione:
	        ConnessioneDB.closeConnection(connessione);
	        
	        return turni;
	    }



}

