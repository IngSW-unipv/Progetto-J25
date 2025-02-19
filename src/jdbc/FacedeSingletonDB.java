package jdbc;

import jdbc.dao.IMacchinarioDAO;
import jdbc.dao.IPanelDAO;
import jdbc.dao.MacchinarioDAO;
import jdbc.dao.PanelDAO;
import modello.creazionePanel.SystemPubblicazionePanel;

public class FacedeSingletonDB {
    private static FacedeSingletonDB instance;

    private IPanelDAO panelDAO;
    private IMacchinarioDAO macchinarioDAO;

    private SystemPubblicazionePanel systemPubblicazionePanel;

    private FacedeSingletonDB() {
        this.panelDAO = new PanelDAO();
        this.macchinarioDAO = new MacchinarioDAO();
    }

    public static FacedeSingletonDB getInstance() {
        if (instance == null) {
            instance = new FacedeSingletonDB();
        }
        return instance;
    }

    public SystemPubblicazionePanel getSystemPubblicazionePanel() {
        if (systemPubblicazionePanel == null) {
            systemPubblicazionePanel = new SystemPubblicazionePanel();
            systemPubblicazionePanel.setMacchinari(macchinarioDAO.getMacchinari());
            systemPubblicazionePanel.setPanelisti(); //va ancora creata la classe DAO che si occupa del prelievo dei dati del panelista
        }
        return systemPubblicazionePanel;
    }
    public IPanelDAO getPanelDAO() {
        return panelDAO;
    }
    public IMacchinarioDAO getMacchinarioDAO() {
        return macchinarioDAO;
    }




}
