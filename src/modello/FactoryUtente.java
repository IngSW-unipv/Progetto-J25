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

	public static void main(String[] args) {
		
	Utente u1 = FactoryUtente.CreaUtente(TipoUtente.PANELISTA, "tommaso.ghisolfi003@gmail.com", "Tommaso");
	Utente u2 = FactoryUtente.CreaUtente(TipoUtente.PANELLEADER, "tommaso.ghisolfi003@gmail.com", "Tommaso");
	Utente u3 = FactoryUtente.CreaUtente(TipoUtente.INSACCATORE, "tommaso.ghisolfi003@gmail.com", "Tommaso");
	
	System.out.println(u1.getClass());
	System.out.println(u2.getClass());
	System.out.println(u3.getClass());

	}

}
