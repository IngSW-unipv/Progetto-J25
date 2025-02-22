package jdbc.dao.analisi;

import java.time.LocalDate;
import java.util.ArrayList;
import modello.analisiCampione.*;


public interface IAnalisiDAO {

    boolean insertAnalisi(LocalDate dataAnalisi, double gradazione, int idCampione, int idPanel);
    boolean updateAnalisi(int id, LocalDate dataAnalisi, double gradazione, int idCampione, int idPanel);
    boolean eliminaAnalisi(LocalDate dataAnalisi, double gradazione, int idCampione, int idPanel);
    ArrayList <AnalisiCampione> selectAllAnalisi();
    ArrayList <AnalisiCampione> trovaAnalisiPerData(LocalDate data);
    ArrayList <AnalisiCampione> trovaAnalisiPerGradazione(double gradazione);
    ArrayList <AnalisiCampione> trovaAnalisiPerCampione(int idCampione);
    ArrayList <AnalisiCampione> trovaAnalisiPerPanel(int idPanel);


}
