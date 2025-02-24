package view.prenotazioneInsaccatore;


import jdbc.dao.max.*;
import modello.prenotazioneInsaccatore.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;





public class TestFacadeSingletonMax {
	//ATTRIBUTI:
	private static TestFacadeSingletonMax istanza;
	//attributi DAO:
	private ITurnoDAO turnoDAO;
	private IGiornoDAO giornoDAO;
	
	//attributi system:
	private SystemPrenotaTurnoInsacc systeminsac;
	
	
	//COSTRUTTORE



	public TestFacadeSingletonMax() {
		//inizializzo i dao:
		this.turnoDAO = new TurnoDAO();
		this.giornoDAO = new GiornoDAO();
	}
	
	//METODI GETTER:
	public static TestFacadeSingletonMax getIstanza() {
		if(istanza==null) {
			istanza = new TestFacadeSingletonMax();
		}
		return istanza;
	}
	
	//lazy initialization dei system:
	public SystemPrenotaTurnoInsacc getSystemPrenotaTurnoInsacc(){
		if(systeminsac==null) {
			//istanzio un nuovo systeminsaccatori
			systeminsac = new SystemPrenotaTurnoInsacc();
			if(!settimanaEsistente()) 
				{generaSettimanaPredefinita();
				istanza.aggiornaSettimanaNelDatabase();
				}
			else
			//carico questo system con l'ultima versione della settimana presente sul db:
			systeminsac.setSettimana(istanza.caricaSettimana());
		}
		return systeminsac;
	}
	
	
	//GETTER DAO:
	
	
	//METODI UTILI:
	
	
	public void generaSettimanaPredefinita() {
	    // Creazione di giorni predefiniti
		LocalDate oggi = LocalDate.now();
		LocalDate lunprox = oggi.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		systeminsac.generaSettAuto(lunprox, 60);
	}
	
	//metodo che verifica se esiste almeno un elemento nel database giorni non nullo, segnala la presenza di dati quindi:
	public boolean settimanaEsistente() {
	    // Verifica se esistono tutti i giorni (LUN-VEN) nel database
	    for (GiorniSettimana giornoEnum : GiorniSettimana.values()) {
	        if (giornoEnum.getValore() < 5) { 
	            // Verifica se il giorno esiste nel DB
	            Giorno giorno = giornoDAO.trova(giornoEnum);
	            if (giorno == null) {
	                return false;
	            }
	        }
	    }
	    return true;
	}
	
	
	
	//metodo per aggiornare la settimana del database:
	public void aggiornaSettimanaNelDatabase() {
	    // recupero la settimana dal systeminsac
	    Giorno[] settimana = systeminsac.getSettimana();

	    // Per ogni giorno della settimana, aggiorna il database
	    for (Giorno giorno : settimana) {
	        // Verifica se il giorno esiste nel database
	        Giorno giornoDB = giornoDAO.trova(giorno.getTipo());

	        if (giornoDB != null) {
	            // Se il giorno esiste, aggiorna i turni
	            ArrayList<Turno> turniDB = turnoDAO.recuperaTurniGiorno(giorno);
	            giornoDB.setTurni(turniDB);
	            giornoDAO.aggiorna(giornoDB);
	        } else {
	            // Se il giorno non esiste, inseriscilo
	            giornoDAO.aggiungi(giorno);
	        }

	        // sempre nello stesso momento, inserisco i turni del giorno inserito secondo l'enum:
	        for (Turno turno : giorno.getTurni()) {
	        	if (turno.getTipoGiorno() == null) {
	                turno.setTipoGiorno(giorno.getTipo()); 
	        	}
	        	if(turno.getId()>0){
	        		turnoDAO.aggiorna(turno);  // aggiorna il tunro nel database se è già presente
	        	}
	        	else
	            turnoDAO.aggiungi(turno);  // aggiungi il turno nel database se non è presente.
	        }
	    }
	}

	
	//metodo per caricare il systeminsacc della facade con la settimana di turni del database
	public Giorno[] caricaSettimana() {
	    Giorno[] settimana = new Giorno[5]; // Vettore fisso di 5 giorni (LUN-VEN)

	    // Recupero tutti i giorni della settimana dal database
	    ArrayList<Giorno> gdb = giornoDAO.trovaTutti();

	    // Mappa per tenere traccia dei giorni trovati
	    HashMap<GiorniSettimana, Giorno> mappaGiorni = new HashMap<>();
	       
	    // Popolo la mappa con i giorni trovati nel DB
	    GiorniSettimana tipo;
	    for (Giorno g : gdb) {
	        tipo = g.getTipo();
	        if (tipo.getValore() < 5) { // Considero solo da lun a ven
	            ArrayList<Turno> turni = turnoDAO.recuperaTurniGiorno(g);
	            g.setTurni(turni);
	            mappaGiorni.put(tipo, g);
	        }
	    }	

	    // Creazione del vettore ordinato di 5 giorni
	    for (int i = 0; i < 5; i++) {
	        GiorniSettimana giornoEnum = GiorniSettimana.ricavaEnum(i);
	        if (mappaGiorni.containsKey(giornoEnum)) {
	            settimana[i] = mappaGiorni.get(giornoEnum);
	        } else {
	            settimana[i] = new Giorno(giornoEnum, null, new ArrayList<>()); // Giorno vuoto se non presente nel DB
	        }
	    }
	    return settimana;
	}
	
	
	
	//metodo per generare una settimana in modo automatico data la durata dei turni:
	public Giorno[] generaSettimanaTurni(LocalDate data,int durata) {
		systeminsac.generaSettAuto(data,durata);
		//il sistema aggiorna il database con la nuova settimana appena generata:
		aggiornaSettimanaNelDatabase();
		return systeminsac.getSettimana();
	}
	
	
	
	
}
	
	
	
	
	
	
	
