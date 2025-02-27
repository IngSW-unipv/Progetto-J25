package modello.archiviazioneCampione;
import java.time.LocalDate;

import java.util.ArrayList;

import jdbc.FacadeSingletonDB;

public class SystemCampione {
	
	ArrayList<Integer> campioniNonAnalizzati; //come argomento ha gli integer perchè prendo solo gli id

	
	public SystemCampione() {
		
		this.campioniNonAnalizzati = new ArrayList<>();
		
	}

	@Override
	public ArrayList<Integer> getCampioniNonAnalizzati() {
		return FacadeSingletonDB.getInstance().getSystemCampione().campioniNonAnalizzati;
	}

	public void setCampioniNonAnalizzati(ArrayList<Integer> campioniNonAnalizzati) {
		this.campioniNonAnalizzati = campioniNonAnalizzati;
	}


	public boolean registraCampione(int id, String stato, LocalDate ld) {
		
		
		return FacadeSingletonDB.getInstance().getCampioneDAO().insertCampione(id, stato, ld);
		
	}
	
	public Campione trovaCampione(int id) {
		
		return FacadeSingletonDB.getInstance().getCampioneDAO().trovaCampionePerId(id);
	}
	
	
	public ArrayList<Campione> selezionaCampioni(){
		
		return FacadeSingletonDB.getInstance().getCampioneDAO().selectAllCampioni();
	}
	
	public boolean aggiornaCampione(int id, String stato) {
		
		return FacadeSingletonDB.getInstance().getCampioneDAO().updateCampione(id, stato);
	}
	
	public boolean deleteCampione(int id) {
		
		return FacadeSingletonDB.getInstance().getCampioneDAO().eliminaCampione(id);
	}
	
	public ArrayList<Integer> campioniNonAnalizzati(){
		
		return FacadeSingletonDB.getInstance().getCampioneDAO().trovaCampioneNonAnalizzato(); //serve ancora?
	}
	
	
	

}
