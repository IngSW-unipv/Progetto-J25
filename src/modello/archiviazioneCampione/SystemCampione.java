package modello.archiviazioneCampione;
import java.time.LocalDate;

import java.util.ArrayList;

import jdbc.FacedeSingletonDB;

public class SystemCampione implements ISystemCampione{
	
	ArrayList<Integer> campioniNonAnalizzati; //come argomento ha gli integer perchè prendo solo gli id
	GestoreMagazzino gm;
	
	public SystemCampione() {
		
		this.campioniNonAnalizzati = new ArrayList<>();
		
	}

	@Override
	public ArrayList<Integer> getCampioniNonAnalizzati() {
		return campioniNonAnalizzati;
	}

	@Override
	public void setCampioniNonAnalizzati(ArrayList<Integer> campioniNonAnalizzati) {
		this.campioniNonAnalizzati = campioniNonAnalizzati;
	}

	@Override
	public boolean registraCampione(int id, String stato, LocalDate ld) {
		
		int sacche = FacedeSingletonDB.getInstance().getMagazzinoDAO().restituisciSacche();
		
		 gm = new GestoreMagazzino(sacche);
		
		FacedeSingletonDB.getInstance().getMagazzinoDAO().aggiornaSacche(gm.decrementaSacche(1));
		
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
	

}
