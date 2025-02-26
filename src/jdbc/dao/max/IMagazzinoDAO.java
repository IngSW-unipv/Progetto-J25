package jdbc.dao.max;

import modello.gestioneInventario.Inventario;

public interface IMagazzinoDAO {
	public Inventario trovaInventario();
	public void aggiornaSacche(int sacche);
	public void aggiornaBuste(int buste);
	public void aggiornaTappi(int tappi);
	public void aggiornaTubi(int tubi);
	public void aggiornaRotoliTubi(int rotoliTubi);
	public void aggiornaSteccheTappi(int steccheTappi);
	public void aggiornaRotoliNalophan(int rotoliNalophan);
}
