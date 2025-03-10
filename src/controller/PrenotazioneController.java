package controller;

import java.time.LocalTime;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import jdbc.FacadeSingletonDB;
import modello.Panelista;
import modello.Utente;
import modello.creazionePanel.Sondaggio;
import modello.prenotazionePanel.SystemPrenotazione;
import view.prenotazionePanel.PrenotazioneView;

public class PrenotazioneController {
	
	SystemPrenotazione system;
	//private PrenotazioneView view;
	
	public PrenotazioneController() {
		
		system = FacadeSingletonDB.getInstance().getSystemPrenotazione();
		//this.view = view;
	}
	
	 public void prenotaSondaggio(int idSondaggio, LocalTime orarioSlot, Panelista panelista) {
	        try {
	        	
	        	system.prenotazione(idSondaggio, orarioSlot, panelista);
	        	JOptionPane.showMessageDialog(null, "Prenotazione effettuata con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
	        }catch (Exception ex) {
	            // In caso di errore, mostra un messaggio d'errore
	            JOptionPane.showMessageDialog(null,
	                    "Errore durante la prenotazione: " + ex.getMessage(),
	                    "Errore",
	                    JOptionPane.ERROR_MESSAGE);
	        }
	
	 }

	public SystemPrenotazione getSystem() {
		return system;
	}

	
	 
	 
	 
}
