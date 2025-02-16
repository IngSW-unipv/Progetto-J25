package modello;

public class Panelista extends Utente {
	
	String nome;
	double oreLavoro;
	String email;

    public Panelista(String email, String nome, double oreLavoro) {
        super(email, nome);
		this.oreLavoro = oreLavoro;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
    
    

}
