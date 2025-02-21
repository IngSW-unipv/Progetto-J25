package modello.prenotazioneInsaccatore;
import java.util.ArrayList;
import java.time.LocalDate;

public class Giorno {
	private GiorniSettimana tipo;
	private ArrayList<Turno> turni;
	private LocalDate data;
	
	
	//COSTRUTTORI:
	public Giorno(GiorniSettimana tipo,LocalDate data, ArrayList<Turno> turni) {
		this.tipo = tipo;
		this.data = data;
		this.turni = turni;
	}
	public Giorno(GiorniSettimana tipo, LocalDate data) {
		this.tipo = tipo;
		this.data= data;
		
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
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}

	
	
	
	//METODI DI TEST, MI SERVONO PER VEDERE SE SI CREANO GLI OGGETTI:
	public void stampaTurni() {
		for(Turno t: turni) {
			System.out.println("Durata: "+ t.getDurata() + "  Inizio: "+t.getOrainizio());
		}
	}
	
	
}
