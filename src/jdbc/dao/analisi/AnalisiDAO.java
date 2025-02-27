package jdbc.dao.analisi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modello.analisiCampione.AnalisiCampione;
import jdbc.ConnessioneDB;

/* CREATE TABLE `ANALISI` (
  `ID_CAMPIONE` int NOT NULL,
  `ID_PANEL` int DEFAULT NULL,
  `ORARIO_INIZIO` time DEFAULT NULL,
  `ORARIO_FINE` time DEFAULT NULL,
  `GRADAZIONE` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`ID_CAMPIONE`),
  KEY `ID_PANEL` (`ID_PANEL`),
  CONSTRAINT `ANALISI_ibfk_1` 
  FOREIGN KEY (`ID_PANEL`) 
  REFERENCES `PANEL` (`ID_PANEL`)
) 
 */


public class AnalisiDAO implements IAnalisiDAO {

    public AnalisiDAO() {}

    // Metodo insertAnalisi
    @Override
    public boolean insertAnalisi(int idCampione, int idPanel, AnalisiCampione analisi) throws SQLException {
        
        //Controllo dati prima di eseguire la query per evitare di interagire con il database quando i dati non sono validi
        if (idCampione <= 0) {
            throw new IllegalArgumentException(" ID Campione non valido");
        }

        if (idPanel <= 0) {
            throw new IllegalArgumentException("ID Panel non valido");
        }

        if (analisi.getInizioAnalisi() == null || analisi.getFineAnalisi() == null) {
            throw new IllegalArgumentException("Orari di inizio e fine analisi non possono essere null!");
        }

        if (analisi.getFineAnalisi().isBefore(analisi.getInizioAnalisi())) {
            throw new IllegalArgumentException("Orario di fine analisi non può essere prima dell'inizio!");
        }

        if (analisi.getGradazione() < 0) {
            throw new IllegalArgumentException("Gradazione non può essere negativa!");
        }

        //Query SQL
        String query = "INSERT INTO osmotech.ANALISI VALUES (?,?,?,?,?)";

        // Per evitare connessioni multiple, utilizzo un'unica connessione per tutte le operazioni
        try (Connection conn = ConnessioneDB.startConnection(null, "osmotech");
             PreparedStatement ps1 = conn.prepareStatement(query)) {
            
            // Passiamo gli id esistenti e i valori dell'analisi
            ps1.setInt(1, idCampione);
            ps1.setInt(2, idPanel);
            ps1.setTime(3, java.sql.Time.valueOf(analisi.getInizioAnalisi()));
            ps1.setTime(4, java.sql.Time.valueOf(analisi.getFineAnalisi())); 
            ps1.setDouble(5, analisi.getGradazione());
            
            /*Controllo per vedere se la query è stata modificata
            * Utilizzo questo metodo per evitare di trovarci che la query non modifica
            * nessuna riga del database, ma il metodo ritorna comunque true.
            */
            int rowsAffected = ps1.executeUpdate();
            return (rowsAffected > 0);
        
        }catch (Exception e) {
            
            e.printStackTrace();
            return false;

        }
    }


    // Metodo updateAnalisi
    @Override
    public boolean updateAnalisi(int idCampione, int idPanel, AnalisiCampione analisi) throws SQLException {
       
     //Controllo dati prima di eseguire la query per evitare di interagire con il database quando i dati non sono validi
        if (idCampione <= 0) {
            throw new IllegalArgumentException("ID Campione non valido");
        }

        if (idPanel <= 0) {
            throw new IllegalArgumentException(" ID Panel non valido");
        }

        if (analisi.getInizioAnalisi() == null || analisi.getFineAnalisi() == null) {
            throw new IllegalArgumentException("Orari di inizio e fine analisi non possono essere null!");
        }

        if (analisi.getFineAnalisi().isBefore(analisi.getInizioAnalisi())) {
            throw new IllegalArgumentException("Orario di fine analisi non può essere prima dell'inizio!");
        }

        if (analisi.getGradazione() < 0) {
            throw new IllegalArgumentException("Gradazione non può essere negativa!");
        }

        String query = "UPDATE osmotech.ANALISI SET ORARIO_INIZIO = ?, ORARIO_FINE = ?, GRADAZIONE = ? WHERE ID_CAMPIONE = ? AND ID_PANEL = ?";

        try (Connection conn = ConnessioneDB.startConnection(null, "osmotech");
             PreparedStatement ps1 = conn.prepareStatement(query)) {
            
            
             ps1.setTime(1, java.sql.Time.valueOf(analisi.getInizioAnalisi()));
             ps1.setTime(2, java.sql.Time.valueOf(analisi.getFineAnalisi())); 
             ps1.setDouble(3, analisi.getGradazione());
             ps1.setInt(4, idCampione);
             ps1.setInt(5, idPanel);
            
             int rowsAffected = ps1.executeUpdate();
             return (rowsAffected > 0);

        } catch (Exception e) {
            
            e.printStackTrace();
            return false;

        }
    }


    // Metodo eliminaAnalisi
    @Override
    public boolean eliminaAnalisi(int idCampione, int idPanel, AnalisiCampione analisi) throws SQLException {
        
        if (idCampione <= 0) {
            throw new IllegalArgumentException("ID Campione non valido");
        }

        if (idPanel <= 0) {
            throw new IllegalArgumentException("ID Panel non valido");
        }

        String query = "DELETE FROM osmotech.ANALISI WHERE ID_CAMPIONE = ? AND ID_PANEL = ?";

        try (Connection conn = ConnessioneDB.startConnection(null, "osmotech");
             PreparedStatement ps1 = conn.prepareStatement(query)) {
            
            ps1.setInt(1, idCampione);
            ps1.setInt(2, idPanel);
            
            int rowsAffected = ps1.executeUpdate();
            return (rowsAffected > 0);

        } catch (Exception e) {
            
            e.printStackTrace();
            return false;

        }
    }

    // Metodo idCampioniAnalizzati
    @Override
    public ArrayList<Integer> idCampioniAnalizzati() {

        ArrayList<Integer> idCampioni = new ArrayList<>();

        String query = "SELECT DISTINCT ID_CAMPIONE FROM osmotech.ANALISI";

        try (Connection conn = ConnessioneDB.startConnection(null, "osmotech");
             PreparedStatement ps1 = conn.prepareStatement(query);
             ResultSet rs1 = ps1.executeQuery()) {
            
            while (rs1.next()) {
                idCampioni.add(rs1.getInt("ID_CAMPIONE"));
            }

        } catch (Exception e) {
            
            e.printStackTrace();

        }
    
        return idCampioni;

    }


    // Metodo idPanelCampioniAnalizzati
    @Override
    public ArrayList<Integer> idPanelCampioniAnalizzati() {

        ArrayList<Integer> idPanel = new ArrayList<>();

        String query = "SELECT DISTINCT ID_PANEL FROM osmotech.ANALISI";

        try (Connection conn = ConnessioneDB.startConnection(null, "osmotech");
             PreparedStatement ps1 = conn.prepareStatement(query);
             ResultSet rs1 = ps1.executeQuery()) {
            
            while (rs1.next()) {
                idPanel.add(rs1.getInt("ID_PANEL"));
            }

        } catch (Exception e) {
            
            e.printStackTrace();

        }
    
        return idPanel;

    }

}
