package testing;

import java.time.LocalDate;

import jdbc.FacedeSingletonDB;
import modello.Panelista;
import modello.TipoUtente;
import modello.prenotazionePanel.SystemPrenotazione;
import view.prenotazionePanel.PrenotazioneView;
import modello.FactoryUtente;

public class PrenotazioneTest {

	public static void main(String[] args) {
		
		//SystemPrenotazione systemPrenotazione = FacedeSingletonDB.getInstance().getSystemPrenotazione();
		
		Panelista p = (Panelista) FactoryUtente.CreaUtente(TipoUtente.PANELISTA, "Khawla", 10 , "khawla.ouaadoupa@gmail.com", 0, "Khawla", "Pavia",
															LocalDate.now(), null, null, null, null, null, 0, 0);
		
		 PrenotazioneView prenotazioneView = new PrenotazioneView(p);

	}

}
