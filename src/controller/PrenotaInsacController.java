package controller;
import java.time.LocalDate;

import modello.prenotazioneInsaccatore.*;
import view.prenotazioneInsaccatore.*;

public class PrenotaInsacController {
<<<<<<< HEAD

	private SystemPrenotaTurnoInsacc modello;
	private ViewInsaccatore interf;
	
	public PrenotaInsacController(SystemPrenotaTurnoInsacc modello, ViewInsaccatore interf) {
		this.modello = modello;
		this.interf = interf;
		//collego la view al controller:
		this.interf.setController(this);
	}
=======
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
	}
>>>>>>> branch 'main' of https://github.com/IngSW-unipv/Progetto-J25.git
	
	
	//METODI APERTURA VIEW:
	public void apriViewInsaccatore() {
		//prima di tutto verifico la presenza di turni
		new ViewInsaccatore();
		viewturni.aggiornaTurni(facade.getSystemPrenotaTurnoInsacc().getSettimana());
        viewprinc.setVisible(false);  // Nasconde la vista principale
        viewturni.setVisible(true);  // Mostra la vista dei turni
    }

    public void tornaAllaViewPrincipale() {
        viewturni.setVisible(false);  // Nasconde la vista dei turni
        viewprinc.setVisible(true);  // Mostra la vista principale
    }

}
