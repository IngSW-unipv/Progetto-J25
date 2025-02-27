package controller;
import java.time.LocalDate;

import jdbc.FacadeSingletonInsaccatori;
import modello.prenotazioneInsaccatore.*;
import view.prenotazioneInsaccatore.*;

public class PrenotaInsacController {
	private ViewInsaccatore viewturni;
	private ViewPrincInsaccatore viewprinc;
	private FacadeSingletonInsaccatori facade;
	
	//COSTRUTTORE:
	public PrenotaInsacController( ViewInsaccatore viewturni, ViewPrincInsaccatore viewprinc) {
		this.viewturni = viewturni;
		this.viewprinc = viewprinc;
		//collego la view al controller:
		this.viewturni.setController(this);
		this.viewprinc.setController(this);
		this.facade = FacadeSingletonInsaccatori.getIstanza();
	}
	
	
	//METODI UTILI:
	public void caricaTurni() {
		//ottengo i giorni della settimana dalla facade:
		Giorno[] sett = facade.getSystemPrenotaTurnoInsacc().getSettimana();
		viewturni.aggiornaTurni(sett);		
	}
	
	public void generaSettimanaTurni(LocalDate data,int durata) {
		facade.generaSettimanaTurni(data,durata);
	}
	
	public boolean prenotaTurno(Turno t) {
		boolean successo = false;
		try {
		facade.prenotazioneAlTurno(viewprinc.getIdInsaccatore(), t);
		successo = true;
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return successo;
	}
	
	public boolean cancellaTurno(Turno t) {
		boolean successo = false;
		try {
		facade.cancellazioneAlTurno(viewprinc.getIdInsaccatore(), t);
		successo = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return successo;
	}
	
	//METODI APERTURA VIEW:
	public void apriViewInsaccatore() {
		//prima di tutto verifico la presenza di turni
		viewturni.aggiornaTurni(facade.getSystemPrenotaTurnoInsacc().getSettimana());
        viewprinc.setVisible(false);  // Nasconde la vista principale
        viewturni.setVisible(true);  // Mostra la vista dei turni
    }

    public void tornaAllaViewPrincipale() {
        viewturni.setVisible(false);  // Nasconde la vista dei turni
        viewprinc.setVisible(true);  // Mostra la vista principale
    }

}
