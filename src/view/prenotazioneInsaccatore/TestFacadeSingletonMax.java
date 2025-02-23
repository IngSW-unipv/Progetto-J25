package view.prenotazioneInsaccatore;

import jdbc.dao.max.ITurnoDAO;
import jdbc.dao.max.TurnoDAO;
import testing.TestSystemPrenotaTurnoInsacc;


public class TestFacadeSingletonMax {
	//ATTRIBUTI:
	private static TestFacadeSingletonMax istanza;

	private  TestFacadeSingletonMax() {
	}

	public static TestFacadeSingletonMax getIstanza() {
		if(istanza==null) {
			istanza = new TestFacadeSingletonMax();
		}
		return istanza;
	}
	
	
	//METODI LAZY:
	private ITurnoDAO getTurnoDAO() {
		if(turnoDAO==null) {
			turnoDAO = new TurnoDAO();
		}
		return turnoDAO;
	}
	
	//METODI DEI DAO:
	
	
	
	
	
	
	
	
	
	
}*/
