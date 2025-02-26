package controller;

import jdbc.FacedeSingletonDB;
import modello.Panelista;
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

    public boolean prenotaUtente(int idPanel, Panelista p){
        return systemPrenotazione.prenotazionePanel(idPanel, p);
    }
}
