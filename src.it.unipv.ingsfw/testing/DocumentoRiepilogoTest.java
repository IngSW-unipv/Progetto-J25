package testing;

import java.time.LocalDate;

import modello.FactoryUtente;
import modello.Panelista;
import modello.TipoUtente;
import modello.documento.Azienda;

public class DocumentoRiepilogoTest {

	public static void main(String[] args) {
		
		LocalDate localDate = LocalDate.of(2003, 02, 16); 
		
		LocalDate dataEmissione = LocalDate.of(2025, 02, 17);
		
		Panelista p = (Panelista) FactoryUtente.CreaUtente(TipoUtente.PANELISTA,"Tommaso", "email", 
				20, "Ghisolfi", "Broni", localDate, "GHSTMS03B16B201L", 
				"ll", 0, 0);
		
		Azienda azienda = new Azienda();
		
		azienda.generaDocumento(p, dataEmissione, "FEBBRAIO");

	}

}
