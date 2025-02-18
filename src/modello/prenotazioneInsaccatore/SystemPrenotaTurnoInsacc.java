
package modello.prenotazioneInsaccatore;

import modello.Insaccatore;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.LocalTime;



public class SystemPrenotaTurnoInsacc {
	private Giorno[] settimana; 
	public static final int maxoremat=4;
	public static final int maxorepom=4;
	
	public SystemPrenotaTurnoInsacc() {
	}
		
	//METODI UTILI:
	
	
	
	//LATO INSACCATORE:
	
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
			turno.setIns(null);
			turno.setStato(false);
	}
	
	

	
	//LATO PANEL LEADER:
	
	//metodo che restituisce una nuova settimana, ovvero un vettore di giorni con l'attributo enum definito e quello dei turni non ancora
	//il parametro "datainizio" dev'essere un lunedì:
	public Giorno[] generaSettimana(LocalDate datainizio) {
		Giorno[] settimana = new Giorno[5];
		//LocalDate proxlunedì = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));   --> mi permette di trovare il prossimo lunedì rispetto al giorno corrente
		//questo prevede:
		//import java.time.DayOfWeek;
		//import java.time.temporal.TemporalAdjusters;	
		for(int i=0;i<settimana.length;i++) {
			settimana[i]= new Giorno(GiorniSettimana.ricavaEnum(i),datainizio.plusDays(i));
		}
		return settimana;
	}
	
	//metodo che permette al panel leader di generare in modo automatico i turni per il giorno selezionato
	public void generaTurniPerGiorno(Giorno giorno, int tempturno) {
		if(tempturno != 60 || tempturno != 30 || tempturno != 90 || tempturno != 120) {
			throw new IllegalArgumentException("La durata di ogni turno indicata non è selezionabile");
		}
		int turnimattino=(maxoremat*60)/tempturno;
		int turnipome=(maxorepom*60)/tempturno;
		LocalTime oramat= LocalTime.of(9,0);
		LocalTime orapom= LocalTime.of(14,0);
		
		
		//organizzo i turni del mattino:
		for(int i=0;i<turnimattino;i++) {
			oramat.plusMinutes(tempturno*i);
			giorno.getTurni().add(new Turno(tempturno,oramat));
			oramat.minusMinutes(tempturno*i);
		}
		
		//organizzo i turni del pomeriggio:
		for(int i=0;i<turnipome;i++) {
			orapom.plusMinutes(tempturno);
			giorno.getTurni().add(new Turno(tempturno,orapom));
			orapom.minusMinutes(tempturno*i);
		}
	}
	
	//metodo che permette di generare i turni per un giorno in base alla fascia oraria e al giorno indicato 
	public void generaTurniPerOrario(LocalTime orainizio, LocalTime orafine, Giorno giorno, int tempturno) {
		if(tempturno != 60 || tempturno != 30 || tempturno != 90 || tempturno != 120) {
			throw new IllegalArgumentException("La durata di ogni turno indicata non è selezionabile");
		}
		
		
	}
	
	//metodo che permette di cancellare i turni di un giorno che rientrano nella fascia oraria indicata:
	public void eliminaTurniPerOrario(LocalTime orainizio, LocalTime orafine, Giorno giorno) {
		Iterator<Turno> i=giorno.getTurni().iterator();
		while(i.hasNext()) {
			Turno t1= i.next();
			int uguali1= t1.getOrainizio().compareTo(orainizio);
			int uguali2= t1.getOrainizio().compareTo(orafine);
			if(uguali1>=0 && uguali2<=0) {
				i.remove();
			}
		}
	}
	
	
	//metodo che permette di generare una settimana in modo automatico con il solo inserimento della durata dei turni per ogni giorno desiderata:
	public void riempiSettimana(int tempturno) {
		
		if(tempturno != 60 || tempturno != 30 || tempturno != 90 || tempturno != 120) {
			throw new IllegalArgumentException("La durata di ogni turno indicata non è selezionabile");
		}
		
		for(int i=0;i<settimana.length;i++) {
			generaTurniPerGiorno(settimana[i],tempturno);
		}
	}

	public static void main(String[] args) {
		
	}

}


