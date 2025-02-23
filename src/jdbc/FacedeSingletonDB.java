package jdbc;

import jdbc.bean.*;
import jdbc.dao.campione.CampioneDAO;
import jdbc.dao.campione.ICampioneDAO;
import jdbc.dao.documento.IOreLavoroDAO;
import jdbc.dao.documento.OreLavoroDAO;
import modello.archiviazioneCampione.SystemCampione;
import modello.creazionePanel.Slot;
import modello.creazionePanel.Sondaggio;
import modello.creazionePanel.SystemPubblicazionePanel;
import modello.prenotazionePanel.SystemPrenotazione;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

public class FacedeSingletonDB {
    private static FacedeSingletonDB instance;

    private IPanelDAO panelDAO;
    private IMacchinarioDAO macchinarioDAO;
    private ISondaggioDAO sondaggioDAO;
    private ISlotDAO slotDAO;
    private IPrenotazionePanelDAO prenotazionePanelDAO;
    private IOreLavoroDAO oreLavoroDAO;
    private IUserDAO userDAO;
    private ICampioneDAO campioneDAO;

    private SystemPubblicazionePanel systemPubblicazionePanel;
    private SystemPrenotazione systemPrenotazione;
    private SystemCampione systemCampione;

    private FacedeSingletonDB() {
        this.panelDAO = new PanelDAO();
        this.macchinarioDAO = new MacchinarioDAO();
        this.sondaggioDAO = new SondaggioDAO();
        this.slotDAO = new SlotDAO();
        this.prenotazionePanelDAO = new PrenotazionePanelDAO();
        this.oreLavoroDAO = new OreLavoroDAO();
        this.userDAO = new UserDAO();
        this.campioneDAO = new CampioneDAO();
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
            systemPubblicazionePanel.setPanelisti(userDAO.getPanelistas());
        }
        return systemPubblicazionePanel;
    }
    
    public SystemCampione getSystemCampione() {
    	
    	if(systemCampione == null) {
    		
    		systemCampione = new SystemCampione();
    		systemCampione.setCampioniNonAnalizzati(campioneDAO.trovaCampioneNonAnalizzato());
    	}
    	
    	return systemCampione;
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

    public void getPrenotati(Map<LocalTime, Slot> slots){
        prenotazionePanelDAO.getPrenotazioni(slots);
        oreLavoroDAO.getOreLavPrenotati(slots);
        //prelevo i prenoati e poi setto le loro ore
    }
    
    
    public ICampioneDAO getCampioneDAO() {
		return campioneDAO;
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
    public IPrenotazionePanelDAO getPrenotazionePanelDAO() {
        return prenotazionePanelDAO;
    }
    public IOreLavoroDAO getOreLavoroDAO() {
        return oreLavoroDAO;
    }




}
