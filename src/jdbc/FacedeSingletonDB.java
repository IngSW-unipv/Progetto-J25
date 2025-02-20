package jdbc;

import jdbc.dao.*;
import modello.creazionePanel.Slot;
import modello.creazionePanel.Sondaggio;
import modello.creazionePanel.SystemPubblicazionePanel;
import modello.prenotazionePanel.SystemPrenotazione;

import java.util.ArrayList;
import java.util.List;

public class FacedeSingletonDB {
    private static FacedeSingletonDB instance;

    private IPanelDAO panelDAO;
    private IMacchinarioDAO macchinarioDAO;
    private ISondaggioDAO sondaggioDAO;
    private ISlotDAO slotDAO;

    private SystemPubblicazionePanel systemPubblicazionePanel;
    private SystemPrenotazione systemPrenotazione;

    private FacedeSingletonDB() {
        this.panelDAO = new PanelDAO();
        this.macchinarioDAO = new MacchinarioDAO();
        this.sondaggioDAO = new SondaggioDAO();
        this.slotDAO = new SlotDAO();
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
            //systemPubblicazionePanel.setPanelisti(); //va ancora creata la classe DAO che si occupa del prelievo dei dati del panelista
        }
        return systemPubblicazionePanel;
    }
    public ArrayList<Sondaggio> popolaSondaggi() {
        ArrayList<Sondaggio> sondaggi = sondaggioDAO.selectAllSondaggi();
        for (Sondaggio s : sondaggi) {
           ArrayList<Slot> slots = slotDAO.getSlots(s);
            if (slots == null || slots.isEmpty()) {
                System.out.println("Nessun slot trovato per il sondaggio ID: " + s.getId());
                continue;
            }
           for(Slot slot : slots) {
               s.aggiungiSlot(slot.getTime(), slot);
               System.out.println("tutto ok");
           }
        }
        return sondaggi;
    }

    public SystemPrenotazione getSystemPrenotazione() {
        if (systemPrenotazione == null) {
            systemPrenotazione = new SystemPrenotazione();
            systemPrenotazione.setSondaggi(popolaSondaggi());
        }
        return systemPrenotazione;
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
    public ISlotDAO getSlotDAO() {
        return slotDAO;
    }




}
