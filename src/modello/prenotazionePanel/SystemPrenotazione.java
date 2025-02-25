package modello.prenotazionePanel;

import jdbc.FacedeSingletonDB;
import modello.Panelista;
import modello.creazionePanel.*;


import java.time.LocalTime;
import java.util.*;
import modello.email.NotificaMessage;


public class SystemPrenotazione {
	ArrayList <Sondaggio> sondaggi;
	ArrayList <Panel> panels;
	ArrayList <Panelista> panelistas;

	public SystemPrenotazione() {
		sondaggi = new ArrayList<>();
		panels = new ArrayList<>();
		panelistas = new ArrayList<>();
	}

	public ArrayList<Sondaggio> getSondaggi() {
		return sondaggi;
	}

	public void setSondaggi(ArrayList<Sondaggio> sondaggi) {
		this.sondaggi = sondaggi;
	}
	
	

	public ArrayList<Sondaggio> getSondaggi() {
		return sondaggi;
	}

	public  ArrayList<Panel> getPanels(){
		return panels;
	}

	public void setPanels(ArrayList<Panel> panels) {
		this.panels = panels;
	}

	public  ArrayList<Panelista> getPanelistas(){
		return panelistas;
	}

	public void setPanelistas(ArrayList<Panelista> panelistas) {
		this.panelistas = panelistas;
	}

	public Sondaggio trovaSondaggioPerId(int id) {
		for (Sondaggio s : sondaggi) {
			if (s.getId() == id) {
				return s; // Ritorna il sondaggio trovato
			}
		}
		return null; // Nessun sondaggio trovato
	}

	public Panel trovaPanelPerId(int id) {
		for (Panel p : panels) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}


	public void prenotazione(int idSondaggio, LocalTime orarioSlot, Panelista p) {
		Sondaggio s = trovaSondaggioPerId(idSondaggio);
		Slot slot = s.getSlots().get(orarioSlot);
		FacedeSingletonDB.getInstance().getPrenotazionePanelDAO().salvaPrenotazione(slot, p);
	}
	
	public void cancellazione(int idPanel, Panelista p) {
		FacedeSingletonDB.getInstance().getPanelDAO().rimuoviUtenteDaPanel(idPanel, p.getEmail());
		Panel panel = trovaPanelPerId(idPanel);

		String text = "Si è liberato un posto ad un panel il seguente giorno: " +
				panel.getData()+" alla seguente ora: " + panel.getOrarioInizio();
		NotificaMessage notifica = new NotificaMessage( "Cancellazione panel", text);
		
		notifica.setListaUtenti(panelistas);
        notifica.notificaObserver();

		
		/*questo metodo toglie dalla lista di slot
		 * le persone che si sono prenotate ma che
		 * hanno disdetto 
		 */
		
	}


	
	
}
