package modello.prenotazioneInsaccatore;

//Enumerazione per gestire i giorni lavorativi della settimana
//questi devono essere visualizzati nel mio calendario
public enum GiorniSettimana{
	LUNEDI(0),
	MARTEDI(1),
	MERCOLEDI(2),
	GIOVEDI(3),
	VENERDI(4);
	
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
	        case LUNEDI:  return "LUNEDI";
	        case MARTEDI: return "MARTEDI";
	        case MERCOLEDI: return "MERCOLEDI";
	        case GIOVEDI: return "GIOVEDI";
	        case VENERDI:  return "VENERDI";
	        default:   throw new IllegalArgumentException("Giorno non valido: " + giorno);
	    }
	}
	
	

	
}