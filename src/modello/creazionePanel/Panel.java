package modello.creazionePanel;

import modello.Panelista;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Panel {
    private LocalTime orarioInizio;
    private LocalTime orarioFine;
    private List<Panelista> listaPanelisti;
    private Macchinario macchinario;

    public Panel(LocalTime orarioInizio, Macchinario macchinario) {
        this.orarioInizio = orarioInizio;
        this.macchinario = macchinario;
        this.listaPanelisti = new ArrayList<>();
    }

    public void addpanelista(Panelista p) {
        this.listaPanelisti.add(p);
    }

}
