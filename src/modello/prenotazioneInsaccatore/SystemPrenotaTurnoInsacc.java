
package modello.prenotazioneInsaccatore;

import modello.Insaccatore;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.HashSet;



public class SystemPrenotaTurnoInsacc {
	private Giorno[] settimana; 
	private static final int maxoremat=4;
	private static final int maxorepom=4;
	private static final int maxgiorni =5;
	private static final LocalTime  iniziomattino =LocalTime.of(9,0);
	private static final LocalTime iniziopome = LocalTime.of(14, 0);
	
	private static final Set<Integer> tempiammessi = Set.of(30,60,90,120); 
	
	
	public SystemPrenotaTurnoInsacc() {
		this.settimana = new Giorno[maxgiorni];
	}
		
	public SystemPrenotaTurnoInsacc(Giorno[] settimana) {
		this.settimana = settimana;
	}

	
	//SETTER GETTERS:
	
		
	
	//METODI UTILI:
	public Giorno[] getSettimana() {
		return settimana;
	}

	public void setSettimana(Giorno[] settimana) {
		this.settimana = settimana;
	}

	
	
	
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
		//LocalDate proxlunedì = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));   --> mi permette di trovare il prossimo lunedì rispetto al giorno corrente
		//questo prevede:
		//import java.time.DayOfWeek;
		//import java.time.temporal.TemporalAdjusters;	
		settimana = new Giorno[maxgiorni];
		for(int i=0;i<settimana.length;i++) {
			settimana[i]= new Giorno(GiorniSettimana.ricavaEnum(i),datainizio.plusDays(i),new ArrayList<Turno>());
		}
		return settimana;
	}
	
	//metodo che permette al panel leader di generare in modo automatico i turni per il giorno selezionato
	public void generaTurniPerGiorno(Giorno giorno, int tempturno) {
		if(!tempiammessi.contains(tempturno)) {
			throw new IllegalArgumentException("La durata di ogni turno indicata non è selezionabile");
		}
		else {
			int turnimattino=(maxoremat*60)/tempturno;
			int turnipome=(maxorepom*60)/tempturno;

			//organizzo i turni del mattino:
			for(int i=0;i<turnimattino;i++) {
				LocalTime oramat=iniziomattino.plusMinutes(i*tempturno);
				giorno.aggiungiTurno(new Turno(tempturno,oramat));
				giorno.getTurni().get(i).setTipoGiorno(giorno.getTipo());
			}
		
			//organizzo i turni del pomeriggio:
			for(int i=0;i<turnipome;i++) {
				LocalTime orapom=iniziopome.plusMinutes(i*tempturno);
				giorno.aggiungiTurno(new Turno(tempturno,orapom));
				giorno.getTurni().get(i).setTipoGiorno(giorno.getTipo());
			}
		}
	}
	
	//metodo che permette di generare i turni per un giorno in base alla fascia oraria e al giorno indicato 
	public void generaTurniPerOrario(LocalTime orainizio, LocalTime orafine, Giorno giorno, int tempturno) {
		if(!tempiammessi.contains(tempturno)) {
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
		
		if(!tempiammessi.contains(tempturno)) {
			throw new IllegalArgumentException("La durata di ogni turno indicata non è selezionabile");
		}
		else
		for(int i=0;i<settimana.length;i++) {
			generaTurniPerGiorno(settimana[i],tempturno);
		}
	}
	
	//metodo che mi genera la settimana in modo automatico partendo da 0: 
	public void generaSettAuto(LocalDate datainizio, int tempturno) {
		this.settimana= generaSettimana(datainizio);
		riempiSettimana(tempturno);
	}
	
	
	
	//METODI DI TEST, MI SERVONO PER CAPIRE SE GLI OGGETTI VENGONO CREATI EFFETTIVAMENTE
	public void stampaEnum() {
		for(int i=0;i<settimana.length;i++) {
			System.out.println(settimana[i]);
		}
	}
	
	public void stampaEnumSett() {
		GiorniSettimana giorno;
		for(int i=0;i<settimana.length;i++) {
			giorno = settimana[i].getTipo();
		System.out.println(GiorniSettimana.stampaEnum(giorno) +"  "+ settimana[i].getData());
		}
	}
	
	


}


