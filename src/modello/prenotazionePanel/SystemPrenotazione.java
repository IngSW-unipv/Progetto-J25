package modello.prenotazionePanel;

import java.time.LocalDate;

import modello.creazionePanel.Slot;

import java.util.*;

import modello.Panelista;

public class SystemPrenotazione {

	public SystemPrenotazione() {
		
		
	}
	
	
	public void prenotazione(Slot s) {
		
		LocalDate ld = s.getData();
		ArrayList <Panelista> prenotati= s.getPrenotati();
		
		
		//mi serve il nome del panelista che si è prenotato
		//ma per far ciò ho bisogno di fare query al database
		//oppure aggiungere un metodo in slot che permetta,
		//per ora, di aggiungere panelisti all?attributo prenotati
	}
	
	
}
