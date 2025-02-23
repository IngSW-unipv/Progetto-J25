package jdbc.dao.max;

import java.sql.*;
import jdbc.ConnessioneDB;
import modello.prenotazioneInsaccatore.*;
import java.time.*;
import java.util.ArrayList;

/* TABELLA DI RIFERIMENTO:
 * 
   CREATE TABLE `GIORNO` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` enum('LUNEDI','MARTEDI','MERCOLEDI','GIOVEDI','VENERDI','SABATO','DOMENICA') NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`id`)
) 

 */
public class GiornoDAO implements IGiornoDAO {
	
	private static final String DB = "osmotech";
	private Connection connessione;
	
	   public void aggiungi(Giorno giorno) {
	        connessione = ConnessioneDB.startConnection(connessione, DB);
	        String sql = "INSERT INTO GIORNO (tipo, data) VALUES (?, ?)";

	        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
	            stmt.setString(1, giorno.getTipo().name());  // Usa name() per l'Enum
	            stmt.setDate(2, java.sql.Date.valueOf(giorno.getData()));

	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            throw new RuntimeException("Errore nell'aggiungere il giorno.", e);
	        } finally {
	            ConnessioneDB.closeConnection(connessione);
	        }
	    }

	    @Override
	    public Giorno trova(int id) {
	        connessione = ConnessioneDB.startConnection(connessione, DB);
	        Giorno giorno = null;
	        String sql = "SELECT tipo, data FROM GIORNO WHERE id = ?";

	        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
	            stmt.setInt(1, id);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    GiorniSettimana tipo = GiorniSettimana.valueOf(rs.getString("tipo"));
	                    LocalDate data = rs.getDate("data").toLocalDate();

	                    giorno = new Giorno(tipo, data);
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("Errore nel trovare il giorno.", e);
	        } finally {
	            ConnessioneDB.closeConnection(connessione);
	        }

	        return giorno;
	    }

	    @Override
	    public ArrayList<Giorno> trovaTutti() {
	        connessione = ConnessioneDB.startConnection(connessione, DB);
	        ArrayList<Giorno> giorni = new ArrayList<>();
	        String sql = "SELECT tipo, data FROM GIORNO";

	        try (PreparedStatement stmt = connessione.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                GiorniSettimana tipo = GiorniSettimana.valueOf(rs.getString("tipo"));
	                LocalDate data = rs.getDate("data").toLocalDate();

	                Giorno giorno = new Giorno(tipo, data);
	                giorni.add(giorno);
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("Errore nel recuperare i giorni.", e);
	        } finally {
	            ConnessioneDB.closeConnection(connessione);
	        }

	        return giorni;
	    }

	    @Override
	    public void aggiorna(Giorno giorno) {
	        connessione = ConnessioneDB.startConnection(connessione, DB);
	        String sql = "UPDATE GIORNO SET tipo = ?, data = ? WHERE tipo = ? AND data = ?";

	        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
	            stmt.setString(1, giorno.getTipo().name());
	            stmt.setDate(2, java.sql.Date.valueOf(giorno.getData()));
	            stmt.setString(3, giorno.getTipo().name());  // per identificare il giorno da aggiornare
	            stmt.setDate(4, java.sql.Date.valueOf(giorno.getData()));

	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            throw new RuntimeException("Errore nell'aggiornare il giorno.", e);
	        } finally {
	            ConnessioneDB.closeConnection(connessione);
	        }
	    }

	    @Override
	    public void rimuovi(Giorno giorno) {
	        connessione = ConnessioneDB.startConnection(connessione, DB);
	        String sql = "DELETE FROM GIORNO WHERE tipo = ? AND data = ?";

	        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
	            stmt.setString(1, giorno.getTipo().name());
	            stmt.setDate(2, java.sql.Date.valueOf(giorno.getData()));

	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            throw new RuntimeException("Errore nel rimuovere il giorno.", e);
	        } finally {
	            ConnessioneDB.closeConnection(connessione);
	        }
	    }

}