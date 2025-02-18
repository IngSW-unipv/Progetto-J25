package testing;

import java.time.LocalDate;

import modello.Panelista;
import modello.TipoUtente;
import modello.documento.Azienda;
import modello.documento.FactoryPanelista;

public class DocumentoRiepilogoTest {

	public static void main(String[] args) {
		
		LocalDate localDate = LocalDate.of(2003, 02, 16); 
		
		LocalDate dataEmissione = LocalDate.of(2025, 02, 17);
		
		Panelista p = FactoryPanelista.CreaUtente(TipoUtente.PANELISTA,"tommaso.ghisolfi003@gmail.com", "Tommaso", "Ghisolfi", "Broni", localDate, "GHSTMS3B16B201L", "Via Paolo Borsellino 28 Bressana Bottarone", 16);
		
		Azienda azienda = new Azienda();
		
		azienda.generaDocumento(p, dataEmissione, "FEBBRAIO");

	}

}
