package modello.documento;
import modello.Panelista;

public class GestoreStipendi {
	
	
	private final double STIPENDIOPANELISTA = 10;
	private final double TRATTENUTA = 0.2;
	
	
	public GestoreStipendi() {
	
		
	}
	
	public double stipendioLordo(Panelista panelista) {
		
		return  panelista.getOreLavoro() * STIPENDIOPANELISTA; 
		
	}
	
	public double trattenute(Panelista panelista) {
		
		
		return  panelista.getOreLavoro() * STIPENDIOPANELISTA * TRATTENUTA; 
		
	}
	
	public double stipendioNetto(Panelista panelista) {
		
		return (panelista.getOreLavoro() * STIPENDIOPANELISTA) -(panelista.getOreLavoro() * STIPENDIOPANELISTA * TRATTENUTA);
	}
	

}
