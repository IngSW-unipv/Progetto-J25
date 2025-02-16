package modello;

import modello.email.IObserver;
import static modello.email.EmailSender.sendEmail;

public class Utente implements IObserver {
	private String email;
	private String nome;

	public Utente(String email, String nome) {
		this.email = email;
		this.nome = nome;
	}

	@Override
	public void aggiorna(String subject, String body) {
		sendEmail(email,subject, body);
	}

	public String getEmail() {
		return email;
	}



}
