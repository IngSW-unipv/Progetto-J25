package modello;

public class Panelista extends Utente {
	
	String nome;

    public Panelista(String email, String nome) {
        super(email);
        this.nome = nome;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
    
    

}
