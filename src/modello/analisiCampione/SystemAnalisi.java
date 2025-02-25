package modello.analisiCampione;

import modello.archiviazioneCampione.Campione;
import modello.creazionePanel.Panel;

import java.sql.SQLException;

import jdbc.FacedeSingletonDB;

/*
 * Questa classe rappresenta il sistema di analisi di un campione.
 */

public class SystemAnalisi {


    public SystemAnalisi() {}

    
    // Aggiunta di una nuova analisi nel database
    public boolean inserisciAnalisi(Campione campione, Panel panel, AnalisiCampione analisi) throws SQLException {
        return FacedeSingletonDB.getInstance().getAnalisiDAO().insertAnalisi(campione, panel, analisi);
    }

    // Modifica un'analisi nel database
    public boolean modificaAnalisi(Campione campione, Panel panel, AnalisiCampione analisi)  throws SQLException {
        return FacedeSingletonDB.getInstance().getAnalisiDAO().updateAnalisi(campione, panel, analisi);
    }

    // Elimina un'analisi dal database
    public boolean eliminaAnalisi(Campione campione, Panel panel, AnalisiCampione analisi) throws SQLException {
        return FacedeSingletonDB.getInstance().getAnalisiDAO().eliminaAnalisi(campione, panel, analisi); 
    }
}
