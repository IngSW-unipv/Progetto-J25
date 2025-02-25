package controller;

import modello.analisiCampione.SystemAnalisi;
import modello.analisiCampione.AnalisiCampione;
import modello.archiviazioneCampione.Campione;
import modello.creazionePanel.Panel;

import java.sql.SQLException;

public class AnalisiController {

    private SystemAnalisi systemAnalisi;

    public AnalisiController() {
        this.systemAnalisi = new SystemAnalisi();
    }

    // Metodo per l'inserimento di un'analisi
    public boolean inserisciAnalisi(Campione campione, Panel panel, AnalisiCampione analisi){
        try {
            return systemAnalisi.inserisciAnalisi(campione, panel, analisi);
        } catch (SQLException e) {
            System.err.println("Errore durante l'inserimento dell'analisi" + e.getMessage());
            return false;
        }
    }

    // Metodo per la modifica di un'analisi
    public boolean modificaAnalisi(Campione campione, Panel panel, AnalisiCampione analisi){
        try {
            return systemAnalisi.modificaAnalisi(campione, panel, analisi);
        } catch (SQLException e) {
            System.err.println("Errore durante la modifica dell'analisi" + e.getMessage());
            return false;
        }
    }

    // Metodo per l'eliminazione di un'analisi
    public boolean eliminaAnalisi(Campione campione, Panel panel, AnalisiCampione analisi){
        try {
            return systemAnalisi.eliminaAnalisi(campione, panel, analisi);
        } catch (SQLException e) {
            System.err.println("Errore durante l'eliminazione dell'analisi" + e.getMessage());
            return false;
        }
    }
    
}
