package modello.documento;
import modello.Panelista;

import java.time.LocalDate;


public class Azienda {
	private IGestoreStipendi gestore;
	private ISystemDocumento systemDocumento;
	
	public Azienda(IGestoreStipendi gestore, ISystemDocumento systemDocumento) {
			
		this.gestore = gestore;
		this.systemDocumento = systemDocumento;
		
		}
	
	public void generaDocumento(int id, LocalDate ld, String mese) {
		
		Panelista p = systemDocumento.restituisciPanelista(id);
		
		p.setOreLavoro(systemDocumento.oreLavoroPanelista(id, mese));
		
		DocumentoRiepilogo dr = DocumentoRiepilogoFactory.creaDoc(p, ld, mese, gestore);
		dr.riepilogoUtente();
		
		}
	
	
	
	
}
