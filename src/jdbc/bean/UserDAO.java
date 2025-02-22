package jdbc.bean;
import jdbc.ConnessioneDB;
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

  @Override  
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
        String query = "SELECT * FROM UTENTE WHERE RUOLO IN ('op', 'insaccatore')";

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

}






