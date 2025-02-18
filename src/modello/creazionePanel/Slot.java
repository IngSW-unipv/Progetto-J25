package modello.creazionePanel;
import modello.Panelista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Slot {
    private List<Panelista> prenotati;
    private LocalDate data;
    private LocalTime orarioInizio;
    private int idSlot;
    private int idSondaggio;

    public Slot(LocalDate data, LocalTime time) {
        prenotati = new ArrayList<>();
        this.data = data;
        this.orarioInizio = time;
    }
    public List<Panelista> getPrenotati() {
    	return prenotati;
    }
    public void addPrenotato(Panelista prenotato) {
        this.prenotati.add(prenotato);
        prenotati.sort((p1, p2) -> Double.compare(p2.getOreLavoro(), p1.getOreLavoro()));
    }

    public LocalDate getData() {

        return data;
    }
    public LocalTime getTime() {

        return orarioInizio;
    }
    
    public void rimuoviPrenotato(Panelista p) {
    	
    	prenotati.remove(p);
    }
    


}
