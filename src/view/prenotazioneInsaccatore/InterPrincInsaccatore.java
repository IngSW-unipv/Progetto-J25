package view.prenotazioneInsaccatore;

import javax.swing.*;

import controller.PrenotaInsacController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InterPrincInsaccatore extends JFrame{
	private PrenotaInsacController controller;
	public InterPrincInsaccatore() {
		super("Sezione Insaccatore");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new java.awt.FlowLayout()); //flowlayout mi serve per disporre gli elementi in modo orizzontale
		
		//creo il pulsante che mi permette di accedere al calendario dei turni: 
		JButton botCalendario = new JButton("Calendario Turni");
		//creo l'oggetto ActionListener con un override che mi permetta di aprire la finestra con il calendario
		ActionListener interazione = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.apriViewInsaccatore();;
			}
		};
		botCalendario.addActionListener(interazione); //aggiungo l'interazione effettiva al bottone
		add(botCalendario);
		
		//pulsante per generare i turni:
		JButton botGenTurni = new JButton("Genera i turni");
		
		ActionListener interazione2 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					String dataIn = JOptionPane.showInputDialog("Inserisci il prossimo lunedì");
					String durataIn= JOptionPane.showInputDialog("Inserisci la durata del turni in minuti:");
					try {
						int durata= Integer.parseInt(durataIn);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate data = LocalDate.parse(dataIn, formatter);
						controller.generaSettimanaTurni(data,durata);
					}
					catch(Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Errore: formato non valido");
					}
			}
		};
		botGenTurni.addActionListener(interazione2);
		add(botGenTurni);
		
		setVisible(true);
		
		
	}
	
	 // Metodo per impostare il controller nella vista principale
    public void setController(PrenotaInsacController controller) {
        this.controller = controller;
    }
	
	
	public static void main(String[] args) {
		new InterPrincInsaccatore();	
	}
}

