package modello;


public class FactoryUtente {
	
	public static Utente CreaUtente(TipoUtente u, String nome, String email, int oreLavorate) {
		
		switch(u) {
		
			case PANELLEADER:
				return new PanelLeader(email, nome);
			case PANELISTA:
				return new Panelista(email, nome, oreLavorate);
			case INSACCATORE:
				return new Insaccatore(email, nome, oreLavorate);
			default: throw new IllegalArgumentException("Utente non riconosciuto");
		}
		
	}

}
