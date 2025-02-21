package jdbc.dao.autenticazione;
import jdbc.ConnessioneDB;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private Connection connection;
    public UserDAO() {
        super();
    }

    public UserDAO(Connection conn) {
        this.connection = connection;


    }

    public boolean controlloEmail(String emailInput) throws SQLException {
        connection = ConnessioneDB.startConnection(connection, "osmotech");
        PreparedStatement pst = null; //crei oggetto dentro il quale mettere la query
        ResultSet rs = null;
        boolean emailTrovata = false; //inizialmente si asssume che l'email non sia trovata
        try{
            String query = "SELECT 1 FROM osmotech.UTENTE WHERE EMAIL = ?";
            pst = connection.prepareStatement(query);
            pst.setString(1, emailInput);
            rs = pst.executeQuery();

            if(rs.next()) {
                emailTrovata = true;
            } }

        catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(pst != null) rs.close();
                if(pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnessioneDB.closeConnection(connection);
        }
        return emailTrovata;
    }

  /*  public boolean controlloEmail(String emailInput) throws SQLException {
        connection = ConnessioneDB.startConnection(connection, "osmotech");
        PreparedStatement pst = null; //crei oggetto dentro il quale mettere la query
        ResultSet rs = null;
        boolean emailTrovata = false; //inizialmente si asssume che l'email non sia trovata
        try{
            String query = "SELECT 1 FROM osmotech.UTENTE WHERE EMAIL = ?";
            pst = connection.prepareStatement(query);
            pst.setString(1, emailInput);
            rs = pst.executeQuery();

            if(rs.next()) {
                emailTrovata = true;
            } }

            catch(SQLException e){
                e.printStackTrace();
            } finally {
                    try {
                        if(pst != null) rs.close();
                        if(pst != null) pst.close();
                    } catch (SQLException e) {
                    e.printStackTrace();
                }
                ConnessioneDB.closeConnection(connection);
                }
            return emailTrovata;
        } */

    }






