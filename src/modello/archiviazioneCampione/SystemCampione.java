package modello.archiviazioneCampione;
import java.time.LocalDate;


import java.util.ArrayList;

<<<<<<< HEAD

=======
>>>>>>> branch 'main' of https://github.com/IngSW-unipv/Progetto-J25
import jdbc.FacadeSingletonDB;
import modello.gestioneInventario.Inventario;

public class SystemCampione implements ISystemCampione{
	
	ArrayList<Integer> campioniNonAnalizzati; //come argomento ha gli integer perchè prendo solo gli id
	Inventario inventario;
	
	public SystemCampione() {
		
		this.campioniNonAnalizzati = new ArrayList<>();
		inventario = FacadeSingletonDB.getInstance().getInventario();
	}

	@Override
	public ArrayList<Integer> getCampioniNonAnalizzati() {
		
		return FacadeSingletonDB.getInstance().getSystemCampione().campioniNonAnalizzati;
	}

	@Override
	public void setCampioniNonAnalizzati(ArrayList<Integer> campioniNonAnalizzati) {
		this.campioniNonAnalizzati = campioniNonAnalizzati;
	}

	@Override
	public boolean registraCampione(int id, String stato, LocalDate ld) {
		

		int sacche = FacadeSingletonDB.getInstance().getMagazzinoDAO().restituisciSacche();

		//int sacche = FacedeSingletonDB.getInstance().getMagazzinoDAO().restituisciSacche();

		
		 
		 inventario.decrementaSacche();
		
		FacadeSingletonDB.getInstance().getMagazzinoDAO().aggiornaSacche(gm.decrementaSacche(1));

		
<<<<<<< HEAD
=======
		
>>>>>>> branch 'main' of https://github.com/IngSW-unipv/Progetto-J25
		return FacadeSingletonDB.getInstance().getCampioneDAO().insertCampione(id, stato, ld);
		
	}
	
	@Override
	public Campione trovaCampione(int id) {
		
		return FacadeSingletonDB.getInstance().getCampioneDAO().trovaCampionePerId(id);
	}
	
	@Override
	public ArrayList<Campione> selezionaCampioni(){
		
		return FacadeSingletonDB.getInstance().getCampioneDAO().selectAllCampioni();
	}
	
	@Override
	public boolean aggiornaCampione(int id, String stato) {
		
		return FacadeSingletonDB.getInstance().getCampioneDAO().updateCampione(id, stato);
	}
	
	@Override
	public boolean deleteCampione(int id) {
		
		return FacadeSingletonDB.getInstance().getCampioneDAO().eliminaCampione(id);
	}
	
	
	@Override
	public ArrayList<Integer> campioniNonAnalizzati(){
		
		return FacadeSingletonDB.getInstance().getCampioneDAO().trovaCampioneNonAnalizzato(); 
	}
	
	

}
