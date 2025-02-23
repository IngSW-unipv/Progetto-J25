package modello.documento;

import modello.Panelista;

public interface IGestoreStipendi {

	public double stipendioLordo(Panelista panelista);
	public double trattenute(Panelista panelista);
	public double stipendioNetto(Panelista panelista);
	
}
