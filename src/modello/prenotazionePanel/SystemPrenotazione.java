package modello.prenotazionePanel;

import jdbc.FacedeSingletonDB;
import modello.Panelista;
import modello.creazionePanel.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import modello.email.NotificaMessage;
import modello.Utente;


public class SystemPrenotazione {
	ArrayList <Sondaggio> sondaggi;
	ArrayList <Panelista> panelisti;

	public SystemPrenotazione() {
		sondaggi = new ArrayList<>();
		panelisti = new ArrayList<>();
	}

	public void setSondaggi(ArrayList<Sondaggio> sondaggi) {
		this.sondaggi = sondaggi;
	}

	public Sondaggio trovaSondaggioPerId(int id) {
		for (Sondaggio s : sondaggi) {
			if (s.getId() == id) {
				return s; // Ritorna il sondaggio trovato
			}
		}
		return null; // Nessun sondaggio trovato
	}


	public void prenotazione(int idSondaggio, LocalTime orarioSlot, Panelista p) {
		Sondaggio s = trovaSondaggioPerId(idSondaggio);
		Slot slot = s.getSlots().get(orarioSlot);
		FacedeSingletonDB.getInstance().getPrenotazionePanelDAO().salvaPrenotazione(slot, p);
	}
	
	public void cancellazione(Panel panel, Panelista p) {
		FacedeSingletonDB.getInstance().getPanelDAO().rimuoviUtenteDaPanel(panel.getId(), p.getEmail());
		
		/*s.rimuoviPrenotato(p);*/
		
		String text = "Si è liberato un posto ad un panel il seguente giorno: " +panel.getData()+" alla seguente ora: " +panel.getOrarioInizio();
		NotificaMessage notifica = new NotificaMessage( "Cancellazione panel", text);
		
		notifica.setListaUtenti(panelisti);
        notifica.notificaObserver();
		
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
	
	
	
	public ArrayList<Panelista> getPanelisti() {
		return panelisti;
	}

	public void setPanelisti(ArrayList<Panelista> panelisti) {
		this.panelisti = panelisti;
	}

	/*public static void main (String[] args) {
		
		LocalDate ld = LocalDate.of(2025, 02, 19);
		LocalTime lt = LocalTime.of(14, 0);
		
		Slot s = new Slot(ld, lt);
		Panelista p = new Panelista("tommaso.ghisolfi003@gmail.com", "Tommaso", "Ghisolfi", "Broni", ld, "GG", "gg", 10);
		Panelista k = new Panelista("khawlaouaadou1@gmail.com", "Tommaso", "Ghisolfi", "Broni", ld, "GG", "gg", 10);
		
		ArrayList <Panelista> panelisti = new ArrayList<>();
		panelisti.add(p);
		panelisti.add(k);
		
       
		SystemPrenotazione sys = new SystemPrenotazione();
		sys.setPanelisti(panelisti);
		
		sys.cancellazione(s, p);
		
	} */
	
	
}
