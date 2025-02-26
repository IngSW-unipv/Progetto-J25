package modello.documento;

import modello.Panelista;

public interface ISystemDocumento {

	public Panelista restituisciPanelista(int id);
	public double oreLavoroPanelista(int id, String mese);
}
