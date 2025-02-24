package view.prenotazioneInsaccatore;
import modello.prenotazioneInsaccatore.*;
import javax.swing.*;

import controller.PrenotaInsacController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewInsaccatore extends JFrame {
    private static final long serialVersionUID = 1L;
    private PrenotaInsacController controller;
    private JLabel avviso;

    // Costruttore:
    public ViewInsaccatore() {
        super("INTERFACCIA INSACCATORE");

<<<<<<< HEAD
	public void setController(PrenotaInsacController controller) {
		this.controller = controller;
	}
	
	

	//METODI UTILI:
	
	// metodo di aggiornamento della view, utile per avere l'interfaccia aggiornata seguendo i cambiamenti eseguiti:
	public void aggiornaInterfaccia() {
		
	}

	
	
=======
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout di base: BorderLayout
        setLayout(new BorderLayout());

        // Messaggio iniziale, grande e centrato
        avviso = new JLabel("<html><div style='text-align: center;'>Benvenut* nell'area di prenotazione degli Insaccatori!<br>Non ci sono turni al momento</div></html>", SwingConstants.CENTER);
        avviso.setFont(new Font("Arial", Font.BOLD, 20));
        add(avviso, BorderLayout.NORTH); // Il messaggio iniziale va nella parte superiore

        // pulsante per tornare alla view principale
        JButton botTornaIndietro = new JButton("Torna alla Home");

        ActionListener interazione3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.tornaAllaViewPrincipale(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Errore: Controller non impostato.");
                }
            }
        };
        botTornaIndietro.addActionListener(interazione3);
        add(botTornaIndietro, BorderLayout.SOUTH);

        setVisible(false);
    }

    // Setters and Getters:
    public PrenotaInsacController getController() {
        return controller;
    }

    public void setController(PrenotaInsacController controller) {
        this.controller = controller;
    }

    // Metodo di aggiornamento della view:
    public void aggiornaTurni(Giorno[] settimana) {
        // Rimuovo tutti i componenti (inclusi i bottoni) dalla vista
        getContentPane().removeAll();

        // Crea un pannello con GridLayout per i giorni e i turni
        // 1 riga per i giorni e N righe per i turni (dove N è il massimo dei turni per giorno)
        JPanel pannelloTurni = new JPanel();
        pannelloTurni.setLayout(new GridLayout(1 + settimana[0].getTurni().size(), settimana.length));  // 1 riga per i giorni, poi N righe per i turni

        // Aggiungo i titoli per giorni (Lunedì, Martedì, ...), prima colonna con i giorni
        String[] giorni = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì"};
        for (String giorno : giorni) {
            pannelloTurni.add(new JLabel(giorno, SwingConstants.CENTER)); // Intestazione dei giorni
        }

        // Trovo il numero massimo di turni per giorno
        int maxTurni = 0;
        for (Giorno giorno : settimana) {
            maxTurni = Math.max(maxTurni, giorno.getTurni().size());
        }

        // Aggiungo i turni, organizzando per righe sotto ogni giorno
        for (int i = 0; i < maxTurni; i++) {
            // Per ogni turno aggiungo una riga con il turno per ciascun giorno
            for (int j = 0; j < settimana.length; j++) {
                Giorno giorno = settimana[j];
                ArrayList<Turno> turni = giorno.getTurni();
                
                if (i < turni.size()) {
                    Turno turno = turni.get(i);

                    // Creo un nuovo pulsante per il turno
                    JButton botTurno = new JButton("Turno " + turno.getOrainizio() + " - " + turno.getDurata() + " min");
                    
                    // Aggiungi il bottone alla griglia
                    pannelloTurni.add(botTurno);
                } else {
                    // Se non ci sono turni per il giorno, aggiungi uno spazio vuoto
                    pannelloTurni.add(new JLabel());
                }
            }
        }

        // Aggiungo il pannello con i turni alla finestra
        add(pannelloTurni, BorderLayout.CENTER);
        
        // Rende visibile la finestra dopo l'aggiornamento
        revalidate();
        repaint();
    }
>>>>>>> branch 'main' of https://github.com/IngSW-unipv/Progetto-J25.git
}
