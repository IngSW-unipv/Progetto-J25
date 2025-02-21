package modello.email;

import modello.Utente;

public class NotificaAttivazione implements INotifica{
    private String subject;
    private String message;
    private Utente utente;
    public NotificaAttivazione(String subject, String message, Utente utente) {
        this.subject = subject;
        this.message = message;
        this.utente = utente;
    }

    @Override
    public void notificaObserver() {
        this.utente.aggiorna(subject, message);
    }
}
