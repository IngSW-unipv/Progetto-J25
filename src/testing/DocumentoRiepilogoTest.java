package testing;

import java.time.LocalDate;

import controller.DocumentoController;
import jdbc.FacedeSingletonDB;
import modello.documento.Azienda;
import modello.documento.GestoreStipendi;
import modello.documento.SystemDocumento;
import modello.documento.IGestoreStipendi;


public class DocumentoRiepilogoTest {

	
	public static void main(String[] args) {
	
	/*SystemDocumento sys = FacedeSingletonDB.getInstance().getSystemDocumento();
	
	GestoreStipendi gs = new GestoreStipendi();
	
	Azienda azienda = new Azienda(gs, sys);
	
	azienda.generaDocumento(9, LocalDate.now(), "Febbraio");
	*/
		
		IGestoreStipendi gestore = new GestoreStipendi(); 
        SystemDocumento systemDocumento = new SystemDocumento(); 
        Azienda azienda = new Azienda(gestore, systemDocumento); 

        
        DocumentoController controller = new DocumentoController(azienda);
	


	}

}
