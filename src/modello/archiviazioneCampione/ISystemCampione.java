package modello.archiviazioneCampione;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ISystemCampione {
	
	boolean registraCampione(int id, String stato, LocalDate ld);
	Campione trovaCampione(int id);
	ArrayList<Campione> selezionaCampioni();
	boolean aggiornaCampione(int id, String stato);
	boolean deleteCampione(int id);
	ArrayList<Integer> getCampioniNonAnalizzati();
	void setCampioniNonAnalizzati(ArrayList<Integer> campioniNonAnalizzati);
	
}
