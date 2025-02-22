package modello;

import java.time.LocalDate;

public class Panelista extends Utente {

	double oreLavoro;

    public Panelista(int id,String email, String nome, String cognome, String luogoNascita, LocalDate dataNascita, String codiceFiscale,
					 String nickname, String password, String ruolo, String residenza, double oreLavoro) {
        super(id, email, nome, cognome, luogoNascita, dataNascita, codiceFiscale, residenza, nickname, password, ruolo);
		this.oreLavoro = oreLavoro;
    }


	public double getOreLavoro() {
		return oreLavoro;
	}

	public void setOreLavoro(int oreLavoro) {
		this.oreLavoro = oreLavoro;
	}

    

}
