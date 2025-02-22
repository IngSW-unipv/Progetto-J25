package modello.autenticazione;
import jdbc.bean.UserDAO;
import modello.Utente;
import modello.email.NotificaAttivazione;
import view.autenticazione.HomeInsaccatore;
import view.autenticazione.HomeOperatore;
import view.autenticazione.HomePanelLeader;
import view.autenticazione.HomePanelLeader;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemAutenticazione {
    private UserDAO userDAO;
    private NotificaAttivazione notificaAttivazione;
    private String codiceAttivazione;

    public SystemAutenticazione(UserDAO userDAO) {

        this.userDAO = userDAO;
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

    public void attivazioneEmail(String emailInput) throws SQLException {
           if(userDAO.controlloEmail(emailInput)){
               codiceAttivazione = generaCodiceAttivazione();
               inviaEmailAttivazione(emailInput, codiceAttivazione);
    } else {
               System.out.println("Email non trovata");
           } }

           public boolean inizioRegistrazione(String codiceInput){
             if(codiceAttivazione.equals(codiceInput)){
                 System.out.println("La registrazione può iniziare");
                 return true;
             } else {
                 System.out.println("Codice di attivazione errato");
             }
            return false;
        }

        public void registrazioneUtente(String codiceInput,String emailInput, String passwordInput, String luogoNascitaInput, LocalDate dataNascitaInput,String codiceFiscaleInput,
                                        String residenzaInput, String nicknameInput) throws SQLException {
        if(inizioRegistrazione(codiceInput)){
            userDAO.registraUtente(emailInput,passwordInput,luogoNascitaInput, dataNascitaInput, codiceFiscaleInput, residenzaInput, nicknameInput);

        } }

       public String controllaPassword(String passwordInput) {
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

       }


    //public boolean login(String usernameOrEmail, String passwordInput) throws SQLException {
    //userDAO.controlloLogin(usernameOrEmail, passwordInput);
        public void login(String emailOrNickname, String password) {
            try {
                String ruolo = userDAO.controlloLogin(emailOrNickname, password);

                if (ruolo != null) {
                    System.out.println("Login riuscito!");
                    reindirizzaUtente(ruolo); // Reindirizza in base al ruolo
                } else {
                    JOptionPane.showMessageDialog(null, "Login fallito! Credenziali non valide.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void reindirizzaUtente(String ruolo) {
        if (ruolo.equals("pl")) {
            new HomePanelLeader(); // Apre la home del panelista
        } else if (ruolo.equals("op")) {
            new HomeOperatore(); // Apre la home dell'operatore
        } else if (ruolo.equals("in")) {
            new HomeInsaccatore(); // Apre la home dell'insaccatore
        } else {
            JOptionPane.showMessageDialog(null, "Ruolo non riconosciuto!");
        }
    }}