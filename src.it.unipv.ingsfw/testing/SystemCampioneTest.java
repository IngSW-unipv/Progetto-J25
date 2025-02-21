package testing;
import modello.archiviazioneCampione.*;

import java.sql.Connection;
import java.time.LocalDate;

import jdbc.dao.campione.*;
import jdbc.ConnessioneDB;

public class SystemCampioneTest {

	public static void main(String[] args) {
		
		CampioneDAO campioneDAO = new CampioneDAO();
		SystemCampione sys = new SystemCampione(campioneDAO);
		
		
		
		boolean inserito = sys.registraCampione(1, "Non analizzato", LocalDate.now());
		 System.out.println("Campione inserito? " + inserito);
		 
		 
		 
		 Campione c = sys.trovaCampione(1);
	        if (c != null) {
	            System.out.println("Campione trovato: " + c.getId() + " - " + c.getStato());
	        }
	        
	        
	        boolean aggiornato = sys.aggiornaCampione(1, "Analizzato");
	        System.out.println("Campione aggiornato? " + aggiornato);
	        
	        
	        System.out.println("Lista campioni: " + sys.selezionaCampioni().size());
	     
	       boolean eliminato = sys.deleteCampione(1);
	        System.out.println("Campione eliminato? " + eliminato);

	        
	}

}
