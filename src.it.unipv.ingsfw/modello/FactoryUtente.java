package modello;

import java.time.LocalDate;

public class FactoryUtente {
	
	public static Utente CreaUtente(TipoUtente u, String nome, int id,String email,
									double oreLavoro, String cognome, String luogoNascita,
									LocalDate dataNascita, String codiceFiscale, String residenza, String nickname, String password,
									String ruolo, int oreLimite,
									int limiteCanc) {

	
		switch(u) {
		
			case PANELLEADER:
				return new PanelLeader(id,email, nome, cognome, luogoNascita, dataNascita, codiceFiscale, residenza, nickname, password, ruolo);
			case PANELISTA:
				return new Panelista(id,email, nome, cognome, luogoNascita, dataNascita, codiceFiscale, residenza, nickname, password, ruolo, oreLavoro);

			case INSACCATORE:
				return new Insaccatore(id,email, nome, cognome, luogoNascita, dataNascita, codiceFiscale, residenza, oreLavoro,
						nickname, password, ruolo, oreLimite, limiteCanc );
			default: throw new IllegalArgumentException("Utente non riconosciuto");
		}
		
	}

}
