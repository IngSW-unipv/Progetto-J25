package modello.pubblicazionePanel;

public class Utente implements IObserver {

	@Override
	public void aggiorna(String message) {
		System.out.println(message);
		
	}

}
