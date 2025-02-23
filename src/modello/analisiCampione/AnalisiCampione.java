package modello.analisiCampione;

import modello.archiviazioneCampione.Campione;
import modello.creazionePanel.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class AnalisiCampione {

    private Campione campione;
    private Panel panel;
    private LocalDate dataAnalisi;
    private LocalDateTime inizio_Analisi;
    private LocalDateTime fine_Analisi;
    private double gradazione;


    public AnalisiCampione(LocalDate dataAnalisi, LocalDateTime inizio_Analisi, LocalDateTime fine_Analisi, double gradazione) {
        
        this.dataAnalisi = dataAnalisi;
        this.inizio_Analisi = inizio_Analisi;
        this.fine_Analisi = fine_Analisi;
        this.gradazione = gradazione;

    }

    
    public Campione getCampione() {
        return campione;
    }

    public void setCampione(Campione campione) {
        this.campione = campione;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public LocalDate getDataAnalisi() {
        return dataAnalisi;
    }

    public void setDataAnalisi(LocalDate dataAnalisi) {
        this.dataAnalisi = dataAnalisi;
    }

    public LocalDateTime getInizio_Analisi() {
        return inizio_Analisi;
    }

    public void setInizio_Analisi(LocalDateTime inizio_Analisi) {
        this.inizio_Analisi = inizio_Analisi;
    }

    public LocalDateTime getFine_Analisi() {
        return fine_Analisi;
    }

    public void setFine_Analisi(LocalDateTime fine_Analisi) {
        this.fine_Analisi = fine_Analisi;
    }

    public double getGradazione() {
        return gradazione;
    }

    public void setGradazione(double gradazione) {
        this.gradazione = gradazione;
    }


}

