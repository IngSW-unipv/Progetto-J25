package jdbc.dao.analisi;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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
    
    /**
     * Restituisce tutte le analisi presenti nel database.
     * @return 
     */
    List <AnalisiCampione> selectAllAnalisi();
   
    /**
     * Restituisce tutte le analisi effettuate in una determinata data.
     * @param data
     * @return
     */
    List <AnalisiCampione> trovaAnalisiPerData(LocalDate data);
    
    /**
     * Restituisce tutte le analisi effettuate con una determinata gradazione.
     * @param gradazione
     * @return
     */
    List <AnalisiCampione> trovaAnalisiPerGradazione(double gradazione);

    /**
     * Restituisce tutte le analisi effettuate su un determinato campione.
     * @param idCampione
     * @return List<AnalisiCampione>
     */
    List <AnalisiCampione> trovaAnalisiPerCampione(int idCampione);



}
