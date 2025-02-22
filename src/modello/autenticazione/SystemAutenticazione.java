package modello.autenticazione;
import jdbc.bean.UserDAO;
import modello.Utente;
import modello.email.NotificaAttivazione;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

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


    }




