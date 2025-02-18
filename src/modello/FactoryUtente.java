package modello;

import java.time.LocalDate;

public class FactoryUtente {
	

	public static Utente CreaUtente(TipoUtente u, String email, String nome, String cognome, String luogoNascita, LocalDate dataNascita, String codiceFiscale, String residenza, double oreLavoro) {
	
		switch(u) {
		
			case PANELLEADER:
				return new PanelLeader(email, nome, cognome, luogoNascita, dataNascita, codiceFiscale, residenza);
			case PANELISTA:

				return new Panelista(email, nome, cognome, luogoNascita, dataNascita, codiceFiscale, residenza, oreLavoro);

			case INSACCATORE:
				return new Insaccatore(email, nome, oreLavoro);
			default: throw new IllegalArgumentException("Utente non riconosciuto");
		}
		
	}

}
