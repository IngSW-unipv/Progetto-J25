package modello.email;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import modello.Utente;


public class NotificaMessage implements INotifica {
	
	private ArrayList<Utente> listaUtenti;
	private String subject;
	private String body;
	
	
	public NotificaMessage(String subject, String body) {
		this.listaUtenti = new ArrayList<>();
		this.subject = subject;
		this.body = body;
	}

	public void setListaUtenti(ArrayList<Utente> listaUtenti) {
		this.listaUtenti = listaUtenti;
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
