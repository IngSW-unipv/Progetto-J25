package modello.creazionePanel;
import jdbc.FacedeSingletonDB;
import modello.Panelista;
import modello.email.NotificaMessage;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private int durataSondaggio = 30; // supponiamo che un sondaggio debba durare 5 min
    private int numeroMacchinari;



    public SystemPubblicazionePanel() {
        this.panelisti = new ArrayList<>();
        this.macchinariAttvi = new ArrayList<>();
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
        sondaggio.setData(data);
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

    public Sondaggio creazioneSondaggioManuale(LocalDate data, ArrayList<Slot> slots) {
        this.sondaggio = new Sondaggio();
        sondaggio.setData(data);
        for (Slot slot : slots) {
            sondaggio.aggiungiSlot(slot.getTime(), slot);
        }
        return sondaggio;
    }

    public Sondaggio getSondaggio() {
        return sondaggio;
    }
    public void pubblicazioneSondaggio(Sondaggio sondaggio) {
        boolean sondaggioInserito = false;
        boolean slotsInseriti = false;

        // Inserimento del sondaggio
        sondaggio.setOraInizio(LocalTime.now());
       sondaggio.setId( FacedeSingletonDB.getInstance().getSondaggioDAO().insertSondaggio(sondaggio));
        if (sondaggio.getId() > 0) {  // Verifica che l'inserimento del sondaggio sia andato a buon fine
            sondaggioInserito = true;

            // Assegna l'ID del sondaggio agli slot
            for (Map.Entry<LocalTime, Slot> entry : sondaggio.getSlots().entrySet()) {
                entry.getValue().setIdSondaggio(sondaggio.getId());
            }

            // Inserimento degli slot
            slotsInseriti = FacedeSingletonDB.getInstance().getSlotDAO().insertSlots(sondaggio.getSlots());
        }

        // Se sia il sondaggio che gli slot sono stati inseriti correttamente, invia la notifica
        if (sondaggioInserito && slotsInseriti) {
            NotificaMessage notifica = new NotificaMessage("Sondaggio", "Il nuovo sondaggio per i prossimi panel è appena stato caricato, accedi alla sezione giusta per prenotarti.");
            notifica.setListaUtenti(panelisti);
            notifica.notificaObserver(); // Invio della notifica
        }

        // Pianifica la creazione del panel solo se le operazioni sono andate a buon fine
        if (sondaggioInserito && slotsInseriti) {
            scheduler.schedule(() -> {
                creazionePanel();  // Esegue il metodo creazionePanel
                scheduler.shutdown();  // Arresta il thread pool
            }, durataSondaggio, TimeUnit.SECONDS); // Attivazione del timer
        }
    }

    public ArrayList<Panelista> getPanelisti() {
        return panelisti;
    }

    public void creazionePanel(){
        this.panel = new ArrayList<>();
        FacedeSingletonDB.getInstance().getPrenotati(sondaggio.getSlots());

        for (Map.Entry<LocalTime, Slot> entry : sondaggio.getSlots().entrySet()){
            entry.getValue().ordinadPrenotati();
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
        for (Panel p : panel){
            FacedeSingletonDB.getInstance().getPanelDAO().addPanel(p);
        }
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

        /*SystemPubblicazionePanel sp = FacedeSingletonDB.getInstance().getSystemPubblicazionePanel();
        ArrayList<Panelista> panelistas = sp.getPanelisti();
        Panelista p = new Panelista("khawlaouaadou1@gmail.com", "khawla", "ouaadou",
                "marocco", LocalDate.of(2003,04,14), "jfjhfhf", "via cefaly", 50);
        panelistas.add(p);
        Sondaggio sondaggio1 = sp.creaSondaggioAutomatica(20, LocalDate.now().plusDays(3));
        sp.pubblicazioneSondaggio(sondaggio1);

        /*FacedeSingletonDB.getInstance().getPanelDAO().chiudiPanel(1, LocalTime.of(11, 00));
        FacedeSingletonDB.getInstance().getPanelDAO().chiudiPanel(2, LocalTime.of(12, 00)); */

        //Test per la visualizzazione dei sondaggi attivi
       ArrayList<Sondaggio> sondaggi = FacedeSingletonDB.getInstance().popolaSondaggi();
        for (Sondaggio s : sondaggi) {
            s.stampa();
        }




    }

}


