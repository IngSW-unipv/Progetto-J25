package modello.creazionePanel;

public class Macchinario {
    private int id;
    private int numPanelisti;

    public Macchinario(int id, int numPanelisti) {
        this.id = id;

        this.numPanelisti = numPanelisti;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNumPanelisti() {
        return numPanelisti;
    }
    public void setNumPanelisti(int numPanelisti) {
        this.numPanelisti = numPanelisti;
    }



}
