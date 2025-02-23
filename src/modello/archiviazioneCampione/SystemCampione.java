package modello.archiviazioneCampione;
import java.time.LocalDate;

import java.util.ArrayList;

import jdbc.FacedeSingletonDB;
import jdbc.dao.campione.CampioneDAO;
import jdbc.dao.campione.ICampioneDAO;
import modello.archiviazioneCampione.*;

public class SystemCampione {
	
	ArrayList<Integer> campioniNonAnalizzati; //come argomento ha gli integer perchè prendo solo gli id

	
	public SystemCampione() {
		
		this.campioniNonAnalizzati = new ArrayList<>();
		
	}
	
	
	public ArrayList<Integer> getCampioniNonAnalizzati() {
		return campioniNonAnalizzati; //è utile questo metodo?
	}


	public void setCampioniNonAnalizzati(ArrayList<Integer> campioniNonAnalizzati) {
		this.campioniNonAnalizzati = campioniNonAnalizzati;
	}


	public boolean registraCampione(int id, String stato, LocalDate ld) {
		
		
		return FacedeSingletonDB.getInstance().getCampioneDAO().insertCampione(id, stato, ld);
		
	}
	
	public Campione trovaCampione(int id) {
		
		return FacedeSingletonDB.getInstance().getCampioneDAO().trovaCampionePerId(id);
	}
	
	
	public ArrayList<Campione> selezionaCampioni(){
		
		return FacedeSingletonDB.getInstance().getCampioneDAO().selectAllCampioni();
	}
	
	public boolean aggiornaCampione(int id, String stato) {
		
		return FacedeSingletonDB.getInstance().getCampioneDAO().updateCampione(id, stato);
	}
	
	public boolean deleteCampione(int id) {
		
		return FacedeSingletonDB.getInstance().getCampioneDAO().eliminaCampione(id);
	}
	
	public ArrayList<Integer> campioniNonAnalizzati(){
		
		return FacedeSingletonDB.getInstance().getCampioneDAO().trovaCampioneNonAnalizzato(); //serve ancora?
	}
	
	
	

}
