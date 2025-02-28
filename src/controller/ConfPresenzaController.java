package controller;

import java.time.LocalTime;
import java.util.Map;

import modello.ConfermaPresenzaPanel.SystemPresenzaPanel;

public class ConfPresenzaController {

    private SystemPresenzaPanel systemPresenzaPanel;

    public ConfPresenzaController() {
        this.systemPresenzaPanel = new SystemPresenzaPanel();
    }

    public boolean presenzaEChiusuraPanel(int panelId, Map<Integer, Boolean> presenze, LocalTime orarioFine) {
        return systemPresenzaPanel.presenzaEChiusuraPanel(panelId, presenze, orarioFine);
    }

    public int getPanelistaIdByEmail (String email) {
        return systemPresenzaPanel.getPanelistaIdByEmail(email);
    }
    
}
