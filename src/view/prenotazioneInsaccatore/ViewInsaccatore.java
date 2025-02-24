package view.prenotazioneInsaccatore;
import modello.prenotazioneInsaccatore.*;
import javax.swing.*;

import controller.PrenotaInsacController;

import java.awt.*;
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

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout di base: GridLayout
        setLayout(new BorderLayout());

        // Messaggio iniziale, grande e centrato
        avviso = new JLabel("<html><div style='text-align: center;'>Benvenut* nell'area di prenotazione degli Insaccatori!<br>Non ci sono turni al momento</div></html>", SwingConstants.CENTER);
        avviso.setFont(new Font("Arial", Font.BOLD, 20));
        add(avviso, BorderLayout.NORTH); // Il messaggio iniziale va nella parte superiore

        setVisible(true);
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
        JPanel pannelloTurni = new JPanel();
        pannelloTurni.setLayout(new GridLayout(6, 5));  // 6 righe (1 per i giorni e 5 per i turni) e 5 colonne (una per ogni giorno)

        // Aggiungo i titoli per giorni (Lunedì, Martedì, ...)
        String[] giorni = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì"};
        for (String giorno : giorni) {
            pannelloTurni.add(new JLabel(giorno, SwingConstants.CENTER)); // Intestazione dei giorni
        }

        // Ciclo attraverso la settimana per aggiungere nuovi bottoni
        for (int i = 0; i < settimana.length; i++) {
            Giorno giorno = settimana[i];  // Ottieni il giorno corrente
            ArrayList<Turno> turni = giorno.getTurni();  // Ottieni i turni di quel giorno

            // Ciclo per creare e aggiungere i pulsanti per ogni turno
            for (int j = 0; j < turni.size(); j++) {
                Turno turno = turni.get(j);

                // Creo un nuovo pulsante per il turno
                JButton botTurno = new JButton("Turno " + turno.getOrainizio() + " - " + turno.getDurata() + " min");
                
                // Aggiungi il bottone alla griglia
                pannelloTurni.add(botTurno);
            }

            // Se ci sono meno di 5 turni, aggiungi JLabel vuoti per mantenere la griglia completa
            for (int j = turni.size(); j < 5; j++) {
                pannelloTurni.add(new JLabel()); // Spazi vuoti per allineare la griglia
            }
        }

        // Aggiungo il pannello con i turni alla finestra
        add(pannelloTurni, BorderLayout.CENTER);

        // Rende visibile la finestra dopo l'aggiornamento
        revalidate();
        repaint();
    }
}
