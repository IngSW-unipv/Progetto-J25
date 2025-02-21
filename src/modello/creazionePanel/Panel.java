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
    private boolean stato = true;

    public Panel(LocalTime orarioInizio, Macchinario macchinario, LocalDate data) {
        this.orarioInizio = orarioInizio;
        this.macchinario = macchinario;
        this.data = data;
        this.listaPanelisti = new ArrayList<>();
    }

    public boolean getStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public LocalTime getOrarioInizio() {
        return orarioInizio;
    }
    public void setOrarioInizio(LocalTime orarioInizio) {
        this.orarioInizio = orarioInizio;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public LocalTime getOrarioFine() {
        return orarioFine;
    }
    public void setOrarioFine(LocalTime orarioFine) {
        this.orarioFine = orarioFine;
    }
    public List<Panelista> getListaPanelisti() {
        return listaPanelisti;
    }
    public void setListaPanelisti(List<Panelista> listaPanelisti) {
        this.listaPanelisti = listaPanelisti;
    }
    public Macchinario getMacchinario() {
        return macchinario;
    }

    public void addpanelista(Panelista p) {
        this.listaPanelisti.add(p);
    }

    public void setMacchinario(Macchinario macchinario) {
        this.macchinario = macchinario;
    }

    public void stampa(){
        System.out.println("data:" + this.data.toString());
        System.out.println("orarioInizio:" + this.orarioInizio);
        System.out.println("Macchinario: " + this.macchinario.getId());
        for(Panelista p : this.listaPanelisti){
            System.out.println(p.getEmail());
        }
    }

}
