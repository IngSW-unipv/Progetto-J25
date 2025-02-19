package modello.documento;

import java.time.LocalDate;

import modello.Insaccatore;

import modello.Panelista;
import modello.TipoUtente;

public class FactoryPanelista {

	public static Panelista CreaUtente(TipoUtente u, String email, String nome, String cognome, String luogoNascita, LocalDate dataNascita, String codiceFiscale, String residenza, double oreLavoro) {
		
		switch(u) {
		
			case PANELISTA:

				return new Panelista(email, nome, cognome, luogoNascita, dataNascita, codiceFiscale, residenza, oreLavoro);

			/*case INSACCATORE:
				return new Insaccatore(email, nome, oreLavoro);*/
			default: throw new IllegalArgumentException("Utente non riconosciuto");
		}
		
	}
}
