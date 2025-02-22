package jdbc.dao.analisi;

import java.time.LocalDate;
import java.util.ArrayList;
import modello.analisiCampione.*;
import modello.creazionePanel.Panel;



public class  AnalisiDAO implements IAnalisiDAO {
    @Override
    public boolean insertAnalisi(LocalDate dataAnalisi, double gradazione, int idCampione, int idPanel) {
        return false;
    }

    @Override
    public boolean updateAnalisi(int id, LocalDate dataAnalisi, double gradazione, int idCampione, int idPanel) {
        return false;
    }

    @Override
    public boolean eliminaAnalisi(LocalDate dataAnalisi, double gradazione, int idCampione, int idPanel) {
        return false;
    }

    @Override
    public ArrayList<AnalisiCampione> selectAllAnalisi() {
        return null;
    }

    @Override
    public ArrayList<AnalisiCampione> trovaAnalisiPerData(LocalDate data) {
        return null;
    }

    @Override
    public ArrayList<AnalisiCampione> trovaAnalisiPerGradazione(double gradazione) {
        return null;
    }

    @Override
    public ArrayList<AnalisiCampione> trovaAnalisiPerCampione(int idCampione) {
        return null;
    }

    @Override
    public ArrayList<AnalisiCampione> trovaAnalisiPerPanel(int idPanel) {
        return null;
    }


}
