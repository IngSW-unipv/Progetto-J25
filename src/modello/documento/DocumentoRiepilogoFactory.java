package modello.documento;

import java.time.LocalDate;

import modello.Panelista;

public class DocumentoRiepilogoFactory {

	public static DocumentoRiepilogo creaDoc(Panelista p, LocalDate ld, String mese, IGestoreStipendi gestore) {
		
		double lordo = gestore.stipendioLordo(p);
		double netto = gestore.stipendioNetto(p);
		double trattenute = gestore.trattenute(p);
		
		return new DocumentoRiepilogo(p, ld, mese, lordo, netto, trattenute);
	}
}
