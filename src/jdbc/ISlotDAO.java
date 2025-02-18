package jdbc;

import modello.creazionePanel.Slot;

import java.util.ArrayList;

public interface ISlotDAO {
    boolean insertSlots(ArrayList<Slot> slots);
    ArrayList<Slot> getSlots(int idSondaggio);

}
