package jdbc.dao.max;

import java.sql.*;
import jdbc.ConnessioneDB;
import modello.prenotazioneInsaccatore.*;
import java.time.*;
import java.util.ArrayList;

/* TABELLA DI RIFERIMENTO:
 * 
CREATE TABLE `GIORNO` (
  `tipo` ENUM('LUNEDI','MARTEDI','MERCOLEDI','GIOVEDI','VENERDI') NOT NULL,
  `data` DATE NOT NULL,
  PRIMARY KEY (`tipo`)
);


 */
public class GiornoDAO implements IGiornoDAO {
    
    private static final String DB = "osmotech";
    private Connection connessione;

    @Override
    public void aggiungi(Giorno giorno) {
        connessione = ConnessioneDB.startConnection(connessione, DB);
        String sql = "INSERT INTO GIORNO (tipo, data) VALUES (?, ?)";

        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setString(1, giorno.getTipo().name());
            stmt.setDate(2, java.sql.Date.valueOf(giorno.getData()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'aggiungere il giorno.", e);
        } finally {
            ConnessioneDB.closeConnection(connessione);
        }
    }

    @Override
    public Giorno trova(GiorniSettimana tipo) {  //uso il tipo per trovare il mio giorno nel database
        connessione = ConnessioneDB.startConnection(connessione, DB);
        Giorno giorno = null;
        String sql = "SELECT data FROM GIORNO WHERE tipo = ?";

        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setString(1, tipo.name());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
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
                giorni.add(new Giorno(tipo, data));
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
        String sql = "UPDATE GIORNO SET data = ? WHERE tipo = ?";

        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(giorno.getData()));
            stmt.setString(2, giorno.getTipo().name());
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
        String sql = "DELETE FROM GIORNO WHERE tipo = ?";

        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setString(1, giorno.getTipo().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel rimuovere il giorno.", e);
        } finally {
            ConnessioneDB.closeConnection(connessione);
        }
    }
    
    
    
    //METODO PER SVUOTARE L'INTERA SETTIMANA DI GIORNI: 
    public void svuotaSettimana() {
    	connessione = ConnessioneDB.startConnection(connessione, DB);
    	String sql = "DELETE FROM GIORNO";
    	
    	try(PreparedStatement st = connessione.prepareStatement(sql)){
    		//cancello tutti i giorni:
    		st.executeUpdate();
    	}
    	catch(SQLException q) {
    		throw new RuntimeException("Errore nell'eliminare tutte la settimana in GIORNO",q);
    	}finally {
    		ConnessioneDB.closeConnection(connessione);
    	}
    }
}
