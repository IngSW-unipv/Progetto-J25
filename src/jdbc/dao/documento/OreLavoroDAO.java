package jdbc.dao.documento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc.ConnessioneDB;

public class OreLavoroDAO implements IOreLavoroDAO {

	Connection conn;
	
	public OreLavoroDAO() {
		
		
	}
	@Override
	public double selectOreLavoro(int id) {
		
		conn = ConnessioneDB.startConnection(conn, "osmotech");
		PreparedStatement ps1;
		ResultSet rs1;
		
		double oreLavoro = 0;
		
		try {
			
			String query = "SELECT ORE FROM osmotech.ORE_LAVORATE WHERE UTENTE_ID = ?";
					
			ps1 = conn.prepareStatement(query);
			ps1.setInt(1, id);
			
			rs1 = ps1.executeQuery();
			
			while(rs1.next()) {
				
				oreLavoro = rs1.getDouble("ORE");
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		conn = ConnessioneDB.closeConnection(conn);
		
		
		return oreLavoro;
	}

	
}
