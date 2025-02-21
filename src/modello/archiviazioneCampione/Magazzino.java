package modello.archiviazioneCampione;

public class Magazzino {
	
	private int numeroSacche;

	public Magazzino(int numeroSacche) {
		
		if(numeroSacche < 0 ) {
			
			throw new IllegalArgumentException("Il numero di sacche deve essere positivo");
		}
		
		this.numeroSacche = numeroSacche;
	}

	public int getNumeroSacche() {
		return numeroSacche;
	}

	public void setNumeroSacche(int numeroSacche) {
		this.numeroSacche = numeroSacche;
	}
	
	public int decrementaSacche(int numeroCampione) {
		
		if(numeroCampione > this.numeroSacche) {
			
			throw new IllegalArgumentException("Non ci sono abbastanza sacche");
		}else if(numeroCampione < 0) {
			
			throw new IllegalArgumentException("Il numero di campioni deve essere positivo");
		}
		
		this.numeroSacche = this.numeroSacche - numeroCampione;
		
		return this.numeroSacche;
	}
	
	
	
	
}
