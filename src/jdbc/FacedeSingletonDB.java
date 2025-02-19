package jdbc;

import jdbc.dao.*;
import modello.creazionePanel.Sondaggio;
import modello.creazionePanel.SystemPubblicazionePanel;

import java.util.ArrayList;
import java.util.List;

public class FacedeSingletonDB {
    private static FacedeSingletonDB instance;

    private IPanelDAO panelDAO;
    private IMacchinarioDAO macchinarioDAO;
    private ISondaggioDAO sondaggioDAO;
    private ISlotDAO slotDAO;

    private SystemPubblicazionePanel systemPubblicazionePanel;

    private FacedeSingletonDB() {
        this.panelDAO = new PanelDAO();
        this.macchinarioDAO = new MacchinarioDAO();
        this.sondaggioDAO = new SondaggioDAO();
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
            //systemPubblicazionePanel.setPanelisti(); //va ancora creata la classe DAO che si occupa del prelievo dei dati del panelista
        }
        return systemPubblicazionePanel;
    }
    public List<Sondaggio> popolaSondaggi() {
        List<Sondaggio> sondaggi = new ArrayList<Sondaggio>();
        sondaggi = sondaggioDAO.selectAllSondaggi();
        for (Sondaggio s : sondaggi) {
            for(int i = 0; i< s.getSlotDisponili(); i++){
               /*devo crare il metodo per il prelievo degli slot di un sondaggio, faccio prima a passarli direttamente il sondaggio
                cosi prelevo id e il numero degli slot che devo cercare, cosi da non perder tempo una volta riempiota la lista.
                Magari sarebbe carino che quando setto un sondaggio a chiuso, posso eleminare anche gli slot
                che gli appartenevano, per risparmiare spazio.
                */
            }
        }
        return sondaggi;
    }
    public IPanelDAO getPanelDAO() {
        return panelDAO;
    }
    public IMacchinarioDAO getMacchinarioDAO() {
        return macchinarioDAO;
    }
    public ISondaggioDAO getSondaggioDAO() {
        return sondaggioDAO;
    }




}
