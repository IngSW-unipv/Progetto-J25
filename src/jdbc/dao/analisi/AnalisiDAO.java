package jdbc.dao.analisi;

import java.sql.Connection;
import java.sql.PreparedStatement;


import modello.analisiCampione.AnalisiCampione;
import modello.archiviazioneCampione.Campione;
import modello.creazionePanel.Panel;
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
    public boolean insertAnalisi(Campione campione, Panel panel, AnalisiCampione analisi) {
        
        //Controllo dati prima di eseguire la query per evitare di interagire con il database quando i dati non sono validi
        if (campione == null || campione.getId() <= 0) {
            throw new IllegalArgumentException("Campione non valido");
        }

        if (panel == null || panel.getId() <= 0) {
            throw new IllegalArgumentException("Panel non valido");
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
            ps1.setInt(1, campione.getId());
            ps1.setInt(2, panel.getId());
            ps1.setTime(3, java.sql.Time.valueOf(analisi.getInizioAnalisi()));
            ps1.setTime(4, java.sql.Time.valueOf(analisi.getFineAnalisi())); 
            ps1.setDouble(5, analisi.getGradazione());
            
            /*Controllo per vedere se la query è stata modificata
            * Utilizzo questo metodo per evitare di trovarci in cui la query non modifica
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
    public boolean updateAnalisi(Campione campione, Panel panel, AnalisiCampione analisi) {
       
     //Controllo dati prima di eseguire la query per evitare di interagire con il database quando i dati non sono validi
        if (campione == null || campione.getId() <= 0) {
            throw new IllegalArgumentException("Campione non valido");
        }

        if (panel == null || panel.getId() <= 0) {
            throw new IllegalArgumentException("Panel non valido");
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
             ps1.setInt(4, campione.getId());
             ps1.setInt(5, panel.getId());
            
             int rowsAffected = ps1.executeUpdate();
             return (rowsAffected > 0);

        } catch (Exception e) {
            
            e.printStackTrace();
            return false;

        }
    }


    // Metodo eliminaAnalisi
    @Override
    public boolean eliminaAnalisi(Campione campione, Panel panel, AnalisiCampione analisi) {
        
        if (campione == null || campione.getId() <= 0) {
            throw new IllegalArgumentException("Campione non valido");
        }

        String query = "DELETE FROM osmotech.ANALISI WHERE ID_CAMPIONE = ? AND ID_PANEL = ?";

        try (Connection conn = ConnessioneDB.startConnection(null, "osmotech");
             PreparedStatement ps1 = conn.prepareStatement(query)) {
            
            ps1.setInt(1, campione.getId());
            ps1.setInt(2, panel.getId());
            
            int rowsAffected = ps1.executeUpdate();
            return (rowsAffected > 0);

        } catch (Exception e) {
            
            e.printStackTrace();
            return false;

        }
    }

}
