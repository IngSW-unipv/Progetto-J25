package testing;

import controller.PrenotaInsacController;
import view.prenotazioneInsaccatore.*;

public class TestSystemPrenotaTurnoInsacc {
	public static void main(String[] argvs) {
		TestFacadeSingletonMax facade= TestFacadeSingletonMax.getIstanza();
		InterPrincInsaccatore viewprinc = new InterPrincInsaccatore();
		ViewInsaccatore viewturni = new ViewInsaccatore();
		 viewturni.setVisible(false);
		 viewprinc.setVisible(true);
	
		PrenotaInsacController controller = new PrenotaInsacController(viewturni,viewprinc);
		facade.getSystemPrenotaTurnoInsacc();
		
		
		//simulo la chiamata per generare i turni:
		
//		//CREAZIONE:
//		LocalDate luneprox = LocalDate.of(2025, 02, 24);
//		SystemPrenotaTurnoInsacc s1 = new SystemPrenotaTurnoInsacc();
//		//genero la mia prorssima settimana di turni:
//		s1.generaSettimana(luneprox);
//		//genero i tuni per tutta la settimana:
//		s1.riempiSettimana(120);
//		
//		
//		
//		//TEST: 
//		System.out.println("SETTIMANA GENERATA:");
//		System.out.println();
//		s1.stampaEnumSett();
//		
//		System.out.println();	
//		System.out.println();
//		
//		System.out.println("TURNI DI LUNEDI':");
//		System.out.println();
//		Giorno MART = s1.getSettimana()[1];
//		MART.stampaTurni();

		
	}

}
