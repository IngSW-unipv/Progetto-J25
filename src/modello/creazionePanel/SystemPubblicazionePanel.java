package modello.creazionePanel;
import modello.Panelista;

import java.lang.foreign.MemorySegment;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SystemPubblicazionePanel {
    private ArrayList<Panelista> panelisti;
    private Sondaggio sondaggio;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private int durataSondaggio = 300;
    private int numeroMacchinari;
    private ArrayList<Macchinario> macchinari;


    public SystemPubblicazionePanel() {
        this.panelisti = new ArrayList<>();
    }

    public void setMacchinari(ArrayList<Macchinario> macchinari) {
        //metodo che verra utilizzato da panelista per settare i macchinari attivi attraverso l'id
        this.macchinari = macchinari;
        numeroMacchinari = macchinari.size();
    }

    public Sondaggio creaSondaggioAutomatica(int numeroCampioni) {
        //metodo utile per visualizzare gli slot dopo la creazione automatica
        this.sondaggio = new Sondaggio();
        this.numeroMacchinari = numeroMacchinari;
        numeroCampioni /= numeroMacchinari;  //tengo conto della presenza del numero di macchinari utilizzabili

                LocalTime startTime = LocalTime.of(9,00);
                while (numeroCampioni > 0) {
                    if ((startTime.equals(LocalTime.of(13,00)))) {
                        startTime = startTime.plusMinutes(60);
                    }
                    Slot newslot = new Slot(LocalDate.now().plusDays(1), startTime);
                    sondaggio.aggiungiSlot(newslot);
                    numeroCampioni = numeroCampioni - (10 * numeroMacchinari);
                    startTime = startTime.plusMinutes(120);
                }
                return sondaggio;
    }

    public Sondaggio creazioneSondaggioManuale() {
        this.sondaggio = new Sondaggio();
        return sondaggio;
    }

    public Sondaggio getSondaggio() {
        return sondaggio;
    }

    public Sondaggio pubblicazioneSondaggio(){
        //il controllore una volta che il panelista clicca su conferma
        // esegue questo metodo per ottenere la reference per poter salvare in memoria i dati ed eseguire l'invio delle email
        //a tutti i panelisti
        scheduler.schedule(this::creazionePanel, durataSondaggio, TimeUnit.SECONDS); //ATTIVAZIONE TIMER DI CONTEGGIO
        //UNA VOLTA CHE IL TIMER SARà FINITO  VERRà ESEGUITO IN AUTOMATICO IL METODO CREAZIONE DEL PANEL
       return sondaggio;
    }

    public ArrayList<Panelista> getPanelisti() {
        return panelisti;
    }

    public void creazionePanel(){
        int i;
        for (Map.Entry<LocalTime, Slot> entry : sondaggio.getSlots().entrySet()){
            for(i=0; i<numeroMacchinari; i++){
                 // devo capire come gestire la situazione dei macchinari come
                // faccio a capire quali sono attivi e quali no?

            }
        }
    }

    public void pubblicazionePanel(){
    }
}
