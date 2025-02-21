package modello.archiviazioneCampione;

import java.time.LocalDate;

public class Campione {

	private int id;
	private String stato;
	private LocalDate dataArrivo;
	
	public Campione(int id, String stato, LocalDate dataArrivo) {

		this.id = id;
		this.stato = stato;
		this.dataArrivo = dataArrivo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public LocalDate getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(LocalDate dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	
	
}
