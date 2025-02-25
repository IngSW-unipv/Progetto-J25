package jdbc.dao.analisi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import java.time.LocalDate;


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

    private Connection conn;

    public AnalisiDAO() {
        
    }

    @Override
    public boolean insertAnalisi(Campione campione, Panel panel, AnalisiCampione analisi) {
        
        conn = ConnessioneDB.startConnection(conn, "osmotech");

        boolean queryRiuscita = true;


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

        try (PreparedStatement ps1 = conn.prepareStatement(query)) {
            
            ps1.setInt(1, campione.getId());
            ps1.setInt(2, panel.getId());
            ps1.setTime(3, java.sql.Time.valueOf(analisi.getInizioAnalisi()));
            ps1.setTime(4, java.sql.Time.valueOf(analisi.getFineAnalisi())); 
            ps1.setDouble(5, analisi.getGradazione());
            
            ps1.executeUpdate();
        

        }catch (Exception e) {
            
            e.printStackTrace();
            queryRiuscita = false;
        } finally {
            conn = ConnessioneDB.closeConnection(conn);
        }

        return queryRiuscita;
    }

    @Override
    public boolean updateAnalisi(Campione campione, Panel panel, AnalisiCampione analisi) {
       conn = ConnessioneDB.startConnection(conn, "osmotech");

       boolean queryRiuscita = true;

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

        try (PreparedStatement ps1 = conn.prepareStatement(query)) {
            
            ps1.setTime(1, java.sql.Time.valueOf(analisi.getInizioAnalisi()));
            ps1.setTime(2, java.sql.Time.valueOf(analisi.getFineAnalisi())); 
            ps1.setDouble(3, analisi.getGradazione());
            ps1.setInt(4, campione.getId());
            ps1.setInt(5, panel.getId());
            
            ps1.executeUpdate();
        } catch (Exception e) {
            
            e.printStackTrace();
            queryRiuscita = false;

        } finally {
            conn = ConnessioneDB.closeConnection(conn);
        }    

        return queryRiuscita;
    }

    @Override
    public boolean eliminaAnalisi(Campione campione, Panel panel, AnalisiCampione analisi) {
        return false;
    }

    @Override
    public ArrayList<AnalisiCampione> selectAllAnalisi() {
        return null;
    }

    @Override
    public ArrayList<AnalisiCampione> trovaAnalisiPerData(LocalDate data) {
        return null;
    }

    @Override
    public ArrayList<AnalisiCampione> trovaAnalisiPerGradazione(double gradazione) {
        return null;
    }

    @Override
    public ArrayList<AnalisiCampione> trovaAnalisiPerCampione(int idCampione) {
        return null;
    }

}
