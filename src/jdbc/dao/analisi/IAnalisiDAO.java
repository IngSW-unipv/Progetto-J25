package jdbc.dao.analisi;

import java.sql.SQLException;

import modello.analisiCampione.*;
import modello.archiviazioneCampione.Campione;
import modello.creazionePanel.Panel;

public interface IAnalisiDAO {


    /**
     * Inserisce una nuova analisi nel database.
     * 
     * @param 
     * @return true se l'inserimento è riuscito, false altrimenti
     * @throws possibile SQLException.
     */
    boolean insertAnalisi(Campione campione, Panel panel, AnalisiCampione analisi) throws SQLException;
   
   /**
    * Aggiorna i dati di un'analisi nel database.
    * @param 
    * @return true se l'aggiornamento è riuscito, false altrimenti
    * @throws possibile SQLException.
    */
    boolean updateAnalisi(Campione campione, Panel panel, AnalisiCampione analisi) throws SQLException;
    
    /**
     * Elimina un'analisi dal database.
     * @param 
     * @return true se l'eliminazione è riuscita, false altrimenti
     * @throws possibile SQLException.
     */
    boolean eliminaAnalisi(Campione campione, Panel panel, AnalisiCampione analisi) throws SQLException;
    
    

}
