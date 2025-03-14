package controller;

import jdbc.FacadeSingletonDB;
import modello.Panelista;
import modello.Utente;
import modello.creazionePanel.Panel;
import modello.prenotazionePanel.SystemPrenotazione;

import java.util.ArrayList;

public class PanelController {
    private SystemPrenotazione systemPrenotazione;

    public PanelController() {
        this.systemPrenotazione = FacadeSingletonDB.getInstance().getSystemPrenotazione();
    }

    public ArrayList<Panel> getPanels(){
        return systemPrenotazione.getPanels();
    }

    public boolean prenotaUtente(int idPanel, Panelista p){
        return systemPrenotazione.prenotazionePanel(idPanel, p);
    }

    public boolean cancellaUtente(int idPanel, Utente p){
        return systemPrenotazione.cancellazioneDaPanel(idPanel, p);
    }

    public ArrayList<Integer> getIdPanelsAttivi() {
        return systemPrenotazione.getIdPanelsAttivi();
    }

    public ArrayList<String> getPanelisti(int panelId) {
        return systemPrenotazione.getPanelisti(panelId);
    }   
}
