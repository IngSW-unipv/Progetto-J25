package controller;
import modello.prenotazioneInsaccatore.*;
import view.prenotazioneInsaccatore.*;

public class PrenotaInsacController {

	private SystemPrenotaTurnoInsacc modello;
	private ViewInsaccatore interf;
	
	public PrenotaInsacController(SystemPrenotaTurnoInsacc modello, ViewInsaccatore interf) {
		this.modello = modello;
		this.interf = interf;
		//collego la view al controller:
		this.interf.setController(this);
	}
	
	
	
	

}
