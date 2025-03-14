package modello.prenotazionePanel;

import jdbc.FacadeSingletonDB;
import modello.Panelista;
import modello.Utente;
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


	public void prenotazione(int idSondaggio, LocalTime orarioSlot, Panelista panelista) {
		Sondaggio s = trovaSondaggioPerId(idSondaggio);
		Slot slot = s.getSlots().get(orarioSlot);
		FacadeSingletonDB.getInstance().getPrenotazionePanelDAO().salvaPrenotazione(slot, panelista);
	}
	
	public boolean cancellazioneDaPanel(int idPanel, Utente utente) {
		boolean statoCancellazione = FacadeSingletonDB.getInstance().getPanelDAO().rimuoviUtenteDaPanel(idPanel, utente.getEmail());
		Panel panel = trovaPanelPerId(idPanel);

		if(statoCancellazione = true){
			String text = "Si è liberato un posto ad un panel il seguente giorno: " +
					panel.getData()+" alla seguente ora: " + panel.getOrarioInizio();
			NotificaMessage notifica = new NotificaMessage( "Cancellazione panel", text);
			// devo gestire il caricamento del sondaggio
			notifica.setListaUtenti(panelistas);
			notifica.notificaObserver();
		}
		return statoCancellazione;
		
	}

	public boolean prenotazionePanel(int idPanel, Utente p) {
		return FacadeSingletonDB.getInstance().getPanelDAO().aggiungiUtenteAlPanel(idPanel, p.getEmail());
	}


	public ArrayList<Integer> getIdPanelsAttivi() {
		return FacadeSingletonDB.getInstance().getPanelDAO().getIdPanelsAttivi();
	}

	public ArrayList<String> getPanelisti(int panelId) {
		return FacadeSingletonDB.getInstance().getPanelDAO().getPanelisti(panelId);
	}


	
	
}
