package modello.archiviazioneCampione;
import java.time.LocalDate;

import java.util.ArrayList;

import jdbc.dao.campione.CampioneDAO;
import jdbc.dao.campione.ICampioneDAO;
import modello.archiviazioneCampione.*;

public class SystemCampione {
	
	ICampioneDAO campioneDAO;
	
	
	public SystemCampione(ICampioneDAO campioneDAO) {
		
		this.campioneDAO = campioneDAO;
		
	}
	
	
	public boolean registraCampione(int id, String stato, LocalDate ld) {
		
		
		return campioneDAO.insertCampione(id, stato, ld);
		
	}
	
	public Campione trovaCampione(int id) {
		
		return campioneDAO.trovaCampionePerId(id);
	}
	
	
	public ArrayList<Campione> selezionaCampioni(){
		
		return campioneDAO.selectAllCampioni();
	}
	
	public boolean aggiornaCampione(int id, String stato) {
		
		return campioneDAO.updateCampione(id, stato);
	}
	
	public boolean deleteCampione(int id) {
		
		return campioneDAO.eliminaCampione(id);
	}
	
	public ArrayList<Integer> campioniNonAnalizzati(){
		
		return campioneDAO.trovaCampioneNonAnalizzato();
	}
	
	
	

}
