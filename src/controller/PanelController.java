package controller;

import jdbc.FacedeSingletonDB;
import modello.Panelista;
import modello.Utente;
import modello.creazionePanel.Panel;
import modello.prenotazionePanel.SystemPrenotazione;

import java.util.ArrayList;

public class PanelController {
    private SystemPrenotazione systemPrenotazione;

    public PanelController() {
        this.systemPrenotazione = FacedeSingletonDB.getInstance().getSystemPrenotazione();
    }

    public ArrayList<Panel> getPanels(){
        return systemPrenotazione.getPanels();
    }

    public boolean prenotaUtente(int idPanel, Utente p){
        return systemPrenotazione.prenotazionePanel(idPanel, p);
    }

    public boolean cancellaUtente(int idPanel, Utente p){
        return systemPrenotazione.cancellazioneDaPanel(idPanel, p);
    }
}
