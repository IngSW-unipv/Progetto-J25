package modello.documento;

import jdbc.FacedeSingletonDB;
import modello.Panelista;

public class SystemDocumento {
	
	
	
	public SystemDocumento() {
		
	}
	
	public Panelista restituisciPanelista(int id) {
		
		return FacedeSingletonDB.getInstance().getUserDAO().selectPanelista(id);
	}
	
	public double oreLavoroPanelista(int id, String mese) {
		
		return FacedeSingletonDB.getInstance().getOreLavoroDAO().selectOreLavoro(id, mese);
	}
	
	
	
}
