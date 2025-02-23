package view.prenotazioneInsaccatore;


import jdbc.dao.max.*;
import modello.prenotazioneInsaccatore.*;
import testing.TestSystemPrenotaTurnoInsacc;


public class TestFacadeSingletonMax {
	//ATTRIBUTI:
	private static TestFacadeSingletonMax istanza;
	//attributi DAO:
	private ITurnoDAO turnoDAO;
	private IGiornoDAO giornoDAO;
	
	//attributi system:
	private SystemPrenotaTurnoInsacc systeminsac;
	
	
	//COSTRUTTORE



	public TestFacadeSingletonMax() {
		//inizializzo i dao:
		this.turnoDAO = new TurnoDAO();
		this.giornoDAO = new GiornoDAO();
	}
	
	//METODI GETTER:
	public static TestFacadeSingletonMax getIstanza() {
		if(istanza==null) {
			istanza = new TestFacadeSingletonMax();
		}
		return istanza;
	}
	
	//lazy initialization dei system:
	public SystemPrenotaTurnoInsacc getSystemPrenotaTurnoInsacc(){
		if(systeminsac==null) {
			systeminsac = new SystemPrenotaTurnoInsacc();
			systeminsac.);
		}
	}
	
	//GETTER DAO:
	
	
	
	
	
	//METODI UTILI:
	
	
	
	
	
	
	
	
	
	
}
