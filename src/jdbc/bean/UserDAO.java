package jdbc.bean;
import jdbc.ConnessioneDB;
import modello.Utente;

import java.sql.*;
import java.time.LocalDate;

public class UserDAO {
    private Connection connection;
    public UserDAO() {
        super();
    }

    public UserDAO(Connection connection) {
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

  public void registraUtente(String emailInput, String passwordInput, String luogoNascitaInput, LocalDate dataNascitaInput,String codiceFiscaleInput,
                             String residenzaInput, String nicknameInput) throws SQLException {
        String[] emailParts = emailInput.split("@")[0].split("\\.");
        String nome = emailParts[0];
        String cognome = emailParts[1].substring(0, emailParts[1].length()-2);
        String ruolo = emailParts[1].substring(emailParts[1].length()-2);

        connection = ConnessioneDB.startConnection(connection, "osmotech");
        try{
            String query = "UPDATE osmotech.UTENTE SET NOME = ?, COGNOME = ?, PASSWORD = ?, LUOGONASCITA = ?, DATANASCITA = ?, CODICEFISCALE = ?, RESIDENZA = ?, NICKNAME = ?, RUOLO = ? WHERE EMAIL = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, nome);
            pst.setString(2, cognome);
            pst.setString(3, passwordInput);
            pst.setString(4, luogoNascitaInput);
            pst.setString(5, String.valueOf(Date.valueOf(dataNascitaInput)));
            pst.setString(6, codiceFiscaleInput);
            pst.setString(7, residenzaInput);
            pst.setString(8, nicknameInput);
            pst.setString(9, ruolo);
            pst.setString(10, emailInput);

            int rowsUpdated = pst.executeUpdate();
            if(rowsUpdated > 0) {
                System.out.println("Registrazione completata con successo.");
            } else {
                System.out.println("Errore : nessun utente trovato con questa email.");
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
          ConnessioneDB.closeConnection(connection);



        }
  }

  public String controlloLogin(String usernameOrEmailInput, String passwordInput) throws SQLException {
        connection = ConnessioneDB.startConnection(connection, "osmotech");
        PreparedStatement pst = null;
        ResultSet rs = null;
        String ruolo = null;
        try{
            String query = "SELECT RUOLO FROM osmotech.UTENTE WHERE (EMAIL = ? OR NICKNAME = ?) AND PASSWORD = ?";
            pst = connection.prepareStatement(query);
            pst.setString(1, usernameOrEmailInput);
            pst.setString(2, usernameOrEmailInput);
            pst.setString(3, passwordInput);
            rs = pst.executeQuery();

            if(rs.next()) {
                ruolo = rs.getString("RUOLO");
            } else {
                System.out.println("Credenziali non valide.");
            }

        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            if(pst != null) pst.close();
            if(rs != null) rs.close();
            ConnessioneDB.closeConnection(connection);
        }
        return ruolo;
  }

    }






