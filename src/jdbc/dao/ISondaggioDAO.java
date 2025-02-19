package jdbc.dao;

import modello.creazionePanel.Sondaggio;

import java.util.ArrayList;
import java.util.List;

public interface ISondaggioDAO {
    List<Sondaggio> selectAllSondaggi();
    Sondaggio selectSondaggioById(int id);
    boolean insertSondaggio(Sondaggio sondaggio);
    boolean chiudiSondaggio(Sondaggio sondaggio);
}
