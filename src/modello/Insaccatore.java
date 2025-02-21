package modello;

import java.time.LocalDate;

public class Insaccatore extends Panelista {
	private int oreLimite;
	private int limiteCanc;

	
	public Insaccatore(int id,String email, String nome, String cognome, String luogoNascita, LocalDate dataNascita,
			String codiceFiscale, String residenza, double oreLavoro, int oreLimite,int limiteCanc) {
		super(id,email, nome, cognome, luogoNascita, dataNascita, codiceFiscale, residenza, oreLavoro);
		this.limiteCanc=0;
		this.oreLimite=oreLimite;
		this.limiteCanc=0;
	}


	public int getOreLimite() {
		return oreLimite;
	}


	public void setOreLimite(int oreLimite) {
		this.oreLimite = oreLimite;
	}


	public int getLimiteCanc() {
		return limiteCanc;
	}


	public void setLimiteCanc(int limiteCanc) {
		this.limiteCanc = limiteCanc;
	}


	
	
	
	//METODI UTILI: 
	
	
	
}
