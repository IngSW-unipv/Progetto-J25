package modello;


public class FactoryUtente {
	
	public static Utente CreaUtente(TipoUtente u, String nome, String email) {
		
		switch(u) {
		
			case PANELLEADER:
				return new PanelLeader(email, nome);
			case PANELISTA:
				return new Panelista(email, nome);
			case INSACCATORE:
				return new Insaccatore(email, nome);
			default: throw new IllegalArgumentException("Utente non riconosciuto");
		}
		
	}

}
