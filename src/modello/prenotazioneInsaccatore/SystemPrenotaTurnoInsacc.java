package modello.prenotazioneInsaccatore;

import modello.Insaccatore;
import java.util.ArrayList;

public class SystemPrenotaTurnoInsacc {
	private Giorno[] settimana; 
	
	
	public SystemPrenotaTurnoInsacc() {
	}
		
	//METODI UTILI
	
	//metodo per far prenotare un turno all'insaccatore
	public void prenotaTurno(Insaccatore ins, Turno turno){
		if(turno.isStato()!=true && turno.getIns()==null) {
			turno.setIns(ins);
			turno.setStato(true);
		}
		else System.out.println("non puoi prenotarti");
	}
	
	//metodo per togliere la prenotazione di un turno all'insaccatore
	public void cancellaTurno(Turno turno) {
		if(turno.isStato()==true ) {
			turno.setIns(null);
			turno.setStato(false);
		}
	}
	
	//metodo che restituisce una nuova settimana, ovvero un vettore di giorni con l'attributo enum definito e quello dei turni non ancora
	public Giorno[] generaSettimana() {
		Giorno[] settimana = new Giorno[5];
		for(int i=0;i<settimana.length;i++) {
			settimana[i]= new Giorno(GiorniSettimana.ricavaEnum(i));
		}
		return settimana;
	}
	
	//metodo che permette al panel leader di generare in modo automatico i turni per il giorno selezionato
	public void generaTurniGiornoAuto(int numturni, int tempturno) {
		
	}
	
	public void generaTurniGiornoMan() {
		
	}
	
	public static void main(String[] args) {
		
	}

}


