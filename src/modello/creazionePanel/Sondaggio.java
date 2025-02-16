package modello.creazionePanel;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sondaggio {
    private Map<LocalTime, Slot> slots;
    private LocalTime oraInizio; // queste due ore sono utili per capire se i
    private LocalTime oraFine; // panelisti potranno acnora visualizzare il sondaggio o no,
    // in caso contrario non sarà caricato dal database sui sondaggi visualizzabili dai panelisti.


    public Sondaggio() {
        this.slots = new HashMap<>();
    }

    public Map<LocalTime, Slot> getSlots() {
        return slots;
    }
     public void aggiungiSlot(LocalTime time, Slot slot){
        this.slots.put(time, slot);
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
