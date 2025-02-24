
package jdbc.dao.max;

import java.sql.*;

import java.util.ArrayList;
import modello.prenotazioneInsaccatore.*;
import jdbc.ConnessioneDB;
import java.sql.Connection;




//QUESTA E' LA TABLE DI RIFERIMENTO:
/*
CREATE TABLE `TURNO` (
  `idTURNO` int NOT NULL AUTO_INCREMENT,
  `INSACCATORE` int DEFAULT NULL,
  `ORA_INIZIO` time DEFAULT NULL,
  `DURATA` int DEFAULT NULL,
  `STATO` tinyint DEFAULT NULL,
  `tipoGIORNO` enum('LUNEDI','MARTEDI','MERCOLEDI','GIOVEDI','VENERDI','SABATO','DOMENICA') DEFAULT NULL,
  PRIMARY KEY (`idTURNO`),
  KEY `fk_tipo_giorno` (`tipoGIORNO`),
  CONSTRAINT `fk_tipo_giorno` FOREIGN KEY (`tipoGIORNO`) REFERENCES `GIORNO` (`tipo`) ON DELETE CASCADE ON UPDATE CASCADE
) 
*/

public class TurnoDAO implements ITurnoDAO {
    
    private static final String DB = "osmotech";
    private Connection connessione;

    
    @Override
    public void aggiungi(Turno t) {
        connessione = ConnessioneDB.startConnection(connessione, DB);
        String sql = "INSERT INTO TURNO (INSACCATORE, ORA_INIZIO, DURATA, STATO, tipoGIORNO) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connessione.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, t.getId());
            stmt.setTime(2, java.sql.Time.valueOf(t.getOrainizio()));
            stmt.setInt(3, t.getDurata());
            stmt.setBoolean(4, t.isStato());
            stmt.setString(5, t.getTipoGiorno().name());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    t.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'aggiungere il turno.", e);
        } finally {
            ConnessioneDB.closeConnection(connessione);
        }
    }

    @Override
    public Turno trova(Integer idturno) {
        connessione = ConnessioneDB.startConnection(connessione, DB);
        String sql = "SELECT * FROM TURNO WHERE idTURNO = ?";

        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setInt(1, idturno);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Turno turno = new Turno(rs.getInt("DURATA"), rs.getTime("ORA_INIZIO").toLocalTime());
                turno.setId(rs.getInt("idTURNO"));
                turno.setStato(rs.getBoolean("STATO"));
                return turno;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel recuperare il turno con id " + idturno, e);
        } finally {
            ConnessioneDB.closeConnection(connessione);
        }
        return null;
    }

    @Override
    public ArrayList<Turno> trovaTutti() {
        connessione = ConnessioneDB.startConnection(connessione, DB);
        ArrayList<Turno> turni = new ArrayList<>();
        String sql = "SELECT idTURNO, ORA_INIZIO, DURATA, STATO, tipoGIORNO FROM TURNO";

        try (PreparedStatement stmt = connessione.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Turno turno = new Turno(rs.getInt("DURATA"), rs.getTime("ORA_INIZIO").toLocalTime());
                turno.setId(rs.getInt("idTURNO"));
                turno.setStato(rs.getBoolean("STATO"));
                turni.add(turno);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel recuperare tutti i turni.", e);
        } finally {
            ConnessioneDB.closeConnection(connessione);
        }
        return turni;
    }

    @Override
    public void aggiorna(Turno t) {
        connessione = ConnessioneDB.startConnection(connessione, DB);
        String sql = "UPDATE TURNO SET ORA_INIZIO = ?, DURATA = ?, STATO = ?, tipoGIORNO = ? WHERE idTURNO = ?";

        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setTime(1, java.sql.Time.valueOf(t.getOrainizio()));
            stmt.setInt(2, t.getDurata());
            stmt.setBoolean(3, t.isStato());
            stmt.setString(4, t.getTipoGiorno().name());
            stmt.setInt(5, t.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'aggiornare il turno con id " + t.getId(), e);
        } finally {
            ConnessioneDB.closeConnection(connessione);
        }
    }

    @Override
    public void rimuovi(Turno t) {
        connessione = ConnessioneDB.startConnection(connessione, DB);
        String sql = "DELETE FROM TURNO WHERE idTURNO = ?";

        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setInt(1, t.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'eliminare il turno con id " + t.getId(), e);
        } finally {
            ConnessioneDB.closeConnection(connessione);
        }
    }

    @Override
    public ArrayList<Turno> recuperaTurniGiorno(Giorno giorno) {
        connessione = ConnessioneDB.startConnection(connessione, DB);
        ArrayList<Turno> turni = new ArrayList<>();
        
        // La query recupera i turni associati al tipo di giorno
        String sql = "SELECT idTURNO, ORA_INIZIO, DURATA, STATO, tipoGIORNO " +
                     "FROM TURNO " +
                     "WHERE tipoGIORNO = ?";  // La query è filtrata per tipoGIORNO

        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            // Impostiamo il parametro tipoGIORNO come l'ENUM passato nell'oggetto Giorno
            stmt.setString(1, giorno.getTipo().name());  // giorno.getTipo() restituisce LUNEDI, MARTEDI, etc.

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Creiamo l'oggetto Turno recuperando i dati
                    Turno turno = new Turno(rs.getInt("DURATA"), rs.getTime("ORA_INIZIO").toLocalTime());
                    turno.setId(rs.getInt("idTURNO"));
                    turno.setStato(rs.getBoolean("STATO"));
                    turni.add(turno);  // Aggiungiamo il turno alla lista
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel recuperare i turni per il giorno " + giorno.getTipo(), e);
        } finally {
            ConnessioneDB.closeConnection(connessione);
        }
        
        return turni;
    }
}
