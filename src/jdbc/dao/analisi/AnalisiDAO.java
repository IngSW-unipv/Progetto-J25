package jdbc.dao.analisi;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.util.ArrayList;

import java.time.LocalDate;
//import java.util.ArrayList;
//import java.sql.Statement;

import modello.analisiCampione.AnalisiCampione;
//import modello.creazionePanel.Panel;
import jdbc.ConnessioneDB;



public class  AnalisiDAO implements IAnalisiDAO {

    private Connection conn;

    public AnalisiDAO() {
        
    }


    @Override
    public boolean insertAnalisi(LocalDate dataAnalisi, double gradazione, int idCampione, int idPanel) {
        
        conn = ConnessioneDB.startConnection(conn, "osmotech");
        PreparedStatement ps1;

        boolean queryRiuscita = true;

        try {
            
            String query = "INSERT INTO osmotech.ANALISI VALUES (?,?,?,?)";
            ps1 = conn.prepareStatement(query);
            
            ps1.setDate(1, java.sql.Date.valueOf(dataAnalisi));
            ps1.setDouble(2, gradazione);
            ps1.setInt(3, idCampione);
            ps1.setInt(4, idPanel);
            
            ps1.executeUpdate();
        

        }catch (Exception e) {
            
            e.printStackTrace();
            
            queryRiuscita = false;
        }

        conn = ConnessioneDB.closeConnection(conn);

        return queryRiuscita;
    }

    @Override
    public boolean updateAnalisi(int id, LocalDate dataAnalisi, double gradazione, int idCampione, int idPanel) {
        
        return false;
    }

    @Override
    public boolean eliminaAnalisi(LocalDate dataAnalisi, double gradazione, int idCampione, int idPanel) {
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

    @Override
    public ArrayList<AnalisiCampione> trovaAnalisiPerPanel(int idPanel) {
        return null;
    }


}
