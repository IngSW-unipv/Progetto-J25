package modello.autenticazione;

import jdbc.FacadeSingletonDB;
import modello.Utente;
import modello.email.NotificaAttivazione;
import view.autenticazione.HomeInsaccatore;
import view.autenticazione.HomePanelista;
import view.autenticazione.HomePanelLeader;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class SystemAutenticazione {
    private NotificaAttivazione notificaAttivazione;
    private String codiceAttivazione;
    private ArrayList<Utente> utenti;

    public SystemAutenticazione() {
        utenti = new ArrayList<>();
    }
    public ArrayList<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(ArrayList<Utente> utenti) {
        this.utenti = utenti;
    }

    private String generaCodiceAttivazione(){
        Random rand = new Random();
        int codice = 10000 + rand.nextInt(900000);
        return String.valueOf(codice);
    }

    private void inviaEmailAttivazione(String email, String codiceAttivazione) {
        String subject = "Codice di attivazione";
        String message = "Ciao, il tuo codice di attivazione è: " + codiceAttivazione;
        Utente utente = new Utente(0, email, null , null, null, null, null, null, null,
                null, null);
        notificaAttivazione = new NotificaAttivazione(subject, message, utente);
        notificaAttivazione.notificaObserver();
        System.out.println("Email inviata a " + email);
    }

    public boolean controlloEmail(String emailInput) {
        for (Utente utente : utenti) {
            if(emailInput.equals(utente.getEmail())){
                System.out.println("Email inviata a " + utente.getEmail());
                return true;}}
                System.out.println("Email non trovata");
            return false;
    }

    public boolean attivazioneEmail(String emailInput) throws SQLException {
        if(controlloEmail(emailInput)){
            codiceAttivazione = generaCodiceAttivazione();
            inviaEmailAttivazione(emailInput, codiceAttivazione);
            return true;
        } else {
            System.out.println("Email non trovata");
        }
    return false; }

    public boolean inizioRegistrazione(String codiceInput){
        if(codiceAttivazione.equals(codiceInput)){
            return true;
        } else {
        }
        return false;
    } // tramite interfaccia grafica

    public void registrazioneUtente(String emailInput, String passwordInput, String luogoNascitaInput, LocalDate dataNascitaInput, String codiceFiscaleInput,
                                    String residenzaInput, String nicknameInput) throws SQLException {
            FacadeSingletonDB.getInstance().getUserDAO().registraUtente(emailInput,passwordInput,luogoNascitaInput, dataNascitaInput, codiceFiscaleInput, residenzaInput, nicknameInput);
            Utente u = trovaUtentePerEmail(emailInput);
        String[] emailParts = emailInput.split("@")[0].split("\\.");
        String nome = emailParts[0];
        String cognome = emailParts[1].substring(0, emailParts[1].length()-2);
        String ruolo = emailParts[1].substring(emailParts[1].length()-2);
        u.setNome(nome);
        u.setCognome(cognome);
        u.setRuolo(ruolo);
        u.setPassword(passwordInput);
        u.setLuogoNascita(luogoNascitaInput);
        u.setDataNascita(dataNascitaInput);
        u.setCodiceFiscale(codiceFiscaleInput);
        u.setResidenza(residenzaInput);
        u.setNickname(nicknameInput);

// quando ho tutti i dati devo chiamare la facade che da la reference allo user dao e fa il salvataggio dei dati
        }
    public Utente trovaUtentePerEmail(String email) {
        for (Utente utente : utenti) {
            if (utente.getEmail().equals(email)) {
                return utente;
            }
        }
        return null; // Nessun utente trovato
    }

  /*  public String controllaPassword(String passwordInput) {
        if (passwordInput.length() < 8) {
            return "La password deve essere di almeno 8 caratteri.";
        } if(!passwordInput.matches(".*[A-Z].*")){
            return "La password deve contenere almeno una lettera maiuscola.";

        } if (!passwordInput.matches(".*[a-z].*")) {
            return "La password deve contenere almeno una lettera minuscola.";

        } if(!passwordInput.matches(".*[0-9].*")){
            return "La password deve contenere almeno un numero.";

        } if(!passwordInput.matches(".*[!@#\\$%\\^&\\*]")){
            return "La password deve contenere almeno un carattere speciale (es. !@#$%^&*).";
        }
        return "VALIDA";

    } */

    public boolean controllaPassword(String passwordInput) {
        if ((passwordInput.length() < 8) || (!passwordInput.matches(".*[A-Z].*")) || (!passwordInput.matches(".*[a-z].*")) || (!passwordInput.matches(".*[0-9].*")) || (!passwordInput.matches(".*[!@#\\$%\\^&\\*]"))){
            return false;
        }
        return true; }

            public Utente controlloLogin(String emailInputOrNickname, String passwordInput) {
        for (Utente utente : utenti) {
            if((emailInputOrNickname.equals(utente.getEmail()) || emailInputOrNickname.equals(utente.getNickname())) && passwordInput.equals(utente.getPassword())){
                return utente;
            } else {
            }
        }   return null;
    }

    public String getRuolo (String emailInputOrNickname, String passwordInput) {
        Utente utente = controlloLogin(emailInputOrNickname, passwordInput);
        if(utente == null){
            return null;
        }
       String ruolo = utente.getRuolo();
        return ruolo;

    }

   // scrivere get ruolo nel controller per aprire l'interfaccia grafica giusta, e creo le interfacce nel controllo




   // utenteLoggato = controlloLogin(utenteLoggato.getEmail(), passwordInput);
    public boolean modificaPassword(Utente utenteLoggato, String vecchiaPasswordInput, String nuovaPasswordInput) throws SQLException {

           if(utenteLoggato.getPassword().equals(vecchiaPasswordInput)){
               utenteLoggato.setPassword(nuovaPasswordInput);
               FacadeSingletonDB.getInstance().getUserDAO().cambiaPassword(utenteLoggato, nuovaPasswordInput);

               return true;
           }

        return false;
    }

    public boolean cambiaRuolo(int id, String ruolo) throws SQLException {
        Utente utenteDaModificare = null;
        for(Utente u : utenti) {
            if(u.getId() == id){
                utenteDaModificare = u;
                break;
            }
        }
        if(utenteDaModificare != null){
            utenteDaModificare.setRuolo(ruolo);
            FacadeSingletonDB.getInstance().getUserDAO().updateRuolo(id,ruolo);
            return true;
        }

        return false;
    }


public boolean inserisciIban(String iban, Utente utenteLoggato ) throws SQLException {
    if(!(utenteLoggato == null)){
        int userId = utenteLoggato.getId();
        FacadeSingletonDB.getInstance().getUserDAO().inserisciIban(userId,iban);
         return true;
               } return false;

}

public boolean recuperaCredenziali(String email) {
        Utente utente = trovaUtentePerEmail(email);
        if (utente != null) {
            NotificaAttivazione notificaAttivazione1 = new NotificaAttivazione("Recupero credenziali", "Le tue credenziali sono :\nUsername: " + utente.getNickname() + "\nPassword:" + utente.getPassword(), utente);
           notificaAttivazione1.notificaObserver();
            return true;
        }
        return false;
}

}

































