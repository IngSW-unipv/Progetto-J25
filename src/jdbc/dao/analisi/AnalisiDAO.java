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
        PreparedStatement ps1;

        boolean queryRiuscita = true;

        try {
            
            String query = "INSERT INTO osmotech.ANALISI VALUES (?,?,?,?)";
            ps1 = conn.prepareStatement(query);
            
            ps1.setInt(1, campione.getId());
            ps1.setInt(2, panel.getId());
            ps1.setTime(3, java.sql.Time.valueOf(analisi.getInizio_Analisi()));
            ps1.setTime(4, java.sql.Time.valueOf(analisi.getFine_Analisi())); 
            ps1.setDouble(5, analisi.getGradazione());
            
            ps1.executeUpdate();
        

        }catch (Exception e) {
            
            e.printStackTrace();
            
            queryRiuscita = false;
        }

        conn = ConnessioneDB.closeConnection(conn);

        return queryRiuscita;
    }

    @Override
    public boolean updateAnalisi(Campione campione, Panel panel, AnalisiCampione analisi) {
       /* 
        *   conn = ConnessioneDB.startConnection(conn, "osmotech");
        *   PreparedStatement ps1;
        *
        *   boolean queryRiuscita = true;
        */
        return false;
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
