package modello.prenotazioneInsaccatore;

import modello.Insaccatore;


public class SystemPrenotaTurnoInsacc {
	private Giorno[] giorni; 
	private enum GiorniSettimana{LUN, MART, MERC, GIOV, VEN};

	
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
	
	
	//metodo che permette al panel leader di generare in modo automatico i turni per ogni giorno
	public void generaTurniAuto(int oramin,int oramax, int tempturno) {
		
	}
	
	public void generaTurniMan() {
		
	}
	
	
	
	
}
