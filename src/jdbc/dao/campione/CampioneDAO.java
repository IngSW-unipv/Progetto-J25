package jdbc.dao.campione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.Statement;
import java.time.LocalDate;

import modello.archiviazioneCampione.Campione;
import jdbc.ConnessioneDB;


public class CampioneDAO implements ICampioneDAO {
	
	private Connection conn;
	
	

	public CampioneDAO() {
		
	}

	@Override
	public boolean insertCampione(int id, String stato, LocalDate ld) {
		
		conn = ConnessioneDB.startConnection(conn, "osmotech");
		PreparedStatement ps1;
		
		boolean queryRiuscita = true;
		
		try {
			
			String query = "INSERT INTO osmotech.CAMPIONI VALUES (?,?,?)";
			ps1 = conn.prepareStatement(query);
			
			ps1.setInt(1, id);
			ps1.setString(2, stato);
			ps1.setDate(3, java.sql.Date.valueOf(ld)); //java usa LocalDate e dql usa Date===> devo convertire localDate in Date
			
			ps1.executeUpdate();
			 
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			queryRiuscita = false;
		}
		
		conn = ConnessioneDB.closeConnection(conn);
		
		return queryRiuscita;
	}

	@Override
	public Campione trovaCampionePerId(int id) {
		
		Campione c = null;
		
		conn = ConnessioneDB.startConnection(conn, "osmotech");
		PreparedStatement ps1;
		ResultSet rs1;
		
		try {
			
			String query = "SELECT * FROM osmotech.CAMPIONI WHERE ID_CAMPIONE = ?";
			
			ps1 = conn.prepareStatement(query);
			ps1.setInt(1, id);
			
			rs1 = ps1.executeQuery();
			
			while(rs1.next()) {
				java.sql.Date sqlDate = rs1.getDate("DATA_ARRIVO");  
				java.time.LocalDate localDate = sqlDate.toLocalDate();
				c = new Campione(rs1.getInt("ID_CAMPIONE"), rs1.getString("STATO"), localDate);
			}
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		conn = ConnessioneDB.closeConnection(conn);
		return c;
	}

	@Override
	public ArrayList<Campione> selectAllCampioni() {
		ArrayList<Campione> campioni = new ArrayList<>();
		
		conn = ConnessioneDB.startConnection(conn, "osmotech");
		Statement st1;
		ResultSet rs1;
		
		try {
			
			st1 = conn.createStatement();
			String query = "SELECT * FROM osmotech.CAMPIONI";
			rs1 = st1.executeQuery(query);
			
			while(rs1.next()) {
				
				java.sql.Date sqlDate = rs1.getDate("DATA_ARRIVO");  
				java.time.LocalDate localDate = sqlDate.toLocalDate();
				Campione c = new Campione(rs1.getInt("ID_CAMPIONE"), rs1.getString("STATO"), localDate);
				
				campioni.add(c);
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		conn = ConnessioneDB.closeConnection(conn);
		
		
		return campioni;
	}

	@Override
	public boolean updateCampione(int id, String stato) {
		
		conn = ConnessioneDB.startConnection(conn, "osmotech");
		PreparedStatement ps1;
		
		boolean queryRiuscita = true;
		
		try {
			
			String query = "UPDATE osmotech.CAMPIONI SET STATO = ? WHERE ID_CAMPIONE = ?";
			ps1 = conn.prepareStatement(query);
			
			ps1.setString(1, stato);
			ps1.setInt(2, id);
			
			ps1.executeUpdate();
			  
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			queryRiuscita = false;
		}
		
		conn = ConnessioneDB.closeConnection(conn);
		return queryRiuscita;
	}

	@Override
	public boolean eliminaCampione(int id) {
		
		conn = ConnessioneDB.startConnection(conn, "osmotech");
		
		PreparedStatement ps1;
		
		boolean queryRiuscita = true;
		
		try {
			
			String query = "DELETE FROM osmotech.CAMPIONI where ID_CAMPIONE = ?";
			ps1 = conn.prepareStatement(query);
			
			ps1.setInt(1, id);
			
			ps1.executeUpdate();
			  
		}catch(Exception e) {
			
			e.printStackTrace();
			
			queryRiuscita = false;
		}
		
		conn = ConnessioneDB.closeConnection(conn);
		return queryRiuscita;
		
	}

	@Override
	public ArrayList<Integer> trovaCampioneNonAnalizzato() {
		
		ArrayList<Integer> idCampioni = new ArrayList<>();
		
		conn = ConnessioneDB.startConnection(conn, "osmotech");
		
		PreparedStatement ps1;
		ResultSet rs1;
		
		try {
			
			String query = "SELECT ID_CAMPIONE FROM osmotech.CAMPIONI WHERE STATO = ?";
			ps1 = conn.prepareStatement(query);
			
			ps1.setString(1, "Non analizzato");
			rs1 = ps1.executeQuery();
			
			while(rs1.next()) {
				
				int id = rs1.getInt("ID_CAMPIONE");
				idCampioni.add(id);
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return idCampioni;
	}
	
}
