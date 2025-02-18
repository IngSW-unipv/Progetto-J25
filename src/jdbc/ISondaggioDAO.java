package jdbc;

import modello.creazionePanel.Sondaggio;

import java.util.ArrayList;

public interface ISondaggioDAO {
    ArrayList<Sondaggio> selectAllSondaggi();
    Sondaggio selectSondaggioById(int id);
    boolean insertSondaggio(Sondaggio sondaggio);

}
