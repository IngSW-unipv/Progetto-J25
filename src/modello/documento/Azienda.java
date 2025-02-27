package modello.documento;
import modello.Panelista;
import modello.email.NotificaMessage;

import java.time.LocalDate;
import java.util.ArrayList;


public class Azienda {
	
	private IGestoreStipendi gestore;
	private ISystemDocumento systemDocumento;
	private ArrayList<Panelista> panelista;
	
	public Azienda(IGestoreStipendi gestore, ISystemDocumento systemDocumento) {
			
		this.gestore = gestore;
		this.systemDocumento = systemDocumento;
		this.panelista = new ArrayList<>();
		
		}
	
	public void generaDocumento(int id, LocalDate ld, String mese) {
		
		Panelista p = systemDocumento.restituisciPanelista(id);
		
		p.setOreLavoro(systemDocumento.oreLavoroPanelista(id, mese));
		
		DocumentoRiepilogo dr = DocumentoRiepilogoFactory.creaDoc(p, ld, mese, gestore);
		dr.riepilogoUtente();
		
		
		NotificaMessage notifica = new NotificaMessage("Documento di riepilogo", "Il documento di riepilogo è stato generato");
		panelista.add(p);
        notifica.setListaUtenti(panelista);
        notifica.notificaObserver();
		
		}
	
	
	
	
}
