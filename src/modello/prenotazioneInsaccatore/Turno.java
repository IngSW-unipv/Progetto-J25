package modello.prenotazioneInsaccatore;

import modello.Insaccatore;
import java.time.LocalTime;
public class Turno {
	private Insaccatore ins;
	private boolean stato;
	private int durata; //in minuti
	private LocalTime orainizio;
	private int id;
	
	//COSTRUTTORI:
	public Turno(int durata, LocalTime orainizio) {
		this.durata = durata;
		this.orainizio = orainizio;
	}
	
	//METODI SET GET:
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	
	public LocalTime getOrainizio() {
		return orainizio;
	}
	public void setOrainizio(LocalTime orainizio) {
		this.orainizio = orainizio;
	}
	public Insaccatore getIns() {
		return ins;
	}
	public void setIns(Insaccatore ins) {
		this.ins = ins;
	}
	public boolean isStato() {
		return stato;
	}
	public void setStato(boolean stato) {
		this.stato = stato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
		
}
