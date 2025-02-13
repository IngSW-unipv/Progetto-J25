package modello.pubblicazionePanel;
import java.util.HashSet;


public class NotificaMessage implements INotifica {
	
	private HashSet <Utente> listaUtenti;
	private String message;
	
	
	public NotificaMessage(HashSet<Utente> listaUtenti, String message) {
		
		this.listaUtenti = new HashSet<>();
		this.message = message;
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
			u.aggiorna(message);
			
		}
		
	}

	
	public static void main(String[] args) {
		

	}
}
