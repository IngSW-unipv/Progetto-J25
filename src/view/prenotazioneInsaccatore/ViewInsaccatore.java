package view.prenotazioneInsaccatore;
import modello.prenotazioneInsaccatore.*;
import javax.swing.*;

import controller.PrenotaInsacController;

import java.awt.*;
import java.time.LocalTime;

public class ViewInsaccatore extends JFrame {
	
	private PrenotaInsacController controller;
	

	//Costruttore:
	public ViewInsaccatore(){
		//Titolo interfaccia:
		super("INTERFACCIA INSACCATORE");
		
		//Imposto la dimensione della finestra
		setSize(800,600);
		//Imposto il pulsante di uscita
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Imposto la mia griglia coi turni:
		setLayout(new GridLayout(6,5));	
		
		//Aggiungo i titoli per ogni colonna dato che il lavour è tipo griglia:
		String[] giorni= {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì"};
		for(String g:giorni) {
			add(new JLabel(g, SwingConstants.CENTER));
			}
		
		
		//Pulsanti di informazione:
		JLabel t1 = new JLabel("Benvenut* nell'area di prenotazione degli Insaccatori!");
		
		//Pulsanti dei turni da selezionare:
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				add(new JButton("PRENOTATI!"));
			}
		}
		
		setVisible(true);		
	}
	
	
	//SETTER GETTER:
	public PrenotaInsacController getController() {
		return controller;
	}

	public void setController(PrenotaInsacController controller) {
		this.controller = controller;
	}
	
	
	//METODI UTILI:
	
	// metodo di aggiornamento della view, utile per avere l'interfaccia aggiornata seguendo i cambiamenti eseguiti:
	public void aggiornaInterfaccia() {
		
	}

	
	
}
