package controller;

import jdbc.FacedeSingletonDB;
import jdbc.dao.MacchinarioDAO;
import jdbc.dao.SondaggioDAO;
import modello.creazionePanel.Macchinario;
import modello.creazionePanel.SystemPubblicazionePanel;
import modello.creazionePanel.Sondaggio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SondaggioController {
        private SystemPubblicazionePanel systemPubblicazionePanel;

        public SondaggioController() {
            systemPubblicazionePanel = FacedeSingletonDB.getInstance().getSystemPubblicazionePanel();
        }

        public ArrayList<Macchinario> getMacchinariDisponibili() {
            return FacedeSingletonDB.getInstance().getMacchinarioDAO().getMacchinari();
        }

    private Sondaggio sondaggioCorrente = null;

    public Sondaggio creaSondaggio(int numCampioni, LocalDate data, ArrayList<Macchinario> macchinariSelezionati, TipoCreaSondaggio tipo) {
            this.systemPubblicazionePanel.setMacchinari(macchinariSelezionati);

            Sondaggio sondaggio;
            switch (tipo) {
                case MANUALE:
                    sondaggio = this.systemPubblicazionePanel.creazioneSondaggioManuale();
                    System.out.println("Manuale"); // da vedere
                    break;
                case AUTOMATICO:
                    sondaggio = this.systemPubblicazionePanel.creaSondaggioAutomatica(numCampioni, data);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo di sondaggio non valido: " + tipo);
            }

            return sondaggio;
        }

        public void pubblicaSondaggio(Sondaggio sondaggio) {
            this.systemPubblicazionePanel.pubblicazioneSondaggio(sondaggio);
        }

}
