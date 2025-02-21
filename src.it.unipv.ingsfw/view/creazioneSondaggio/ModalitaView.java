package view.creazioneSondaggio;

import controller.SondaggioController;
import modello.creazionePanel.Macchinario;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModalitaView extends JFrame {
    private SondaggioController controller;
    private int numCampioni;
    private LocalDate data;
    private ArrayList<Macchinario> macchinari;

    public ModalitaView(SondaggioController controller, int numCampioni, LocalDate data, ArrayList<Macchinario> macchinari) {
        this.controller = controller;
        this.numCampioni = numCampioni;
        this.data = data;
        this.macchinari = macchinari;

        setTitle("Seleziona Modalità");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la finestra
        setLayout(new BorderLayout(10, 10)); // Margini tra gli elementi

        // Pannello per la domanda
        JPanel messagePanel = new JPanel();
        JLabel messageLabel = new JLabel("Quale modalità vuoi usare?");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        messagePanel.add(messageLabel);
        add(messagePanel, BorderLayout.NORTH);

        // Pannello per i pulsanti con FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));

        JButton automaticaButton = new JButton("Automatica");
        JButton manualeButton = new JButton("Manuale");

        // Imposta pulsanti più bassi e stretti
        automaticaButton.setPreferredSize(new Dimension(100, 30));
        manualeButton.setPreferredSize(new Dimension(100, 30));

        // Aggiungi azioni ai pulsanti
        automaticaButton.addActionListener(e -> avviaModalitaAutomatica());
        manualeButton.addActionListener(e -> avviaModalitaManuale());

        // Aggiungi i pulsanti al pannello
        buttonPanel.add(automaticaButton);
        buttonPanel.add(manualeButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void avviaModalitaAutomatica() {
        new CaricamentoViewAuto(controller, numCampioni, data, macchinari);
        dispose();
    }

    private void avviaModalitaManuale() {
        new CaricamentoViewManual(controller, data, macchinari, numCampioni);
        dispose();
    }
}