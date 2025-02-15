package modello.creazionePanel;
import modello.Panelista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Slot {
    private ArrayList<Panelista> prenotati;
    private LocalDate data;
    private LocalTime orarioInizio;

    public Slot(LocalDate data, LocalTime time) {
        prenotati = new ArrayList<>();
        this.data = data;
        this.orarioInizio = time;
        prenotati = new ArrayList<>();
    }
    public ArrayList<Panelista> getPrenotati() {
    	return prenotati;
    }
    public void addPrenotati(Panelista prenotati) {
        this.prenotati.add(prenotati);
    }

    public LocalDate getData() {

        return data;
    }
    public LocalTime getTime() {

        return orarioInizio;
    }

    public void aggiungiPrenotato(Panelista p) {
    	
    	prenotati.add(p);
    }
    
    public void rimuoviPrenotato(Panelista p) {
    	
    	prenotati.remove(p);
    }
    


}
