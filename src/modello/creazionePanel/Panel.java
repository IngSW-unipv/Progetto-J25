package modello.creazionePanel;

import modello.Panelista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Panel {
    private LocalTime orarioInizio;
    private LocalDate data;
    private LocalTime orarioFine;
    private List<Panelista> listaPanelisti;
    private Macchinario macchinario;

    public Panel(LocalTime orarioInizio, Macchinario macchinario, LocalDate data) {
        this.orarioInizio = orarioInizio;
        this.macchinario = macchinario;
        this.data = data;
        this.listaPanelisti = new ArrayList<>();
    }

    public void addpanelista(Panelista p) {
        this.listaPanelisti.add(p);
    }

    public void setMacchinario(Macchinario macchinario) {
        this.macchinario = macchinario;
    }

}
