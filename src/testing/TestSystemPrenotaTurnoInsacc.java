package testing;

import java.time.LocalDate;
import java.time.LocalTime;

import modello.prenotazioneInsaccatore.*;

public class TestSystemPrenotaTurnoInsacc {
	public static void main(String[] argvs) {
		
		//CREAZIONE:
		LocalDate luneprox = LocalDate.of(2025, 02, 24);
		SystemPrenotaTurnoInsacc s1 = new SystemPrenotaTurnoInsacc();
		//genero la mia prorssima settimana di turni:
		s1.generaSettimana(luneprox);
		//genero i tuni per tutta la settimana:
		s1.riempiSettimana(120);
		
		
		
		//TEST: 
		System.out.println("SETTIMANA GENERATA:");
		System.out.println();
		s1.stampaEnumSett();
		
		System.out.println();	
		System.out.println();
		
		System.out.println("TURNI DI LUNEDI':");
		System.out.println();
		Giorno MART = s1.getSettimana()[1];
		MART.stampaTurni();

		
	}

}
