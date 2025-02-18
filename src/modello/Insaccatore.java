package modello;

public class Insaccatore extends Panelista {
	private int orelimite;
	private int limitecanc;
	
	public Insaccatore(String email, String nome,  double orelavorate) {
		super(email, nome, orelavorate);

		limitecanc=0;
	}

	public int getOrelimite() {
		return orelimite;
	}

	public void setOrelimite(int orelimite) {
		this.orelimite = orelimite;
	}
	
	
	
	
	//METODI UTILI: 
	
	
	public Insaccatore(String email, String nome, int orelavorate) {
		super(email, nome, orelavorate);
		
	}

	
	
}
