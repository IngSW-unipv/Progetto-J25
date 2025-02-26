package testing;
import modello.archiviazioneCampione.*;
import view.archiviazioneCampione.CampioneView;

import java.sql.Connection;
import java.time.LocalDate;

import controller.CampioneController;
import jdbc.dao.campione.*;
import jdbc.ConnessioneDB;
import jdbc.FacedeSingletonDB;

public class SystemCampioneTest {

	public static void main(String[] args) {
		
		
	//	SystemCampione sys = FacedeSingletonDB.getInstance().getSystemCampione();
		
		
		
		
	/*	boolean inserito = sys.registraCampione(5, "Non analizzato", LocalDate.now());
		 System.out.println("Campione inserito? " + inserito);
		 
		 
		
		 Campione c = sys.trovaCampione(2);
	        if (c != null) {
	            System.out.println("Campione trovato: " + c.getId() + " - " + c.getStato());
	        }
	        
	        
	        boolean aggiornato = sys.aggiornaCampione(2, "Non analizzato");
	        System.out.println("Campione aggiornato? " + aggiornato);
	        
	        
	        System.out.println("Lista campioni: " + sys.selezionaCampioni().size());
	  */    
		
		
	 //      System.out.println( sys.getCampioniNonAnalizzati().size());
	     
	  //    boolean eliminato = sys.deleteCampione(5);
	   //   System.out.println("Campione eliminato? " + eliminato);
	
		 ISystemCampione sys = FacedeSingletonDB.getInstance().getSystemCampione();
		 
		 CampioneController campioneController = new CampioneController(sys);
		
		 
	        
	}

}
