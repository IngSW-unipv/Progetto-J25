package view.prenotazioneInsaccatore;

import modello.prenotazioneInsaccatore.*;
import controller.PrenotaInsacController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ViewInsaccatore extends JFrame {
    private PrenotaInsacController controller;
    private JLabel avviso;
    private int idInsaccatore;

 
	// costruttore
    public ViewInsaccatore(int idInsaccatore) {
        super("INTERFACCIA INSACCATORE");
        this.idInsaccatore = idInsaccatore;
        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // layout di base: BorderLayout
        setLayout(new BorderLayout());

        // messaggio iniziale, grande e centrato
        avviso = new JLabel("Benvenut* nell'area di prenotazione degli Insaccatori!"
        		+ "I turni non sono ancora disponibili...", SwingConstants.CENTER);
        avviso.setFont(new Font("Arial", Font.BOLD, 20));
        add(avviso, BorderLayout.NORTH); // il messaggio iniziale va nella parte superiore

        // pulsante per tornare alla view principale
        JButton botTornaIndietro = new JButton("Torna alla Home");

        ActionListener interazione = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.tornaAllaViewPrincipale(); 
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Errore: Controller non impostato.");
                }
            }
        };
        botTornaIndietro.addActionListener(interazione);
        add(botTornaIndietro, BorderLayout.SOUTH);

        setVisible(true);
    }

    // setters and getters
    public PrenotaInsacController getController() {
        return controller;
    }

    public void setController(PrenotaInsacController controller) {
        this.controller = controller;
    }
    
    public int getIdInsaccatore() {
 		return idInsaccatore;
 	}

 	public void setIdInsaccatore(int idInsaccatore) {
 		this.idInsaccatore = idInsaccatore;
 	}
 	
    // metodo per creare un bottone per un turno
 	private JButton creaBottoneTurno(Turno turno) {
        JButton botTurno = new JButton("Turno " + turno.getOrainizio() + " - " + turno.getDurata() + " min");
        botTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prenotaTurno(turno);
            }
        });
        return botTurno;
    }

 // metodo per prenotare il turno, chiamato quando un bottone viene premuto
 	private void prenotaTurno(Turno turno) {
 	    if (controller != null) {
 	        try {
 	            boolean successo = controller.prenotaTurno(turno);  // passa il turno al controller per prenotarlo
 	            if (successo) {
 	                // mostra una finestra di dialogo di successo
 	                JOptionPane.showMessageDialog(this, 
 	                    "Prenotazione riuscita! Turno alle " + turno.getOrainizio() + " prenotato con successo.", 
 	                    "Successo", 
 	                    JOptionPane.INFORMATION_MESSAGE);
 	            } else {
 	                // mostra una finestra di dialogo di errore
 	                JOptionPane.showMessageDialog(this, 
 	                    "Errore: Turno occupato. Non puoi prenotarti.", 
 	                    "Errore", 
 	                    JOptionPane.ERROR_MESSAGE);
 	            }
 	        } catch (RuntimeException ex) {
 	            // mostra un messaggio di errore se c'è una RuntimeException
 	            JOptionPane.showMessageDialog(this, 
 	                ex.getMessage(), 
 	                "Errore", 
 	                JOptionPane.ERROR_MESSAGE);
 	        }
 	    }
 	}

    
    
 // metodo che permetter di cancellarsi dal turno

    
    



    // metodo di aggiornamento della view
    public void aggiornaTurni(Giorno[] settimana) {
        // rimuovo tutti i componenti (inclusi i bottoni) dalla vista
        getContentPane().removeAll();
        avviso = new JLabel("Seleziona un turno per prenotarti!", SwingConstants.CENTER);
        avviso.setFont(new Font("Arial", Font.BOLD, 16));
        add(avviso, BorderLayout.NORTH);
        
        // creo un pannello con gridLayout per i giorni e i turni
        JPanel pannelloTurni = new JPanel();
        //cerco il massimo delle righe come il massimo dei turni tra i giorni della settimana
        int maxturni = 0;
        for (Giorno giorno : settimana) {
            maxturni = Math.max(maxturni, giorno.getTurni().size());
        }
        pannelloTurni.setLayout(new GridLayout(maxturni + 1, settimana.length)); // una per i giorni, le altre righe per i turni

        // aggiungo i titoli per giorni
        String[] giorni = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì"};
        for (String giorno : giorni) {
            pannelloTurni.add(new JLabel(giorno, SwingConstants.CENTER));
        }

        // aggiungi i bottoni dei turni alla griglia
        for (int i = 0; i < maxturni; i++) {
            for (Giorno giorno : settimana) {
                ArrayList<Turno> turni = giorno.getTurni();
                if (i < turni.size()) {
                    pannelloTurni.add(creaBottoneTurno(turni.get(i))); 
                } 
                else {
                    pannelloTurni.add(new JLabel("")); // se non ci sono turni lascia uno spazio vuoto
                }
            }
        }

        // aggiungo il pannello con i turni alla finestra
        add(pannelloTurni, BorderLayout.CENTER);

        // creo il pulsante per tornare indietro
        JButton botTornaAllaHome = new JButton("Torna alla Home");
        ActionListener inter1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.tornaAllaViewPrincipale();
            }
        };
        botTornaAllaHome.addActionListener(inter1);
        add(botTornaAllaHome, BorderLayout.SOUTH);

        // rende visibile la finestra dopo l'aggiornamento
        revalidate();
        repaint();
    }
}
