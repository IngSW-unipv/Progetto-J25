package jdbc.dao.documento;

import modello.creazionePanel.Slot;

import java.time.LocalTime;
import java.util.Map;

public interface IOreLavoroDAO {
	
	double selectOreLavoro(int id, String mese);
	boolean getOreLavPrenotati(Map<LocalTime, Slot> slots);


	/*
	 * Metodo che permette di aggiornare le ore lavorate di un panelista (Andres)
	 */
	public boolean aggiornaOreLavoro(int id, String mese, double nuoveOre);
}
