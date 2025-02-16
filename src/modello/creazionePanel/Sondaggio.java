package modello.creazionePanel;

import java.time.LocalTime;
import java.util.ArrayList;

public class Sondaggio {
	private int id;
    private ArrayList<Slot> slot;
    private boolean approvato;
    private LocalTime oraInizio; // queste due ore sono utili per capire se i
    private LocalTime oraFine; // panelisti potranno acnora visualizzare il sondaggio o no,
    // in caso contrario non sarà caricato dal database sui sondaggi visualizzabili dai panelisti.


    public Sondaggio(int id) {
        this.slot = new ArrayList<>();
        this.approvato = false;
        this.id = id;
    }

    public ArrayList<Slot> getSondaggio() {
        return slot;
    }
     public void aggiungiSlot(Slot slot ){
        this.slot.add(slot);
     }

     public ArrayList<Slot> getSondaggioSoldi() {
        return slot;
     }

     public boolean getApprovato() {
        return approvato;
     }

     public void setApprovato(boolean approvato) {
        this.approvato = approvato;
     }

     public LocalTime getOraInizio() {
        return oraInizio;
     }
     public void setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
     }

     public LocalTime getOraFine() {
        return oraFine;
     }
     public void setOraFine(LocalTime oraFine) {
        this.oraFine = oraFine;
     }
}
