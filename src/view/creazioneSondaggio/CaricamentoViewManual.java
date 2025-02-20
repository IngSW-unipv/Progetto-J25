package view.creazioneSondaggio;

import controller.SondaggioController;
import modello.creazionePanel.Macchinario;
import modello.creazionePanel.Slot;
import modello.creazionePanel.Sondaggio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CaricamentoViewManual extends JFrame {
    private SondaggioController controller;
    private JTextField orarioField;
    private ArrayList<Slot> slots;

    public CaricamentoViewManual(SondaggioController controller, LocalDate date, ArrayList<Macchinario> macchinari, int numeroCampioni) {
        this.controller = controller;
        this.slots = new ArrayList<>();

        setTitle("Caricamento Manuale");
        setSize(400, 300); // Ho aumentato la dimensione della finestra per migliorare la visibilità
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Pannello per inserimento orario
        JPanel orarioPanel = new JPanel();
        orarioPanel.setLayout(new BoxLayout(orarioPanel, BoxLayout.Y_AXIS));  // Cambiato a BoxLayout per una disposizione verticale

        // Etichetta di avvertimento
        JLabel avvisoLabel = new JLabel("<html><i>Inserisci gli orari in ordine crescente e ricordati che devi gestire " + numeroCampioni + " campioni.</i></html>");
        avvisoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orarioPanel.add(avvisoLabel);

        // Etichetta per Orario
        JLabel orarioLabel = new JLabel("ORARIO SLOT:");
        orarioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orarioPanel.add(orarioLabel);

        // Campo per inserire l'orario
        orarioField = new JTextField(10);  // Spazio maggiore per la tastiera
        orarioField.setMaximumSize(new Dimension(200, 30)); // Dimensione massima per il campo di input
        orarioPanel.add(orarioField);

        add(orarioPanel, BorderLayout.NORTH);

        // Pannello per i pulsanti
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Pulsante Aggiungi
        JButton aggiungiButton = new JButton("Aggiungi");
        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orarioInput = orarioField.getText();
                try {
                    // Converti l'input in LocalTime
                    LocalTime orario = LocalTime.parse(orarioInput);
                    Slot slot = new Slot(date, orario);
                    slots.add(slot); // Aggiungi alla lista
                    orarioField.setText(""); // Pulisci il campo di input
                    System.out.println("Orario aggiunto: " + orario);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CaricamentoViewManual.this, "Formato orario non valido. Usa HH:mm.");
                }
            }
        });
        buttonPanel.add(aggiungiButton);

        // Pulsante Fine
        JButton fineButton = new JButton("Fine");
        fineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orarioInput = orarioField.getText();
                try {
                    // Converti l'input in LocalTime
                    LocalTime orario = LocalTime.parse(orarioInput);
                    Slot slot = new Slot(date, orario);
                    slots.add(slot); // Aggiungi alla lista
                    System.out.println("Orario aggiunto: " + orario);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CaricamentoViewManual.this, "Formato orario non valido. Usa HH:mm.");
                }
                // Chiamata al metodo del controller
                Sondaggio sondaggio = controller.creaSondaggioManual(date, macchinari, slots);
                new PubblicazioneSondaggioView(controller, sondaggio);
                dispose(); // Chiude la finestra
            }
        });
        buttonPanel.add(fineButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Rendere la finestra visibile
        setVisible(true);
    }

    public static void main(String[] args) {
        new CaricamentoViewManual(new SondaggioController(), LocalDate.now(), null, 30);
    }


}

