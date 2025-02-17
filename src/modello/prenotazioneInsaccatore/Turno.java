package modello.prenotazioneInsaccatore;

import modello.Insaccatore;

public class Turno {
	private String data;
	private Insaccatore ins;
	private boolean stato;
	private int durata;
	
	//COSTRUTTORI:
	public Turno(String data, int durata) {
		this.data = data;
		this.ins = null;
		this.stato = false;
		this.durata = durata;
	}
	
	//METODI SET GET:
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
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
	
	
		
}
