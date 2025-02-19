package testing;

import java.time.LocalDate;


import modello.Panelista;
import modello.TipoUtente;
import modello.FactoryUtente;
import modello.documento.GestoreStipendi;

public class GestoreStipendiTest {

	public static void main(String[] args) {
		
		LocalDate localDate = LocalDate.of(2003, 02, 16);
		Panelista p = (Panelista) FactoryUtente.CreaUtente(TipoUtente.PANELISTA,"Tommaso", "email", 
				20, "Ghisolfi", "Broni", localDate, "GHSTMS03B16B201L", 
				"ll", 0, 0);
		
		GestoreStipendi gs = new GestoreStipendi();
		System.out.println(gs.stipendioLordo(p));
		System.out.println(gs.stipendioNetto(p));
		System.out.println(gs.trattenute(p));

	}

}
