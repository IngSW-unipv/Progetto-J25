package testing;

import java.time.LocalDate;

import modello.Panelista;
import modello.TipoUtente;
import modello.documento.FactoryPanelista;
import modello.documento.GestoreStipendi;

public class GestoreStipendiTest {

	public static void main(String[] args) {
		
		LocalDate localDate = LocalDate.of(2003, 02, 16);
		Panelista p = FactoryPanelista.CreaUtente(TipoUtente.PANELISTA,"tommaso.ghisolfi003@gmail.com", "Tommaso", "Ghisolfi", "Broni", localDate, "GHSTMS3B16B201L", "Via Paolo Borsellino 28 Bressana Bottarone", 20);
		
		GestoreStipendi gs = new GestoreStipendi();
		System.out.println(gs.stipendioLordo(p));
		System.out.println(gs.stipendioNetto(p));
		System.out.println(gs.trattenute(p));

	}

}
