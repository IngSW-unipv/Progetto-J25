package jdbc.bean;

import modello.creazionePanel.Sondaggio;

import java.util.ArrayList;

public interface ISondaggioDAO {
    ArrayList<Sondaggio> selectAllSondaggi();
    Sondaggio selectSondaggioById(int id);
    Integer insertSondaggio(Sondaggio sondaggio);
    boolean chiudiSondaggio(Sondaggio sondaggio);
}
