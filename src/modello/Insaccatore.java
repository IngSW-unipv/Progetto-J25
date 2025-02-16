package modello;

public class Insaccatore extends Panelista {
	private int orelimite;
	private int limitecanc;
	
	public Insaccatore(String email, String nome, int orelimite) {
		super(email, nome);
		this.orelimite = orelimite; 
		limitecanc=0;
	}

	public int getOrelimite() {
		return orelimite;
	}

	public void setOrelimite(int orelimite) {
		this.orelimite = orelimite;
	}
	
	
	
	
	//METODI UTILI: 
	
	
}
