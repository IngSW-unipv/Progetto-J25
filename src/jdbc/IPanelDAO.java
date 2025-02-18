package jdbc;

import modello.creazionePanel.Panel;

import java.util.ArrayList;

public interface IPanelDAO {
    ArrayList<Panel> getPanels();
    boolean addPanel(Panel panel);
    boolean removePanel(Panel panel);
    boolean updatePanel(Panel panel);
}
