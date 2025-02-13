package modello.email;

import java.util.ArrayList;
import java.util.HashSet;

import modello.Utente;


public class NotificaMessage implements INotifica {
	
	private ArrayList<Utente> listaUtenti;
	private String subject;
	private String body;
	
	
	public NotificaMessage(ArrayList<Utente> listaUtenti, String subject, String body) {
		this.listaUtenti = listaUtenti;
		this.subject = subject;
		this.body = body;
	}

	@Override
	public void notificaObserver() {
		
		for(Utente u : listaUtenti) {
			/*
			 * in realtà io dovre avere una lista prenotati
			 * quindi magari la classe utente avrà un attributo prenotato
			 * quando questo parametro è true allora posso creare una lista 
			 * di prenotati. perchè con la lista che ho adesso notifico tutti 
			 * gli utenti, non solo quelli prenotati, che è stata pubblicata
			 * la prenotazione
			 */
			u.aggiorna(subject, body);
		}
		
	}


}
