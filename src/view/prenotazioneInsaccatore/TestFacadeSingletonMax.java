package view.prenotazioneInsaccatore;


import jdbc.dao.max.*;
import modello.prenotazioneInsaccatore.*;
import testing.TestSystemPrenotaTurnoInsacc;
import java.util.ArrayList;
import jdbc.*;


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
		}
	}
	
	
	//GETTER DAO:
	
	
	
	
	
	//METODI UTILI:
	
	  public void caricaSettimana() {
	        // Recupero tutti i giorni della settimana
	        ArrayList<Giorno> giorniSettimana = giornoDAO.trovaTutti();
	        
	        // Recupero i turni per ogni giorno
	        for (Giorno giorno : giorniSettimana) {
	            ArrayList<Turno> turniPerGiorno = turnoDAO.recuperaTurniGiorno(giorno);
	            giorno.setTurni(turniPerGiorno);
	        }
	  }
	
	
	
	
	
	
	
	
}
