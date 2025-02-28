package view.prenotazionePanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.PrenotazioneController;
import jdbc.FacadeSingletonDB;
import modello.Panelista;
import modello.Utente;
import modello.creazionePanel.Slot;
import modello.creazionePanel.Sondaggio;
import modello.prenotazionePanel.SystemPrenotazione;

public class PrenotazioneView {
	
	private JFrame frame;
	private JPanel mainPanel;
	private JButton btnPrenota;
	private Panelista panelista;
	private PrenotazioneController controller;
	private Sondaggio sondaggioSelezionato;
	private Slot slotSelezionato;
	
	public PrenotazioneView(Panelista panelista) {
		
		this.panelista = panelista;
		controller = new PrenotazioneController();


		ArrayList<Sondaggio> sondaggi = controller.getSystem().getSondaggi();

		
		frame = new JFrame("Prenotazione al panel");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1));//mettendo 0 al posto delle righe, posso avere un numero i righe arbitrario
		frame.add(new JScrollPane(mainPanel), BorderLayout.CENTER);
		
		for(Sondaggio s : sondaggi) {
			
			JButton btnSondaggio = new JButton("Sondaggio " + s.getData());
			btnSondaggio.addActionListener(new ActionListener (){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					mostraSlot(s);
					
					
				}
				
				
			});
			
			mainPanel.add(btnSondaggio);
			
		}
		
		btnPrenota = new JButton("Prenota");
		btnPrenota.setEnabled(false);
		
		btnPrenota.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				prenota();
				
			}
			
			
		});
		
		frame.add(btnPrenota, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	
	
	private void mostraSlot(Sondaggio sondaggio) {
		
		this.sondaggioSelezionato = sondaggio;
		mainPanel.removeAll();
		
		JLabel titolo = new JLabel("Slot disponibili per sondaggio " +sondaggio.getId());
		mainPanel.add(titolo);
		
		for(Map.Entry<LocalTime, Slot> entry: sondaggio.getSlots().entrySet()) {
			
			LocalTime slotTime = entry.getKey();
			Slot slot = entry.getValue();
			
			JButton btnSlot = new JButton("Slot: "+slotTime);
			btnSlot.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					 slotSelezionato = slot;
					 btnPrenota.setEnabled(true);
				}
				
				
			});
			
			mainPanel.add(btnSlot);
			
		}
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tornaIndietro();
				
			}
			
			
		});
		
		mainPanel.add(btnIndietro);
		mainPanel.revalidate();
        mainPanel.repaint(); 
	}
	
	
	private void prenota() {
		
		if(sondaggioSelezionato != null && slotSelezionato != null) {
			
			controller.prenotaSondaggio(sondaggioSelezionato.getId(), slotSelezionato.getTime(), panelista);
		}else {
			
			JOptionPane.showMessageDialog(frame, "Errore");
		}
	}
	
	
	private void tornaIndietro() {
		
		/*RICORDATI DI CHIEDERE PERCHE
		 * TUTTI QUESTI METODI SONO PRIVATE
		 */
		
		mainPanel.removeAll();
		JLabel titolo = new JLabel("Seleziona un sondaggio:");
		mainPanel.add(titolo);
		
		SystemPrenotazione sys = FacadeSingletonDB.getInstance().getSystemPrenotazione();
		for(Sondaggio s : sys.getSondaggi()) {
			
			JButton sondaggioButton = new JButton("Sondaggio " + s.getId());
            sondaggioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostraSlot(s);
                }
            });
            
            mainPanel.add(sondaggioButton);
		}
		
		mainPanel.revalidate();
        mainPanel.repaint();
	}
	
}
