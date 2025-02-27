package view.archiviazioneCampione;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import controller.CampioneController;
import modello.archiviazioneCampione.Campione;


public class CampioneView {

	private JFrame frame;
	private JToolBar toolBar;
	private JButton btnRegistra, btnTrova, btnAggiorna, btnElimina, btnLista;
	private CampioneController controller;
	private JTextArea displayArea;
	
	public CampioneView(CampioneController controller) {
		
		this.controller = controller;
		frame = new JFrame("Archiviazione campioni");
		frame.setSize(700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		toolBar = new JToolBar();
		btnRegistra = new JButton("Registra campione");
		btnTrova = new JButton("Trova campione");
		btnAggiorna = new JButton("Aggiorna campione");
		btnElimina = new JButton("Elimina campione");
		btnLista = new JButton("Lista Campioni");
		
		toolBar.add(btnRegistra);
		toolBar.add(btnTrova);
		toolBar.add(btnAggiorna);
		toolBar.add(btnElimina);
		toolBar.add(btnLista);
		
		frame.add(toolBar, BorderLayout.NORTH);
		
		displayArea = new JTextArea();
		displayArea.setEditable(false); //cosi l'utente non può modificare l?area di testo
		
		JScrollPane scrollPane = new JScrollPane(displayArea);
		
		frame.add(scrollPane, BorderLayout.CENTER);
		
		
		btnRegistra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				registraCampione();

			}
		});
		
		btnTrova.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				trovaCampione();
				
			}
			
			
		});
		
		btnAggiorna.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aggiornaCampione();
				
			}
			
			
		});
		
		btnElimina.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				eliminaCampione();
				
			}
			
			
		});
		
		btnLista.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				listaCampione();
				
			}
			
			
		});
		
		frame.setVisible(true);
		
		
	}
	
	
	public void registraCampione() {
		
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Inserisci l'id del campione"));
			String stato = JOptionPane.showInputDialog("Inserisci lo stato del campione");
			LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Inserisci la data di arrivo"));
			
			if(controller.registraCampione(id, stato, data)) {
				
				displayArea.setText("Campione registrato con successo");
			}else {
				
				displayArea.setText("Errore nella registrazione");
			}
			
		}catch(Exception e) {
			
			displayArea.setText("Errore di input dati");
		}
		
		
	}
	
	
	public void trovaCampione() {
		
		try {
			
			int id = Integer.parseInt(JOptionPane.showInputDialog("Inserisci l'id del campione"));
			Campione campione = controller.trovaCampione(id);
			
			if(campione != null) {
				
				String details = "Campione trovato:\n";
				details = details + "ID: " + campione.getId();
				details = details + "\nstato: " + campione.getStato();
				details = details + "\ndataArrivo: " + campione.getDataArrivo();
				
				displayArea.setText(details);
			}else {
				
				displayArea.setText("Campione non trovato");
			}
		}catch(Exception e) {
			
			displayArea.setText("Input non valido");
		}
	}
	
	public void aggiornaCampione() {
		
		try {
			
			int id = Integer.parseInt(JOptionPane.showInputDialog("Inserisci l'id del campione: "));
			String nuovoStato = JOptionPane.showInputDialog("Inserisci lo stato del campione: ");
			
			if(controller.aggiornaCampione(id, nuovoStato)) {
				
				displayArea.setText("Stato aggiornato con successo");
			}else {
				
				displayArea.setText("Stato non aggiornato");
			}
		}catch(Exception e) {
			
			displayArea.setText("Input non valido");
		}
	}
	
	
	public void eliminaCampione() {
		
		try {
			
			int id = Integer.parseInt(JOptionPane.showInputDialog("Inserisci l'id del campione: "));
			
			if(controller.eliminaCampione(id)) {
				
				displayArea.setText("Campione eliminato con successo");
			}else {
				
				displayArea.setText("Errore nell'eliminazione");
			}
		}catch(Exception e) {
			
			displayArea.setText("Input non valido");
		}
	}
	
	public void listaCampione() {
		
		ArrayList<Campione> campioni = controller.selezionaCampioni();
		
		if(campioni.isEmpty()) {
			
			displayArea.setText("Nessun campione presente");
		}else {
			
			StringBuilder lista = new StringBuilder();
			
			for(Campione c : campioni) {
				
				lista.append("id: ").append(c.getId())
				.append("\nstato: ").append(c.getStato())
				.append("\ndata: ").append(c.getDataArrivo())
				.append("\n\n");
				
			}
			
			displayArea.setText(lista.toString());
		}
	}
	
	
	
	
}
