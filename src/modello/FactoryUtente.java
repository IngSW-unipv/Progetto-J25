package modello;

import java.time.LocalDate;

public class FactoryUtente {
	

	public static Utente CreaUtente(TipoUtente u, String nome, String email,
									double oreLavoro, String cognome, String luogoNascita,
									LocalDate dataNascita, String codiceFiscale, String residenza, int oreLimite,
									int limiteCanc) {
	
		switch(u) {
		
			case PANELLEADER:
				return new PanelLeader(email, nome, cognome, luogoNascita, dataNascita, codiceFiscale, residenza);
			case PANELISTA:

				return new Panelista(email, nome, cognome, luogoNascita, dataNascita, codiceFiscale, residenza, oreLavoro);

			case INSACCATORE:
				return new Insaccatore(email, nome, cognome, luogoNascita, dataNascita, codiceFiscale, residenza, oreLavoro, oreLimite, limiteCanc );
			default: throw new IllegalArgumentException("Utente non riconosciuto");
		}
		
	}

}
