package controller;

import jdbc.FacadeSingletonDB;
import modello.creazionePanel.Macchinario;
import modello.creazionePanel.Slot;
import modello.creazionePanel.SystemPubblicazionePanel;
import modello.creazionePanel.Sondaggio;

import java.time.LocalDate;
import java.util.ArrayList;

public class SondaggioController {
	
        private SystemPubblicazionePanel systemPubblicazionePanel;

        public SondaggioController() {
            systemPubblicazionePanel = FacadeSingletonDB.getInstance().getSystemPubblicazionePanel();
        }

        public ArrayList<Macchinario> getMacchinariDisponibili() {
            return FacadeSingletonDB.getInstance().getMacchinarioDAO().getMacchinari();
        }

        private Sondaggio sondaggioCorrente = null;

        public Sondaggio creaSondaggioAuto(int numCampioni, LocalDate data, ArrayList<Macchinario> macchinariSelezionati) {
            this.systemPubblicazionePanel.setMacchinari(macchinariSelezionati);
            Sondaggio sondaggio;
            return sondaggio = this.systemPubblicazionePanel.creaSondaggioAutomatica(numCampioni, data);
        }

        public Sondaggio creaSondaggioManual(LocalDate data, ArrayList<Macchinario> macchinariSelezionati, ArrayList<Slot> slotSelezionati) {
            this.systemPubblicazionePanel.setMacchinari(macchinariSelezionati);
            Sondaggio sondaggio = systemPubblicazionePanel.creazioneSondaggioManuale(data, slotSelezionati);
            return sondaggio;
        }

        public void pubblicaSondaggio(Sondaggio sondaggio) {
            this.systemPubblicazionePanel.pubblicazioneSondaggio(sondaggio);
        }

}
