package modello.documento;
import modello.Panelista;

public class GestoreStipendi implements IGestoreStipendi {
	
	
	private final double STIPENDIOPANELISTA = 10;
	private final double TRATTENUTA = 0.2;
	
	
	public GestoreStipendi() {
	
		
	}
	
	@Override
	public double stipendioLordo(Panelista panelista) {
		
		if(panelista.getOreLavoro() < 0) {
			
			throw new IllegalArgumentException("Ore lavorate non valide");
		}
		return  panelista.getOreLavoro() * STIPENDIOPANELISTA; 
		
	}
	
	
	@Override
	public double trattenute(Panelista panelista) {
		
		if(panelista.getOreLavoro() < 0) {
			
			throw new IllegalArgumentException("Ore lavorate non valide");
		}
		return  panelista.getOreLavoro() * STIPENDIOPANELISTA * TRATTENUTA; 
		
	}
	
	@Override
	public double stipendioNetto(Panelista panelista) {
		if(panelista.getOreLavoro() < 0) {
			
			throw new IllegalArgumentException("Ore lavorate non valide");
		}
		return (panelista.getOreLavoro() * STIPENDIOPANELISTA) -(panelista.getOreLavoro() * STIPENDIOPANELISTA * TRATTENUTA);
	}
	

}
