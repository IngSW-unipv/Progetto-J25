package modello.prenotazioneInsaccatore;
import java.util.ArrayList;

public class Giorno {
	private GiorniSettimana tipo;
	private ArrayList<Turno> turni;
	
	//COSTRUTTORI:
	public Giorno(GiorniSettimana tipo, ArrayList<Turno> turni) {
		this.tipo = tipo;
		this.turni = turni;
	}
	public Giorno(GiorniSettimana tipo) {
		this.tipo = tipo;
		
	}


	//SETTER GETTER
	public GiorniSettimana getTipo() {
		return tipo;
	}

	public void setTipo(GiorniSettimana tipo) {
		this.tipo = tipo;
	}

	public ArrayList<Turno> getTurni() {
		return turni;
	}

	public void setTurni(ArrayList<Turno> turni) {
		this.turni = turni;
	}
	
	
}
