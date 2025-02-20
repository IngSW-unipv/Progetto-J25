package jdbc.dao;

import modello.creazionePanel.Sondaggio;

import java.util.ArrayList;
import java.util.List;

public interface ISondaggioDAO {
    ArrayList<Sondaggio> selectAllSondaggi();
    Sondaggio selectSondaggioById(int id);
    Integer insertSondaggio(Sondaggio sondaggio);
    boolean chiudiSondaggio(Sondaggio sondaggio);
}
