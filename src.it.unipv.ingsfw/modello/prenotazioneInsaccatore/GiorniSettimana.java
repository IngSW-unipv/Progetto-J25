package modello.prenotazioneInsaccatore;

//Enumerazione per gestire i giorni lavorativi della settimana
//questi devono essere visualizzati nel mio calendario
public enum GiorniSettimana{
	LUN(0),
	MART(1),
	MERC(2),
	GIOV(3),
	VEN(4);
	
	//attributi:
	int valore;
	
	//costruttore
	private GiorniSettimana(int valore) {
		this.valore = valore;
	}
	
	//setter e getter:
	public int getValore() {
		return valore;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}
	
	//metodo per ottenere il valore di tipo enum indicando il solo valore numerico
	public static GiorniSettimana ricavaEnum(int i) {
		for(GiorniSettimana g : GiorniSettimana.values()) {
			if(g.getValore()==i) return g;
		}
		throw new IllegalArgumentException("Questo valore non è valido: " + i );
	}
	
	public static String stampaEnum(GiorniSettimana giorno) {
	    switch(giorno) {
	        case LUN:  return "LUNEDÌ";
	        case MART: return "MARTEDÌ";
	        case MERC: return "MERCOLEDÌ";
	        case GIOV: return "GIOVEDÌ";
	        case VEN:  return "VENERDÌ";
	        default:   throw new IllegalArgumentException("Giorno non valido: " + giorno);
	    }
	}
	
	

	
}