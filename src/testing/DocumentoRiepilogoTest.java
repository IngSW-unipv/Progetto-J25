package testing;

import java.time.LocalDate;

import jdbc.bean.UserDAO;
import jdbc.dao.documento.OreLavoroDAO;
import modello.FactoryUtente;
import modello.Panelista;
import modello.TipoUtente;
import modello.documento.Azienda;
import modello.documento.GestoreStipendi;
import modello.documento.SystemDocumento;

public class DocumentoRiepilogoTest {

	/*public static void main(String[] args) {
		
		LocalDate localDate = LocalDate.of(2003, 02, 16); 
		
		LocalDate dataEmissione = LocalDate.of(2025, 02, 17);
		
		Panelista p = (Panelista) FactoryUtente.CreaUtente(TipoUtente.PANELISTA,"Tommaso", "email", 
				20, "Ghisolfi", "Broni", localDate, "GHSTMS03B16B201L", 
				"ll", 0, 0);
		
		Azienda azienda = new Azienda();
		
		azienda.generaDocumento(p, dataEmissione, "FEBBRAIO");

	} */
	
	public static void main(String[] args) {
	UserDAO userDAO = new UserDAO();
	
	OreLavoroDAO oreLavoroDAO = new OreLavoroDAO();
	
	SystemDocumento sys = new SystemDocumento(userDAO, oreLavoroDAO);
	
	GestoreStipendi gs = new GestoreStipendi();
	
	Azienda azienda = new Azienda(gs, sys);
	
	azienda.generaDocumento(9, LocalDate.now(), "Febbraio");
	
	


	}

}
