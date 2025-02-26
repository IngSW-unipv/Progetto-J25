package modello.prenotazionePanel;

import jdbc.FacedeSingletonDB;
import modello.Panelista;
import modello.Utente;
import modello.creazionePanel.*;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import modello.email.NotificaMessage;


public class SystemPrenotazione {
	ArrayList <Sondaggio> sondaggi;
	ArrayList <Panel> panels;
	ArrayList <Panelista> panelistas;
	private final int oreLimite = 10;

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
		FacedeSingletonDB.getInstance().getPrenotazionePanelDAO().salvaPrenotazione(slot, panelista);
	}
	
	public boolean cancellazioneDaPanel(int idPanel, Utente utente) {
		Panel panel = trovaPanelPerId(idPanel);
		LocalDateTime dataOra = panel.getData().atTime(panel.getOrarioInizio());
		if (LocalDateTime.now().isAfter(dataOra.minusHours(oreLimite))) {
			return false; // Non è più possibile cancellarsi
		}
		boolean statoCancellazione = FacedeSingletonDB.getInstance().getPanelDAO().rimuoviUtenteDaPanel(idPanel, utente.getEmail());
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

	public boolean prenotazionePanel(int idPanel, Panelista p) {
		return FacedeSingletonDB.getInstance().getPanelDAO().aggiungiUtenteAlPanel(idPanel, p.getEmail());
	}


	
	
}
