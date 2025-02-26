package modello.archiviazioneCampione;
import java.time.LocalDate;

import java.util.ArrayList;

import jdbc.FacedeSingletonDB;

public class SystemCampione implements ISystemCampione{
	
	ArrayList<Integer> campioniNonAnalizzati; //come argomento ha gli integer perchè prendo solo gli id

	
	public SystemCampione() {
		
		this.campioniNonAnalizzati = new ArrayList<>();
		
	}

	@Override
	public void setCampioniNonAnalizzati(ArrayList<Integer> campioniNonAnalizzati) {
		this.campioniNonAnalizzati = campioniNonAnalizzati;
	}

	@Override
	public boolean registraCampione(int id, String stato, LocalDate ld) {
		
		
		return FacedeSingletonDB.getInstance().getCampioneDAO().insertCampione(id, stato, ld);
		
	}
	
	@Override
	public Campione trovaCampione(int id) {
		
		return FacedeSingletonDB.getInstance().getCampioneDAO().trovaCampionePerId(id);
	}
	
	@Override
	public ArrayList<Campione> selezionaCampioni(){
		
		return FacedeSingletonDB.getInstance().getCampioneDAO().selectAllCampioni();
	}
	
	@Override
	public boolean aggiornaCampione(int id, String stato) {
		
		return FacedeSingletonDB.getInstance().getCampioneDAO().updateCampione(id, stato);
	}
	
	@Override
	public boolean deleteCampione(int id) {
		
		return FacedeSingletonDB.getInstance().getCampioneDAO().eliminaCampione(id);
	}
	
	/*
	public ArrayList<Integer> campioniNonAnalizzati(){
		
		return FacedeSingletonDB.getInstance().getCampioneDAO().trovaCampioneNonAnalizzato(); 
	}
	
	*/
	

}
