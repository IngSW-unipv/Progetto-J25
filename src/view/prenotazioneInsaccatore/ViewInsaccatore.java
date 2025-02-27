package view.prenotazioneInsaccatore;

import modello.prenotazioneInsaccatore.*;
import controller.PrenotaInsacController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ViewInsaccatore extends JFrame {
    private static final long serialVersionUID = 1L;
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
        avviso = new JLabel("<html><div style='text-align: center;'>Benvenut* nell'area di prenotazione degli Insaccatori!<br>Non ci sono turni al momento</div></html>", SwingConstants.CENTER);
        avviso.setFont(new Font("Arial", Font.BOLD, 20));
        add(avviso, BorderLayout.NORTH); // il messaggio iniziale va nella parte superiore

        // pulsante per tornare alla view principale
        JButton botTornaIndietro = new JButton("Torna alla Home");

        ActionListener interazione = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.tornaAllaViewPrincipale(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Errore: Controller non impostato.");
                }
            }
        };
        botTornaIndietro.addActionListener(interazione);
        add(botTornaIndietro, BorderLayout.SOUTH);

        setVisible(false);
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
                    // mostra un messaggio di successo
                    avviso.setText("<html><div style='text-align: center;'>Prenotazione riuscita! Turno alle " 
                            + turno.getOrainizio() + " prenotato con successo.</div></html>");
                } else {
                    // mostra un messaggio di errore
                    avviso.setText("<html><div style='text-align: center;'>Errore: Turno occupato. Non puoi prenotarti.</div></html>");
                }
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    // metodo per aggiungere i bottoni dei turni alla griglia
    private void aggiungiBottoniTurni(JPanel pannelloTurni, Giorno[] settimana) {
        // Aggiungo i bottoni dei turni per ogni giorno, solo se esistono turni per quel giorno
        for (int j = 0; j < settimana.length; j++) {
            Giorno giorno = settimana[j];
            ArrayList<Turno> turni = giorno.getTurni();

            for (Turno turno : turni) {
                // Creo un bottone per ogni turno disponibile
                pannelloTurni.add(creaBottoneTurno(turno));  // Usa il metodo per creare il bottone
            }

            // Se il giorno non ha turni, aggiungo un'etichetta vuota
            if (turni.isEmpty()) {
                pannelloTurni.add(new JLabel("Turno non disponibile"));  // Un messaggio che segnala che non ci sono turni
            }
        }
    }
    
    // metodo di aggiornamento della view
    public void aggiornaTurni(Giorno[] settimana) {
        // rimuovo tutti i componenti (inclusi i bottoni) dalla vista
        getContentPane().removeAll();

        // crea un pannello con GridLayout per i giorni e i turni
        // 1 riga per i giorni e N righe per i turni (dove N è il massimo dei turni per giorno)
        JPanel pannelloTurni = new JPanel();

        pannelloTurni.setLayout(new GridLayout(settimana[0].getTurni().size() + 1, settimana.length)); // 1 per i giorni, più righe per i turni

        // aggiungo i titoli per giorni (Lunedì, Martedì, ...), prima colonna con i giorni
        String[] giorni = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì"};
        for (String giorno : giorni) {
            pannelloTurni.add(new JLabel(giorno, SwingConstants.CENTER)); // intestazione dei giorni
        }

        // aggiungi i bottoni dei turni alla griglia
        aggiungiBottoniTurni(pannelloTurni, settimana);

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
