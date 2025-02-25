package controller;

import jdbc.FacedeSingletonDB;
import modello.Utente;
import modello.autenticazione.SystemAutenticazione;

import java.sql.SQLException;
import java.time.LocalDate;

public class AutenticazioneController{
private SystemAutenticazione systemAutenticazione;
    public AutenticazioneController() throws SQLException {
        this.systemAutenticazione= FacedeSingletonDB.getInstance().getSystemAutenticazione();
    }

public boolean attivaEmail(String email) throws SQLException {
        boolean attivo = systemAutenticazione.attivazioneEmail(email);
        return attivo;
}

public boolean verificaCodice(String codice) throws SQLException {
        return systemAutenticazione.inizioRegistrazione(codice);
}

public void registraUtente(String emailInput, String passwordInput, String luogoNascitaInput, LocalDate dataNascitaInput, String codiceFiscaleInput,
                           String residenzaInput, String nicknameInput) throws SQLException {
        systemAutenticazione.registrazioneUtente(emailInput,passwordInput,luogoNascitaInput, dataNascitaInput, codiceFiscaleInput,
                 residenzaInput,  nicknameInput);
}

public boolean verificaPassword(String passwordInput) throws SQLException {
     return systemAutenticazione.controllaPassword(passwordInput);
}

public Utente login(String emailOrNickname, String password) throws SQLException {
     Utente utente = systemAutenticazione.controlloLogin(emailOrNickname, password);
return utente;
}

//public void interfacciaRuolo(String emailOrNickname, String password){
  //  systemAutenticazione.apriInterfacciaPerRuolo(emailOrNickname, password);

//}
public boolean modificaPassword(String vecchiaPassword, String nuovaPassword,Utente utenteLoggato) throws SQLException{

   return systemAutenticazione.modificaPassword(utenteLoggato, vecchiaPassword, nuovaPassword);
}

public boolean cambioRuolo(int idUtente, String nuovoRuolo) throws SQLException {
        return systemAutenticazione.cambiaRuolo(idUtente, nuovoRuolo);

}
public boolean inserisciIban(String iban, Utente utenteLoggato) throws SQLException {
       return systemAutenticazione.inserisciIban(iban, utenteLoggato);
}
}


