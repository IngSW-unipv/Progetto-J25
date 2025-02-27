package modello.documento;

import jdbc.FacadeSingletonDB;
import modello.Panelista;

public class SystemDocumento {
	
	
	
	public SystemDocumento() {
		
	}
	
	public Panelista restituisciPanelista(int id) {
		
		return FacadeSingletonDB.getInstance().getUserDAO().selectPanelista(id);
	}
	
	public double oreLavoroPanelista(int id, String mese) {
		
		return FacadeSingletonDB.getInstance().getOreLavoroDAO().selectOreLavoro(id, mese);
	}
	
	
	
}
