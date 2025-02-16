package modello.creazionePanel;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sondaggio {
    private int id;
    private Map<LocalTime, Slot> slot;
    private boolean approvato;
    private LocalTime oraInizio; // queste due ore sono utili per capire se i
    private LocalTime oraFine; // panelisti potranno acnora visualizzare il sondaggio o no,
    // in caso contrario non sarà caricato dal database sui sondaggi visualizzabili dai panelisti.


    public Sondaggio() {
        this.slot = new HashMap<>();
        this.approvato = false;
    }

    public Map<LocalTime, Slot> getSondaggio() {
        return slot;
    }
     public void aggiungiSlot(LocalTime time, Slot slot ){
        this.slot.put(time, slot);
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
