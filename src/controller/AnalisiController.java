package controller;

import modello.analisiCampione.SystemAnalisi;
import modello.analisiCampione.AnalisiCampione;

import java.sql.SQLException;

public class AnalisiController {

    private SystemAnalisi systemAnalisi;

    public AnalisiController() {
        this.systemAnalisi = new SystemAnalisi();
    }

    // Metodo per l'inserimento di un'analisi
    public boolean inserisciAnalisi(int idCampione, int idPanel, AnalisiCampione analisi){
        try {
            return systemAnalisi.inserisciAnalisi(idCampione, idPanel, analisi);
        } catch (SQLException e) {
            System.err.println("Errore durante l'inserimento dell'analisi" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Metodo per la modifica di un'analisi
    public boolean modificaAnalisi(int idCampione, int idPanel, AnalisiCampione analisi){
        try {
            return systemAnalisi.modificaAnalisi(idCampione, idPanel, analisi);
        } catch (SQLException e) {
            System.err.println("Errore durante la modifica dell'analisi" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Metodo per l'eliminazione di un'analisi
    public boolean eliminaAnalisi(int idCampione, int idPanel, AnalisiCampione analisi){
        try {
            return systemAnalisi.eliminaAnalisi(idCampione, idPanel, analisi);
        } catch (SQLException e) {
            System.err.println("Errore durante l'eliminazione dell'analisi" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
}
