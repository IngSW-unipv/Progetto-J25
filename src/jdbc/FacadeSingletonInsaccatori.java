package jdbc;


import jdbc.bean.IUserDAO;
import jdbc.bean.UserDAO;
import jdbc.dao.max.*;
import modello.Insaccatore;
import modello.gestioneInventario.Inventario;
import modello.prenotazioneInsaccatore.*;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;


public class FacadeSingletonInsaccatori {
	//ATTRIBUTI:
	private static FacadeSingletonInsaccatori istanza;
	//attributi DAO:
	private ITurnoDAO turnoDAO;
	private IGiornoDAO giornoDAO;
	private IUserDAO userDAO;
	private IMagazzinoDAO magDAO;
	
	//attributi system:
	private SystemPrenotaTurnoInsacc systeminsac;
	private Inventario inventario;
	
	
	//COSTRUTTORE



	public FacadeSingletonInsaccatori() {
		//inizializzo i dao:
		this.turnoDAO = new TurnoDAO();
		this.giornoDAO = new GiornoDAO();
		this.userDAO = new UserDAO();
		this.magDAO = new MagazzinoDAO();
	}
	
	//METODI GETTER:
	public static FacadeSingletonInsaccatori getIstanza() {
		if(istanza==null) {
			istanza = new FacadeSingletonInsaccatori();
		}
		return istanza;
	}
	
	public Inventario getInventario() {
		if(inventario==null) {
			//istanzio un nuovo inventario per la facade, dovrà rifarsi a quella presente nel database
			inventario = magDAO.trovaInventario();
		}
		return inventario;
	}
	
	
	
	//lazy initialization dei system:
	public SystemPrenotaTurnoInsacc getSystemPrenotaTurnoInsacc(){
		if(systeminsac==null) {
			//istanzio un nuovo systeminsaccatori
			systeminsac = new SystemPrenotaTurnoInsacc();
			if(!settimanaEsistente()) {
					generaSettimanaPredefinita(); //da testare la sua cancellazione!
					istanza.aggiornaSettimanaNelDatabase(); //il sistema aggiorna il database con la nuova settimana appena generata:
				}
			else {
			//carico questo system con l'ultima versione della settimana presente sul db:
			systeminsac.setSettimana(istanza.caricaSettimana());
			}
		}
		return systeminsac;
	}
	
	
	//METODI UTILI INVENTARIO:
	
	

	
	
	
	//METODI UTILI SETTIMANA TURNI:
	
	
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
		giornoDAO.svuotaSettimana();
		aggiornaSettimanaNelDatabase();
		return systeminsac.getSettimana();
	}
	
	
	//metodo per eseguire la prenotazione ad un insaccatore al turno selezionato:
	public void prenotazioneAlTurno(int idIns,Turno t) {
//		Insaccatore ins = userDAO.getInsaccatore(idIns);
//		Turno taggiornato = systeminsac.prenotaTurno(ins, t);
//		turnoDAO.gestisciTurnoInsac(true,taggiornato.getId(),ins.getId());
		turnoDAO.gestisciTurnoInsac(true,t.getId(),idIns);

	}
	
	//metodo per eseguire una cancellazione dal turno all'insaccatore:
	public void cancellazioneAlTurno(int idIns,Turno t) {
//		Insaccatore ins = userDAO.getInsaccatore(idIns);
//		Turno taggiornato = systeminsac.cancellaTurno(t);
//		turnoDAO.gestisciTurnoInsac(false,taggiornato.getId(),ins.getId());
		turnoDAO.gestisciTurnoInsac(false,t.getId(),idIns);
	}
	
	
	
	
}
	
	
	
	
	
	
	
