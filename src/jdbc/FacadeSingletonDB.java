package jdbc;

import jdbc.bean.*;
import jdbc.dao.campione.CampioneDAO;
import jdbc.dao.campione.ICampioneDAO;
import jdbc.dao.documento.IOreLavoroDAO;
import jdbc.dao.documento.OreLavoroDAO;
import jdbc.dao.max.IMagazzinoDAO;
import jdbc.dao.max.MagazzinoDAO;
import jdbc.dao.analisi.IAnalisiDAO;
import jdbc.dao.analisi.AnalisiDAO;
import modello.archiviazioneCampione.SystemCampione;
import modello.autenticazione.SystemAutenticazione;
import modello.creazionePanel.Slot;
import modello.creazionePanel.Sondaggio;
import modello.creazionePanel.SystemPubblicazionePanel;
import modello.documento.SystemDocumento;

import modello.gestioneInventario.Inventario;
import modello.prenotazionePanel.SystemPrenotazione;
import modello.ConfermaPresenzaPanel.SystemPresenzaPanel;
import modello.analisiCampione.SystemAnalisi;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

public class FacadeSingletonDB {
    private static FacadeSingletonDB instance;

    private IPanelDAO panelDAO;
    private IMacchinarioDAO macchinarioDAO;
    private ISondaggioDAO sondaggioDAO;
    private ISlotDAO slotDAO;
    private IPrenotazionePanelDAO prenotazionePanelDAO;
    private IOreLavoroDAO oreLavoroDAO;
    private IUserDAO userDAO;
    private ICampioneDAO campioneDAO;
    private IOreLavoroDAO lavoroDAO;
    private IAnalisiDAO analisiDAO;
    private IMagazzinoDAO magazzinoDAO;

    private SystemPubblicazionePanel systemPubblicazionePanel;
    private SystemPrenotazione systemPrenotazione;
    private SystemCampione systemCampione;
    private SystemDocumento systemDocumento;
    private SystemAutenticazione systemAutenticazione;
    private SystemAnalisi systemAnalisi;
    private SystemPresenzaPanel systemPresenzaPanel;
    private Inventario inventario;

    private FacadeSingletonDB() {
        this.panelDAO = new PanelDAO();
        this.macchinarioDAO = new MacchinarioDAO();
        this.sondaggioDAO = new SondaggioDAO();
        this.slotDAO = new SlotDAO();
        this.prenotazionePanelDAO = new PrenotazionePanelDAO();
        this.userDAO = new UserDAO();
        this.campioneDAO = new CampioneDAO();
        this.oreLavoroDAO = new OreLavoroDAO();
        this.analisiDAO = new AnalisiDAO();
        this.magazzinoDAO = new MagazzinoDAO();
    }

    public static FacadeSingletonDB getInstance() {
        if (instance == null) {
            instance = new FacadeSingletonDB();
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
    
    public SystemDocumento getSystemDocumento() {
    	
    	if(systemDocumento == null) {
    		
    		systemDocumento = new SystemDocumento();
    	}
    	
    	return systemDocumento;
    }
    
    public Inventario getInventario() {
    	
    	if(inventario == null) {
    		
    		inventario = new Inventario();
    	}
    	
    	return inventario;
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
            systemPrenotazione.setPanelistas(userDAO.getPanelistas());
            systemPrenotazione.setPanels(panelDAO.getPanels());
        }
        return systemPrenotazione;
    }

    
    public void getPrenotati(Map<LocalTime, Slot> slots){
        prenotazionePanelDAO.getPrenotazioni(slots);
        oreLavoroDAO.getOreLavPrenotati(slots);
        //prelevo i prenot89/ati e poi setto le loro ore
    }

    public SystemAutenticazione getSystemAutenticazione() throws SQLException {
        if (systemAutenticazione == null) {
            systemAutenticazione = new SystemAutenticazione();
            systemAutenticazione.setUtenti(userDAO.getAllUtenti());
        }
        return systemAutenticazione;
    }

    public SystemAnalisi getSystemAnalisi(){
        if (systemAnalisi == null) {
            systemAnalisi = new SystemAnalisi();
        }
        return systemAnalisi;
    }

    public SystemPresenzaPanel getSystemPresenzaPanel() {
        if (systemPresenzaPanel == null) {
            systemPresenzaPanel = new SystemPresenzaPanel();
        }
        return systemPresenzaPanel;
    }


    public IOreLavoroDAO getLavoroDAO() {
		return lavoroDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
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

    public IAnalisiDAO getAnalisiDAO() {
        return analisiDAO;
    }

	public IMagazzinoDAO getMagazzinoDAO() {
		return magazzinoDAO;
	}

	

    

}
