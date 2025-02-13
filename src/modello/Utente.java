package modello;

import modello.prenotazionePanel.IObserver;

public class Utente implements IObserver {

	@Override
	public void aggiorna(String message) {
		System.out.println(message);
		
	}

}
