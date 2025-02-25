package jdbc.dao.analisi;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.util.ArrayList;

import java.time.LocalDate;
//import java.util.ArrayList;
//import java.sql.Statement;

import modello.analisiCampione.AnalisiCampione;
import modello.archiviazioneCampione.Campione;
import modello.creazionePanel.Panel;
//import modello.creazionePanel.Panel;
import jdbc.ConnessioneDB;



public class  AnalisiDAO implements IAnalisiDAO {

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
        
        conn = ConnessioneDB.startConnection(conn, "osmotech");
        PreparedStatement ps1;

        boolean queryRiuscita = true;

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
