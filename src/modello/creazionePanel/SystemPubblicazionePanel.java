package modello.creazionePanel;
import modello.Panelista;
import modello.Utente;
import modello.email.NotificaMessage;
import modello.prenotazionePanel.SystemPrenotazione;

import java.lang.foreign.MemorySegment;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SystemPubblicazionePanel {
    private ArrayList<Panelista> panelisti;
    private ArrayList<Macchinario> macchinariAttvi;
    private ArrayList<Panel> panel;
    private Sondaggio sondaggio;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private int durataSondaggio = 60; // supponiamo che un sondaggio debba durare 5 min
    private int numeroMacchinari;


    public SystemPubblicazionePanel() {
        this.panelisti = new ArrayList<>();
    }

    public void setMacchinari(ArrayList<Macchinario> macchinari) {
        //metodo che verra utilizzato da panelista per settare i macchinari attivi attraverso l'id
        this.macchinariAttvi = macchinari;
        numeroMacchinari = macchinari.size();
    }

    public void setPanelisti(ArrayList<Panelista> panelisti) {
        this.panelisti = panelisti;
    }

    public Sondaggio creaSondaggioAutomatica(int numeroCampioni, LocalDate data) {
        //metodo utile per visualizzare gli slot dopo la creazione automatica
        this.sondaggio = new Sondaggio();
        this.numeroMacchinari = numeroMacchinari;
        numeroCampioni /= numeroMacchinari;  //tengo conto della presenza del numero di macchinari utilizzabili

                LocalTime startTime = LocalTime.of(9,00);
                while (numeroCampioni > 0) {
                    if ((startTime.equals(LocalTime.of(13,00)))) {
                        startTime = startTime.plusMinutes(60);
                    }
                    Slot newslot = new Slot(data, startTime);
                    sondaggio.aggiungiSlot(startTime, newslot);
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
        NotificaMessage notifica = new NotificaMessage("Sondaggio", "Il nuovo sondaggio per i prossimi" +
                " panel è appena stato caricato, accedi alla sezione giusta per prenotarti.");
        notifica.setListaUtenti(panelisti);
        notifica.notificaObserver();
        scheduler.schedule(() -> {
            creazionePanel();  // Esegue il metodo
            scheduler.shutdown();  // Arresta il thread pool
        }, durataSondaggio, TimeUnit.SECONDS);//ATTIVAZIONE TIMER DI CONTEGGIO
       return sondaggio;
        // esegue questo metodo per ottenere la reference per poter salvare in
        // memoria i dati ed eseguire l'invio delle email a tutti i panelisti
        //UNA VOLTA CHE IL TIMER SARà FINITO  VERRà ESEGUITO IN AUTOMATICO IL METODO CREAZIONE DEL PANEL
    }

    public ArrayList<Panelista> getPanelisti() {
        return panelisti;
    }

    public void creazionePanel(){
        this.panel = new ArrayList<>();
        for (Map.Entry<LocalTime, Slot> entry : sondaggio.getSlots().entrySet()){
            for(Macchinario m: macchinariAttvi){
                Panel p = new Panel(entry.getValue().getTime(), m, entry.getValue().getData());
                // suppongo che i panelisti vengano inseriti gia in ordine decrescente per ore nella lista dei prenotati allo slot
                for(int i = 0; i<entry.getValue().getPrenotati().size(); i++){
                    p.addpanelista(entry.getValue().getPrenotati().get(i));
                }
                panel.add(p);
            }
        }
        pubblicazionePanel();

       /* for(Panel p: panel){
            p.stampa();
        } UTILE PER IL TEST
            */
    }

    public void pubblicazionePanel(){
        NotificaMessage notifica  = new NotificaMessage("Formazioni definitive", "Sono appena stati pubblicati le formazione definitive per " +
                "i prossimi panel, acceddi all'area riservata per vedere se sei stato scelto.");
        notifica.setListaUtenti(panelisti);
        notifica.notificaObserver();
    }

    public static void main(String[] args) {
       /* Panelista utente1 = new Panelista("khawlaouaadou1@gmail.com", "khawla", 15);
        Panelista utente2 = new Panelista("khawla.ouaadou01@universitadipavia.it", "khawla", 40);
        Panelista utente3 = new Panelista("araldimaxim01@gmail.com", "maxim", 50);
        Panelista utente4 = new Panelista("araldi.maxim01@universitadipavia", "maxim", 60);
        ArrayList<Panelista> utentes = new ArrayList<>();
        utentes.add(utente1);
        utentes.add(utente2);
        utentes.add(utente3);
        utentes.add(utente4);

        ArrayList<Macchinario> macchinari = new ArrayList<>();
        Macchinario m1 = new Macchinario(01, 4);
        macchinari.add(m1);

        SystemPubblicazionePanel systemPubblicazionePanel = new SystemPubblicazionePanel();
        systemPubblicazionePanel.setMacchinari(macchinari);
        systemPubblicazionePanel.setPanelisti(utentes);
        Sondaggio s1 = systemPubblicazionePanel.creaSondaggioAutomatica(10, LocalDate.of(2025, 2, 18));
        systemPubblicazionePanel.pubblicazioneSondaggio();

        s1.setId(03);
        ArrayList<Sondaggio> sondaggi = new ArrayList<>();
        sondaggi.add(s1);

        SystemPrenotazione prenotazione = new SystemPrenotazione();
        prenotazione.setSondaggi(sondaggi);

        prenotazione.prenotazione(03, LocalTime.of(9,00), utente1);
        prenotazione.prenotazione(03, LocalTime.of(9,00), utente2);
        prenotazione.prenotazione(03, LocalTime.of(9,00), utente3);
        prenotazione.prenotazione(03, LocalTime.of(9,00), utente4);

        s1.stampa();*/


    }

}


