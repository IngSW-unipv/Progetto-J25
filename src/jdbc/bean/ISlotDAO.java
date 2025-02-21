package jdbc.bean;

import modello.creazionePanel.Slot;
import modello.creazionePanel.Sondaggio;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

public interface ISlotDAO {
    boolean insertSlots(Map<LocalTime, Slot> slots);
    ArrayList<Slot> getSlots(Sondaggio sondaggio);

}
