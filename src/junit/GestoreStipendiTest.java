/*package junit;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import modello.FactoryUtente;
import modello.Panelista;
import modello.TipoUtente;
import modello.documento.GestoreStipendi;
import modello.documento.IGestoreStipendi;

public class GestoreStipendiTest {
	private IGestoreStipendi gestore;
	private Panelista panelista;
	
	
	@Before
	public void setUp() throws Exception {
		
		gestore = new GestoreStipendi();
		
	}

	@Test
	public void testStipendioLordoPositivo() {
		
		panelista =(Panelista) FactoryUtente.CreaUtente(TipoUtente.PANELISTA, "Tommaso", 16, "tommaso.ghisolfi003@gmail.com", 70, "Ghisolfi", "Broni", 
				LocalDate.now(), "GHSTMS03B16B201L", "Bressana", "Tommy", "gg", "pa", 2, 4);
		
		double stipendioLordo = gestore.stipendioLordo(panelista);
		
		assertEquals(700 , stipendioLordo, 0);
	}
	
	@Test
	public void testStipedioLordoOreNegative() {
		
		panelista =(Panelista) FactoryUtente.CreaUtente(TipoUtente.PANELISTA, "Tommaso", 16, "tommaso.ghisolfi003@gmail.com", -20, "Ghisolfi", "Broni", 
				LocalDate.now(), "GHSTMS03B16B201L", "Bressana", "Tommy", "gg", "pa", 2, 4);
		try {
			
			double stipendioLordo = gestore.stipendioLordo(panelista);
			fail("Eccezione attesa ma non lanciata");
		}catch(IllegalArgumentException e) {
			
			assertEquals("Ore lavorate non valide", e.getMessage());
		}
	}
	
	@Test
	public void testStipedioLordoOreZero() {
		
		panelista =(Panelista) FactoryUtente.CreaUtente(TipoUtente.PANELISTA, "Tommaso", 16, "tommaso.ghisolfi003@gmail.com", 0, "Ghisolfi", "Broni", 
				LocalDate.now(), "GHSTMS03B16B201L", "Bressana", "Tommy", "gg", "pa", 2, 4);
		
		double stipendioLordo = gestore.stipendioLordo(panelista);
		assertEquals(0, stipendioLordo, 0 );
	}
	
	@Test 
	public void testTrattenutePositivo() {
		
		panelista =(Panelista) FactoryUtente.CreaUtente(TipoUtente.PANELISTA, "Tommaso", 16, "tommaso.ghisolfi003@gmail.com", 70, "Ghisolfi", "Broni", 
				LocalDate.now(), "GHSTMS03B16B201L", "Bressana", "Tommy", "gg", "pa", 2, 4);
		
		double trattenute = gestore.trattenute(panelista);
		assertEquals(140, trattenute,0);
	}
	
	@Test
	public void testTrattenuteOreNegative() {
		
		panelista =(Panelista) FactoryUtente.CreaUtente(TipoUtente.PANELISTA, "Tommaso", 16, "tommaso.ghisolfi003@gmail.com", -20, "Ghisolfi", "Broni", 
				LocalDate.now(), "GHSTMS03B16B201L", "Bressana", "Tommy", "gg", "pa", 2, 4);
		
		try {
			
			double trattenute = gestore.trattenute(panelista);
			fail("Eccezione attesa ma non lanciata");
		}catch(IllegalArgumentException e) {
			
			assertEquals("Ore lavorate non valide", e.getMessage());
		}
	}
	
	@Test
	public void testTrannenuteOreZero() {
		
		panelista =(Panelista) FactoryUtente.CreaUtente(TipoUtente.PANELISTA, "Tommaso", 16, "tommaso.ghisolfi003@gmail.com", 0, "Ghisolfi", "Broni", 
				LocalDate.now(), "GHSTMS03B16B201L", "Bressana", "Tommy", "gg", "pa", 2, 4);
		
		double trattenute = gestore.trattenute(panelista);
		assertEquals(0, trattenute, 0);
	}
	
	@Test
	public void testStipendioNettoPositivo() {
		
		panelista =(Panelista) FactoryUtente.CreaUtente(TipoUtente.PANELISTA, "Tommaso", 16, "tommaso.ghisolfi003@gmail.com", 70, "Ghisolfi", "Broni", 
				LocalDate.now(), "GHSTMS03B16B201L", "Bressana", "Tommy", "gg", "pa", 2, 4);
		
		double stipendioNetto = gestore.stipendioNetto(panelista);
		assertEquals(560, stipendioNetto, 0);
	}
	
	@Test
	public void testStipendioNettoOreNegative() {
		
		panelista =(Panelista) FactoryUtente.CreaUtente(TipoUtente.PANELISTA, "Tommaso", 16, "tommaso.ghisolfi003@gmail.com", -20, "Ghisolfi", "Broni", 
				LocalDate.now(), "GHSTMS03B16B201L", "Bressana", "Tommy", "gg", "pa", 2, 4);
		try {
			
			double stipendioNetto = gestore.stipendioNetto(panelista);
			fail("Eccezione attesa ma non lanciata");
		}catch(IllegalArgumentException e){
			
			assertEquals("Ore lavorate non valide", e.getMessage());
		}
	}
	
	@Test
	public void testStipendioNettoOreZero() {
		
		panelista =(Panelista) FactoryUtente.CreaUtente(TipoUtente.PANELISTA, "Tommaso", 16, "tommaso.ghisolfi003@gmail.com", 0, "Ghisolfi", "Broni", 
				LocalDate.now(), "GHSTMS03B16B201L", "Bressana", "Tommy", "gg", "pa", 2, 4);
		
		double stipendioNetto = gestore.stipendioNetto(panelista);
		assertEquals(0, stipendioNetto, 0);
	}

}
*/