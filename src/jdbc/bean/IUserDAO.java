package jdbc.bean;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import modello.Insaccatore;
import modello.Panelista;
import modello.Utente;

public interface IUserDAO {
	
/*	boolean controlloEmail(String emailInput) throws SQLException; */
	void registraUtente(String emailInput, String passwordInput, String luogoNascitaInput, LocalDate dataNascitaInput,String codiceFiscaleInput,
						String residenzaInput, String nicknameInput) throws SQLException;
	Panelista selectPanelista(int id);
	ArrayList<Panelista> getPanelistas();
	ArrayList<Utente> getAllUtenti() throws SQLException;
	void cambiaPassword(Utente utente, String passwordInput) throws SQLException;
	boolean updateRuolo(int id, String ruolo) throws SQLException;
	boolean inserisciIban(int userId, String iban) throws SQLException;
	String getIban(Utente utente) throws SQLException;
	Insaccatore getInsaccatore(int idUtente) throws SQLException;
}
