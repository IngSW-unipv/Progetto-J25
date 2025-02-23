package controller;
import modello.prenotazioneInsaccatore.*;
import view.prenotazioneInsaccatore.*;

public class PrenotaInsacController {
	private ViewInsaccatore interf;
	private TestFacadeSingletonMax facade;
	
	//COSTRUTTORE:
	public PrenotaInsacController( ViewInsaccatore interf) {
		this.interf = interf;
		//collego la view al controller:
		this.interf.setController(this);
		this.facade = TestFacadeSingletonMax.getIstanza();
	}
	
	
	
	
	
	
	
	
	
	

}
