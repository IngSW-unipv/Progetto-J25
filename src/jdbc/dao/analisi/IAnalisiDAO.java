package jdbc.dao.analisi;

import java.sql.SQLException;
import java.util.ArrayList;

import modello.analisiCampione.*;

public interface IAnalisiDAO {


    /**
     * Inserisce una nuova analisi nel database.
     * 
     * @param 
     * @return true se l'inserimento è riuscito, false altrimenti
     * @throws possibile SQLException.
     */
    public boolean insertAnalisi(int idCampione, int idPanel, AnalisiCampione analisi) throws SQLException;
   
   /**
    * Aggiorna i dati di un'analisi nel database.
    * @param 
    * @return true se l'aggiornamento è riuscito, false altrimenti
    * @throws possibile SQLException.
    */
    public boolean updateAnalisi(int idCampione, int idPanel, AnalisiCampione analisi) throws SQLException;
    
    /**
     * Elimina un'analisi dal database.
     * @param 
     * @return true se l'eliminazione è riuscita, false altrimenti
     * @throws possibile SQLException.
     */
    public boolean eliminaAnalisi(int idCampione, int idPanel, AnalisiCampione analisi) throws SQLException;

    /**
     * Restituisce gli ID dei campioni analizzati.
     * @return
     * @throws SQLException
     */
    ArrayList<Integer> idCampioniAnalizzati() throws SQLException;


    /**
     * Restituisce gli ID dei panel dei campioni analizzati.
     * @return
     * @throws SQLException
     */
    ArrayList<Integer> idPanelCampioniAnalizzati() throws SQLException;

    
    

}
