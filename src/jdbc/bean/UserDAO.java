package jdbc.bean;
import jdbc.ConnessioneDB;
import modello.Insaccatore;
import modello.Panelista;
import modello.Utente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserDAO implements IUserDAO{
	
    private Connection connection;
    
    public UserDAO() {
        super();
    }

    public UserDAO(Connection conn) {
        this.connection = connection;



    }

 /* @Override
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
    } */
  
  
@Override
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
  
  		public Panelista selectPanelista(int id) {
  			
  			connection = ConnessioneDB.startConnection(connection, "osmotech");
  			Panelista p = null;
  			
  			PreparedStatement ps1;
  			ResultSet rs1;
  			
  			try {
  				
  				String query = "SELECT * FROM osmotech.UTENTE WHERE ID = ?";
  				ps1 = connection.prepareStatement(query);
  				
  				ps1.setInt(1, id);
  				rs1 = ps1.executeQuery();
  				
  				while(rs1.next()) {
  					
  					java.sql.Date sqlDate = rs1.getDate("DATANASCITA");  
  					java.time.LocalDate localDate = sqlDate.toLocalDate();
  					p = new Panelista(rs1.getInt("ID"), rs1.getString("EMAIL"),rs1.getString("NOME"), rs1.getString("COGNOME"),
  							rs1.getString("LUOGONASCITA"), localDate, rs1.getString("CODICEFISCALE"), rs1.getString("NICKNAME"),
  							rs1.getString("PASSWORD"), rs1.getString("RUOLO"), rs1.getString("RESIDENZA"), rs1.getDouble("ORELAVORO"));
  				}
  						
  			}catch(Exception e) {
  				
  				e.printStackTrace();
  			}
  			
  			connection = ConnessioneDB.closeConnection(connection);
  			return p;
  			
  		}

    public ArrayList<Panelista> getPanelistas() {

        ArrayList<Panelista> panelisti = new ArrayList<>();
        connection = ConnessioneDB.startConnection(connection, "osmotech");
        // Avvia la connessione
        // La query SQL per ottenere i panelisti con ruolo 'op' o 'insaccatore'
        String query = "SELECT * FROM UTENTE WHERE RUOLO IN ('pa', 'insaccatore')";

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Itera sui risultati della query e crea gli oggetti Panelista
            while (rs.next()) {
                int id = rs.getInt("ID");
                String email = rs.getString("EMAIL");
                String nome = rs.getString("NOME");
                String cognome = rs.getString("COGNOME");
                String luogoNascita = rs.getString("LUOGONASCITA");
                LocalDate dataNascita = rs.getDate("DATANASCITA").toLocalDate(); // Convertiamo la data
                String codiceFiscale = rs.getString("CODICEFISCALE");
                String nickname = rs.getString("NICKNAME");
                String password = rs.getString("PASSWORD");
                String ruolo = rs.getString("RUOLO");
                String residenza = rs.getString("RESIDENZA");
                double oreLavoro = rs.getDouble("ORELAVORO");

                // Crea un oggetto Panelista e aggiungilo alla lista
                Panelista panelista = new Panelista(id, email, nome, cognome, luogoNascita, dataNascita,
                        codiceFiscale, nickname, password, ruolo, residenza, oreLavoro);
                panelisti.add(panelista);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Chiudi la connessione al database
            ConnessioneDB.closeConnection(connection);
        }

        return panelisti;  // Restituisce la lista di panelisti
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



  public boolean cambiaRuoloUtente(String email, String nuovoRuolo) throws SQLException {
        connection = ConnessioneDB.startConnection(connection, "osmotech");
        PreparedStatement pst = null;
        boolean successo = false;

        try {
            String query = "UPDATE osmotech.UTENTE SET RUOLO = ? WHERE EMAIL = ?";
            pst = connection.prepareStatement(query);
            pst.setString(1, nuovoRuolo);
            pst.setString(2, email);

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                successo = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            ConnessioneDB.closeConnection(connection);
        }

        return successo;
    }

    public String getRuoloByEmail(String email) throws SQLException {
        connection = ConnessioneDB.startConnection(connection, "osmotech");
        PreparedStatement pst = null;
        ResultSet rs = null;
        String ruolo = null;

        try {
            String query = "SELECT RUOLO FROM UTENTE WHERE EMAIL = ?";
            pst = connection.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();

            if (rs.next()) {
                ruolo = rs.getString("RUOLO");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnessioneDB.closeConnection(connection);
        }

        return ruolo;
    }
    public ArrayList<Utente> getAllUtenti() throws SQLException {
        ArrayList<Utente> listaUtenti = new ArrayList<>();
        connection = ConnessioneDB.startConnection(connection, "osmotech");
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM UTENTE";
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                java.sql.Date SQLdate = rs.getDate("DATANASCITA");
                java.time.LocalDate localeDate = null;

                if(SQLdate != null) {
                    localeDate = SQLdate.toLocalDate();
                }
                Utente utente = new Utente(
                        rs.getInt("ID"),
                        rs.getString("EMAIL"),
                        rs.getString("NOME"),
                        rs.getString("COGNOME"),
                        rs.getString("LUOGONASCITA"),
                        localeDate,
                        rs.getString("CODICEFISCALE"),
                        rs.getString("RESIDENZA"),
                        rs.getString("NICKNAME"),
                        rs.getString("PASSWORD"),
                        rs.getString("RUOLO")
                );
                listaUtenti.add(utente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            ConnessioneDB.closeConnection(connection);
        }

        return listaUtenti;
    }


    
    
 

    public void cambiaPassword(Utente utente, String passwordInput ) throws SQLException {
        connection = ConnessioneDB.startConnection(connection, "osmotech");
        String query = "UPDATE UTENTE SET PASSWORD = ? WHERE EMAIL = ?";
        try (
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, passwordInput);
            pst.setString(2, utente.getEmail());
            pst.executeUpdate();
        }
    }

    public boolean updateRuolo(int id, String ruolo) throws SQLException {
    String query = "UPDATE UTENTE SET RUOLO = ? WHERE ID = ?";
    connection = ConnessioneDB.startConnection(connection, "osmotech");
    try(
            PreparedStatement pst = connection.prepareStatement(query)){
                pst.setString(1, ruolo);
                pst.setInt(2, id);
                int rowsUpdated = pst.executeUpdate();
                return rowsUpdated > 0;
    }

    }

    public boolean inserisciIban(int userId, String iban) throws SQLException {
        String query = "INSERT INTO CONTO (id,iban) VALUES (?,?) ON DUPLICATE KEY UPDATE iban = ?";
        connection = ConnessioneDB.startConnection(connection, "osmotech");
        try (
                PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, userId);
            pst.setString(2, iban);
            pst.setString(3, iban);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } finally {
    if(connection != null) ConnessioneDB.closeConnection(connection);
    }}

    public String getIban(Utente utente) throws SQLException {
        connection = ConnessioneDB.startConnection(connection, "osmotech");
        String query = "SELECT IBAN FROM CONTO WHERE ID = ?";
        ResultSet rs = null;
        String iban = null;

        try (
                PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, utente.getId());
            rs = pst.executeQuery();

            // Verifica se ci sono risultati
            if (rs.next()) {
                iban = rs.getString("IBAN");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return iban;
    }

    public Insaccatore getInsaccatore(int idUtente) throws SQLException {
        connection = ConnessioneDB.startConnection(connection, "osmotech");
        PreparedStatement pst = null;
        ResultSet rs = null;
        Insaccatore insaccatore = null;  // Sposta l'oggetto fuori dal try

        try {
            // Query con condizione WHERE per selezionare solo l'insaccatore con l'ID specificato
            String query = "SELECT * FROM UTENTE WHERE ID = ? AND RUOLO = 'INSACCATORE'";
            pst = connection.prepareStatement(query);
            pst.setInt(1, idUtente);  // Imposta l'idUtente nel prepared statement
            rs = pst.executeQuery();

            // Verifica se esiste un risultato e crea l'oggetto Insaccatore
            if (rs.next()) {
                java.sql.Date SQLdate = rs.getDate("DATANASCITA");
                java.time.LocalDate localeDate = null;

                if (SQLdate != null) {
                    localeDate = SQLdate.toLocalDate();
                }

                insaccatore = new Insaccatore(
                        rs.getInt("ID"),
                        rs.getString("EMAIL"),
                        rs.getString("NOME"),
                        rs.getString("COGNOME"),
                        rs.getString("LUOGONASCITA"),
                        localeDate,
                        rs.getString("CODICEFISCALE"),
                        rs.getString("RESIDENZA"),
                        rs.getDouble("ORELAVORO"),
                        rs.getString("PASSWORD"),
                        rs.getString("NICKNAME"),
                        rs.getString("RUOLO"),
                        rs.getInt("ORELIMITE"),
                        rs.getInt("LIMITECANC")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Chiusura delle risorse
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            ConnessioneDB.closeConnection(connection);
        }

        return insaccatore;  // Restituisci l'insaccatore o null se non trovato
    }
    
    


}





