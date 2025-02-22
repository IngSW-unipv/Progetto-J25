package modello.documento;
import modello.Panelista;
import java.time.LocalDate;

public class Azienda {
	private GestoreStipendi gestore;
	
	public Azienda() {
			
		gestore = new GestoreStipendi();
		}
	
	public void generaDocumento(Panelista p, LocalDate ld, String mese) {
		
		double lordo = gestore.stipendioLordo(p);
		double netto = gestore.stipendioNetto(p);
		double trattenute = gestore.trattenute(p);
		
		DocumentoRiepilogo dr = new DocumentoRiepilogo(p, ld, mese, lordo, netto, trattenute);
		dr.riepilogoUtente();
		
		}
	
	
}
