package controller;
import java.time.LocalDate;

import modello.prenotazioneInsaccatore.*;
import view.prenotazioneInsaccatore.*;

public class PrenotaInsacController {
	private ViewInsaccatore viewturni;
	private InterPrincInsaccatore viewprinc;
	private TestFacadeSingletonMax facade;
	
	//COSTRUTTORE:
	public PrenotaInsacController( ViewInsaccatore viewturni, InterPrincInsaccatore viewprinc) {
		this.viewturni = viewturni;
		this.viewprinc = viewprinc;
		//collego la view al controller:
		this.viewturni.setController(this);
		this.viewprinc.setController(this);
		this.facade = TestFacadeSingletonMax.getIstanza();
	}
	
	
	//METODI UTILI:
	public void caricaTurni() {
		//ottengo i giorni della settimana dalla facadde:
		Giorno[] sett = facade.getSystemPrenotaTurnoInsacc().getSettimana();
		viewturni.aggiornaTurni(sett);	
 		
	}
	
	public void generaSettimanaTurni(LocalDate data,int durata) {
		Giorno[] sett = facade.generaSettimanaTurni(data,durata); 
		viewturni.aggiornaTurni(sett);
	}
	
	
	//METODI APERTURA VIEW:
	public void apriViewInsaccatore() {
        viewprinc.setVisible(false);  // Nasconde la vista principale
        viewturni.setVisible(true);  // Mostra la vista dei turni
    }

    public void tornaAllaViewPrincipale() {
        viewturni.setVisible(false);  // Nasconde la vista dei turni
        viewprinc.setVisible(true);  // Mostra la vista principale
    }

}
