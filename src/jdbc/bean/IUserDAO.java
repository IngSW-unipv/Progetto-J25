package jdbc.bean;

import java.sql.SQLException;
import java.time.LocalDate;

import modello.Panelista;

public interface IUserDAO {
	
	 public boolean controlloEmail(String emailInput) throws SQLException;
	 public void registraUtente(String emailInput, String passwordInput, String luogoNascitaInput, LocalDate dataNascitaInput,String codiceFiscaleInput,
             String residenzaInput, String nicknameInput) throws SQLException;
	 public Panelista selectPanelista(int id);
}
