package modello.analisiCampione;

import java.sql.SQLException;

import jdbc.FacedeSingletonDB;

/*
 * Questa classe rappresenta il sistema di analisi di un campione.
 */

public class SystemAnalisi {


    public SystemAnalisi() {}

    
    // Aggiunta di una nuova analisi nel database
    public boolean inserisciAnalisi(int idCampione, int idPanel, AnalisiCampione analisi) throws SQLException {
        return FacedeSingletonDB.getInstance().getAnalisiDAO().insertAnalisi(idCampione, idPanel, analisi);
    }

    // Modifica un'analisi nel database
    public boolean modificaAnalisi(int idCampione, int idPanel, AnalisiCampione analisi)  throws SQLException {
        return FacedeSingletonDB.getInstance().getAnalisiDAO().updateAnalisi(idCampione, idPanel, analisi);
    }

    // Elimina un'analisi dal database
    public boolean eliminaAnalisi(int idCampione, int idPanel, AnalisiCampione analisi) throws SQLException {
        return FacedeSingletonDB.getInstance().getAnalisiDAO().eliminaAnalisi(idCampione, idPanel, analisi); 
    }
}
