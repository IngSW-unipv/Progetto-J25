package modello.prenotazionePanel;

import modello.Panelista;
import modello.creazionePanel.*;
import java.util.*;

public class SystemPrenotazione {
	
	ArrayList <Sondaggio> sondaggi;

	public SystemPrenotazione() {
		
		sondaggi = new ArrayList<>();
		
	}
	
	
	public void prenotazione(Slot s, Panelista p) {
		
		
		s.aggiungiPrenotato(p);
		
		/*questo metodo aggiunge alla lista di slot
		 * le persone che si sono prenotate
		 */
	
	}
	
	public void cancellazione(Slot s, Panelista p) {
		
		s.rimuoviPrenotato(p);
		
		/*questo metodo toglie dalla lista di slot
		 * le persone che si sono prenotate ma che
		 * hanno disdetto 
		 */
		
	}
	
	public void prenotati(Slot s) {
		
		for(Panelista p: s.getPrenotati()) {
			
			System.out.println(p.getNome());
		}
		
		/*questo metodo restituisce il nome delle persone
		 * che si sono prenotate, nell'app ci sarà una sezione
		 * apposita dove si potranno visualizzare i nomi
		 */
		
	}
	
	public int numeroPrenotati(Slot s) {
		
		if(s.getPrenotati().size() < 0) {
			
			throw new IllegalArgumentException("Errore");
		}
		
		return s.getPrenotati().size();
		/*questo metodo restituisce il numero
		 * delle persone che si sono prenotate
		 */
	}
	
	
}
