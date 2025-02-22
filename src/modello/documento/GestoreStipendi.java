package modello.documento;
import modello.Panelista;

public class GestoreStipendi implements IGestoreStipendi {
	
	
	private final double STIPENDIOPANELISTA = 10;
	private final double TRATTENUTA = 0.2;
	
	
	public GestoreStipendi() {
	
		
	}
	
	@Override
	public double stipendioLordo(Panelista panelista) {
		
		return  panelista.getOreLavoro() * STIPENDIOPANELISTA; 
		
	}
	
	
	@Override
	public double trattenute(Panelista panelista) {
		
		
		return  panelista.getOreLavoro() * STIPENDIOPANELISTA * TRATTENUTA; 
		
	}
	
	@Override
	public double stipendioNetto(Panelista panelista) {
		
		return (panelista.getOreLavoro() * STIPENDIOPANELISTA) -(panelista.getOreLavoro() * STIPENDIOPANELISTA * TRATTENUTA);
	}
	

}
