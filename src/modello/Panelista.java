package modello;

import java.time.LocalDate;

public class Panelista extends Utente {
	
	
	double oreLavoro;

    public Panelista(String email, String nome, String cognome, String luogoNascita, LocalDate dataNascita, String codiceFiscale, String residenza, double oreLavoro) {
        super(email, nome, cognome, luogoNascita, dataNascita, codiceFiscale, residenza);
		this.oreLavoro = oreLavoro;
    }


	public double getOreLavoro() {
		return oreLavoro;
	}

	public void setOreLavoro(double oreLavoro) {
		this.oreLavoro = oreLavoro;
	}
	

    
    

}
