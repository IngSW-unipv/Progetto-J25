package modello.archiviazioneCampione;
import java.time.LocalDate;

import java.util.ArrayList;

import jdbc.FacedeSingletonDB;
import modello.gestioneInventario.Inventario;

public class SystemCampione implements ISystemCampione{
	
	ArrayList<Integer> campioniNonAnalizzati; //come argomento ha gli integer perchè prendo solo gli id
	Inventario inventario;
	
	public SystemCampione() {
		
		this.campioniNonAnalizzati = new ArrayList<>();
		inventario = FacedeSingletonDB.getInstance().getInventario();
	}

	@Override
	public ArrayList<Integer> getCampioniNonAnalizzati() {
		return FacedeSingletonDB.getInstance().getSystemCampione().campioniNonAnalizzati;
	}

	@Override
	public void setCampioniNonAnalizzati(ArrayList<Integer> campioniNonAnalizzati) {
		this.campioniNonAnalizzati = campioniNonAnalizzati;
	}

	@Override
	public boolean registraCampione(int id, String stato, LocalDate ld) {
		
		//int sacche = FacedeSingletonDB.getInstance().getMagazzinoDAO().restituisciSacche();
		
		 
		 inventario.decrementaSacche();
		
		
		
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
