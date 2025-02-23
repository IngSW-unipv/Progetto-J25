package modello.documento;
import modello.Panelista;

import java.time.LocalDate;


public class Azienda {
	private IGestoreStipendi gestore;
	private SystemDocumento systemDocumento;
	
	public Azienda(IGestoreStipendi gestore, SystemDocumento systemDocumento) {
			
		this.gestore = gestore;
		this.systemDocumento = systemDocumento;
		
		}
	
	public void generaDocumento(int id, LocalDate ld, String mese) {
		
		Panelista p = systemDocumento.restituisciPanelista(id);
		
		p.setOreLavoro(systemDocumento.oreLavoroPanelista(id, mese));
		
		double lordo = gestore.stipendioLordo(p);
		double netto = gestore.stipendioNetto(p);
		double trattenute = gestore.trattenute(p);
		
		DocumentoRiepilogo dr = new DocumentoRiepilogo(p, ld, mese, lordo, netto, trattenute);
		dr.riepilogoUtente();
		
		}
	
	
	
	
}
