package modello;

import modello.email.IObserver;
import static modello.email.EmailSender.sendEmail;

public class Utente implements IObserver {
	private String email;

	public Utente(String email) {
		this.email = email;
	}

	@Override
	public void aggiorna(String subject, String body) {
		sendEmail(email,subject, body);
	}




}
