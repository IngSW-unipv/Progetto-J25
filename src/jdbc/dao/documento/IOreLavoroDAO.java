package jdbc.dao.documento;

import modello.creazionePanel.Slot;

import java.time.LocalTime;
import java.util.Map;

public interface IOreLavoroDAO {
	
	double selectOreLavoro(int id, String mese);
	boolean getOreLavPrenotati(Map<LocalTime, Slot> slots);
}
