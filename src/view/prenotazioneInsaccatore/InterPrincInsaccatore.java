package view.prenotazioneInsaccatore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterPrincInsaccatore extends JFrame{
	public InterPrincInsaccatore() {
		super("Sezione Insaccatore");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new java.awt.FlowLayout()); //flowlayout mi serve per disporre gli elementi in modo orizzontale
		
		//creo il pulsante che mi permette di accedere al calendario dei turni: 
		JButton botCalendario = new JButton("ACCEDI AI TURNI");
		//creo l'oggetto ActionListener con un override che mi permetta di aprire la finestra con il calendario
		ActionListener interazione = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewInsaccatore();
			}
		};
		botCalendario.addActionListener(interazione);
		
		add(botCalendario);
		
		setVisible(true);
		
		
	}
	public static void main(String[] args) {
		new InterPrincInsaccatore();
	}
}

