package modello.documento;

import jdbc.FacedeSingletonDB;
import modello.Panelista;

public class SystemDocumento implements ISystemDocumento {
	
	
	
	public SystemDocumento() {
		
	}
	
	@Override
	public Panelista restituisciPanelista(int id) {
		
		Panelista panelista = FacedeSingletonDB.getInstance().getUserDAO().selectPanelista(id);
		
		if(panelista == null) {
			
			throw new IllegalArgumentException("L'id non corrisponde a nessun panelista");
		}
		return panelista;
	}
	
	@Override
	public double oreLavoroPanelista(int id, String mese) {
		
		double ore = FacedeSingletonDB.getInstance().getOreLavoroDAO().selectOreLavoro(id, mese);
		
		if(ore < 0) {
			
			throw new IllegalArgumentException("Ore di lavoro non valide per il panelista inserito");
		}
		
		return ore;
	}
	
	
	
}
