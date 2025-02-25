package modello.analisiCampione;

import modello.archiviazioneCampione.Campione;
import modello.creazionePanel.*;
import java.time.LocalDate;
import java.time.LocalTime;


public class AnalisiCampione {

    private Campione campione;
    private Panel panel;
    private int idCampione;
    private int idPanel;
    private LocalDate dataAnalisi;
    private LocalTime inizio_Analisi;
    private LocalTime fine_Analisi;
    private double gradazione;


    public AnalisiCampione(LocalTime inizio_Analisi, LocalTime fine_Analisi, double gradazione) {
        
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

    public int getIdCampione() {
        return idCampione;
    }

    public void setIdCampione(int idCampione) {
        this.idCampione = idCampione;
    }

    public int getIdPanel() {
        return idPanel;
    }

    public void setIdPanel(int idPanel) {
        this.idPanel = idPanel;
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

    public LocalTime getInizio_Analisi() {
        return inizio_Analisi;
    }

    public void setInizio_Analisi(LocalTime inizio_Analisi) {
        this.inizio_Analisi = inizio_Analisi;
    }

    public LocalTime getFine_Analisi() {
        return fine_Analisi;
    }

    public void setFine_Analisi(LocalTime fine_Analisi) {
        this.fine_Analisi = fine_Analisi;
    }

    public double getGradazione() {
        return gradazione;
    }

    public void setGradazione(double gradazione) {
        this.gradazione = gradazione;
    }


}

