package jdbc.bean;

import modello.Panelista;
import modello.creazionePanel.Slot;

import java.time.LocalTime;
import java.util.Map;

public interface IPrenotazionePanelDAO {
    boolean getPrenotazioni(Map<LocalTime, Slot> slots);
    boolean salvaPrenotazione(Slot slot, Panelista panelista);
}
