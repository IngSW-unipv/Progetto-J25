package modello;

import java.time.LocalDate;

public class PanelLeader extends Utente{

	public PanelLeader(int id, String email, String nome, String cognome, String luogoNascita, LocalDate dataNascita, String codiceFiscale, String residenza,
					   String password, String nickname, String ruolo) {
		
		super(id, email, nome, cognome, luogoNascita, dataNascita, codiceFiscale, residenza, password, nickname, ruolo);
	}
}


