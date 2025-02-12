package modello.creazionePanel;
import modello.Panelista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SystemPubblicazionePanel {
    private ArrayList<Panelista> panelisti;
    private Sondaggio sondaggio;

    public SystemPubblicazionePanel() {
        this.panelisti = new ArrayList<>();
    }

    public void creaSondaggio(int numeroCampioni, TipoCreaSondaggio tipoCreaSondaggio, int numeroMacchinari) {
        this.sondaggio = new Sondaggio();
        numeroCampioni /= numeroMacchinari;  //tengo conto della presenza del numero di macchinari utilizzabili

        switch(tipoCreaSondaggio){
            case manuale:
                System.out.println("manuale"); // da sistemare!!
                break;
            case automatico:
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
        }
    }

    public Sondaggio getSondaggio() {
        return sondaggio; //metodo utile per visualizzare gli slot e confermare per panelista
    }

    //devo capire come gestire la versione manuale, ma penso che convenga ritornare
    // subito la reference di sondaggio e permettere al panelLeader di aggiungere a suo piacimento
    // gli slot. (usando controller e view)

    public void pubblicazioneSondaggio(){
        if (sondaggio.getApprovato() == true) {
            //..
        }
    }
    public void creazionePanel(){
    }

    public void pubblicazionePanel(){
    }
}
