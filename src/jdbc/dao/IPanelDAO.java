package jdbc.dao;

import modello.creazionePanel.Panel;

import java.time.LocalTime;
import java.util.ArrayList;

public interface IPanelDAO {
    ArrayList<Panel> getPanels();
    boolean chiudiPanel(int panelId, LocalTime orarioFine);
    boolean addPanel(Panel panel);
    boolean rimuoviUtenteDaPanel(int panelId, String emailUtente);
    boolean aggiungiUtenteAlPanel(int panelId, String emailUtente);
    //boolean removePanel(Panel panel);
   // boolean updatePanel(Panel panel);
}
