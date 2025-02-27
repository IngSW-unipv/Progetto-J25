package modello.analisiCampione;

import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.FacadeSingletonDB;

/*
 * Questa classe rappresenta il sistema di analisi di un campione.
 */

public class SystemAnalisi {


    public SystemAnalisi() {}

    
    // Aggiunta di una nuova analisi nel database
    public boolean inserisciAnalisi(int idCampione, int idPanel, AnalisiCampione analisi) throws SQLException {
        return FacadeSingletonDB.getInstance().getAnalisiDAO().insertAnalisi(idCampione, idPanel, analisi);
    }

    // Modifica un'analisi nel database
    public boolean modificaAnalisi(int idCampione, int idPanel, AnalisiCampione analisi)  throws SQLException {
        return FacadeSingletonDB.getInstance().getAnalisiDAO().updateAnalisi(idCampione, idPanel, analisi);
    }

    // Elimina un'analisi dal database
    public boolean eliminaAnalisi(int idCampione, int idPanel, AnalisiCampione analisi) throws SQLException {
        return FacadeSingletonDB.getInstance().getAnalisiDAO().eliminaAnalisi(idCampione, idPanel, analisi);
    }

    // Restituisce gli ID dei campioni analizzati
    public ArrayList<Integer> idCampioniAnalizzati() throws SQLException {
        return FacadeSingletonDB.getInstance().getAnalisiDAO().idCampioniAnalizzati();
    }

    // Restituisce gli ID dei panel dei campioni analizzati
    public ArrayList<Integer> idPanelCampioniAnalizzati() throws SQLException {
        return FacadeSingletonDB.getInstance().getAnalisiDAO().idPanelCampioniAnalizzati();
    }
}
