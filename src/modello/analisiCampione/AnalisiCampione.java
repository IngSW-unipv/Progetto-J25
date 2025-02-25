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
    private LocalTime inizioAnalisi;
    private LocalTime fineAnalisi;
    private double gradazione;


    public AnalisiCampione(int idCampione, int idPanel, LocalTime inizioAnalisi, LocalTime fineAnalisi, double gradazione) {
        
        this.idCampione = idCampione;
        this.idPanel = idPanel;
        this.inizioAnalisi = inizioAnalisi;
        this.fineAnalisi = fineAnalisi;
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

    public LocalTime getInizioAnalisi() {
        return inizioAnalisi;
    }

    public void setInizio_Analisi(LocalTime inizioAnalisi) {
        this.inizioAnalisi = inizioAnalisi;
    }

    public LocalTime getFineAnalisi() {
        return fineAnalisi;
    }

    public void setFine_Analisi(LocalTime fineAnalisi) {
        this.fineAnalisi = fineAnalisi;
    }

    public double getGradazione() {
        return gradazione;
    }

    public void setGradazione(double gradazione) {
        this.gradazione = gradazione;
    }


}

