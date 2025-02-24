package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import modello.archiviazioneCampione.Campione;
import modello.archiviazioneCampione.SystemCampione;

public class CampioneController {
	
	private SystemCampione systemCampione;

	public CampioneController(SystemCampione systemCampione) {
		
		this.systemCampione = systemCampione;
	}
	
	public boolean registraCampione(int id, String stato, LocalDate dataArrivo) {
		
		return systemCampione.registraCampione(id, stato, dataArrivo);
	}
	
	public Campione trovaCampione(int id) {
		
		return systemCampione.trovaCampione(id);
	}
	
	public ArrayList<Campione> selezionaCampioni(){
		
		return systemCampione.selezionaCampioni();
	}
	
	public boolean aggiornaCampione(int id, String stato) {
        return systemCampione.aggiornaCampione(id, stato);
    }

    public boolean eliminaCampione(int id) {
        return systemCampione.deleteCampione(id);
    }

    public ArrayList<Integer> campioniNonAnalizzati() {
        return systemCampione.campioniNonAnalizzati();
    }
}
