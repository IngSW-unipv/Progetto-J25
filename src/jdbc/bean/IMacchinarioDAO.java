package jdbc.bean;

import modello.creazionePanel.Macchinario;

import java.util.ArrayList;

public interface IMacchinarioDAO {
    ArrayList<Macchinario> getMacchinari();
    boolean addMacchinario(Macchinario macchinario);
}
