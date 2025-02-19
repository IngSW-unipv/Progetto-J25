package modello.creazionePanel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Sondaggio {
    private int id;
    private Map<LocalTime, Slot> slot;
    private boolean stato = true;
    private int slotDisponili;
    private LocalTime oraInizio; // queste due ore sono utili per capire se i
    private LocalTime oraFine;
    private LocalDate data; // panelisti potranno acnora visualizzare il sondaggio o no,
    // in caso contrario non sarà caricato dal database sui sondaggi visualizzabili dai panelisti.


    public Sondaggio() {
        this.slot = new HashMap<>();
        this.stato = false;
    }

    public Map<LocalTime, Slot> getSlots() {
        return slot;
    }
     public void aggiungiSlot(LocalTime time, Slot slot ){
        this.slot.put(time, slot);
     }

     public LocalDate getData(){
        return data;
     }
     public void setData(LocalDate data){
        this.data = data;
     }

     public boolean getStato() {
        return stato;
     }

     public void setStato(boolean stato) {
        this.stato = stato;
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
     public void stampa(){
        for( Map.Entry<LocalTime, Slot> slot : this.slot.entrySet() ){
            System.out.println("data slot:" +slot.getValue(). getData());
            System.out.println("orario inizio:" +slot.getValue().getTime());
        }
     }
     public int getId() {
        return id;
     }

     public void setId(int id) {
        this.id = id;
     }

     public int getSlotDisponili() {
        return slotDisponili;
     }

     public void setSlotDisponili(int slotDisponili) {
        this.slotDisponili = slotDisponili;
     }
}
