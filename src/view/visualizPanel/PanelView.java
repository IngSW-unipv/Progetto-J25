package view.visualizPanel;

import controller.PanelController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import modello.Panelista;
import modello.Utente;
import modello.creazionePanel.Panel;

public class PanelView extends JFrame {
    private JPanel panelContainer;
    private PanelController controller;
    private Panelista panelista;

    public PanelView(PanelController controller, Panelista p) {
        this.controller = controller;
        this.panelista = p;

        setTitle("Lista Panel");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Pannello contenitore con layout orizzontale (colonne per ogni Panel)
        panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(1, 0, 10, 10)); // 1 riga, colonne dinamiche

        // Scorrimento per contenere molti panel
        JScrollPane scrollPane = new JScrollPane(panelContainer);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Carica i dati dei panel
        caricaDatiPanel();
    }

    private void caricaDatiPanel() {
        panelContainer.removeAll(); // Pulisce prima di ricaricare
        ArrayList<Panel> panels = controller.getPanels();

        for (Panel panel : panels) {
            panelContainer.add(creaColonnaPanel(panel));
        }

        panelContainer.revalidate();
        panelContainer.repaint();
    }

    private JPanel creaColonnaPanel(Panel panel) {
        JPanel panelColonna = new JPanel();
        panelColonna.setLayout(new BoxLayout(panelColonna, BoxLayout.Y_AXIS)); // Colonna verticale
        panelColonna.setBorder(BorderFactory.createTitledBorder("Panel " + panel.getId()));
        panelColonna.setBackground(panel.getEmergenza() ? Color.PINK : Color.LIGHT_GRAY);

        // Informazioni Panel
        panelColonna.add(creaLabel("Data: " + panel.getData()));
        panelColonna.add(creaLabel("Orario: " + panel.getOrarioInizio()));
        panelColonna.add(creaLabel("Macchinario: " + panel.getMacchinario()));

        // Lista utenti
        panelColonna.add(new JLabel("👤 Utenti:"));
        for (Panelista user : panel.getListaPanelisti()) {
            if (user != null) {
                panelColonna.add(creaLabel(" • " + user.getEmail()));
            }
        }

        // Se è in emergenza, aggiunge il bottone per prenotarsi
        if (panel.getEmergenza()) {
            JButton btnPrenota = new JButton("Prenotati!");
            btnPrenota.addActionListener(e -> prenotaUtente(panel.getId()));
            panelColonna.add(btnPrenota);
        }

        return panelColonna;
    }

    private JLabel creaLabel(String testo) {
        JLabel label = new JLabel(testo);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private void prenotaUtente(int panelId) {
            boolean success = controller.prenotaUtente(panelId, panelista);
            if (success) {
                JOptionPane.showMessageDialog(null, "Prenotazione avvenuta con successo!");
                caricaDatiPanel(); // Aggiorna la tabella
            } else {
                JOptionPane.showMessageDialog(null, "Errore nella prenotazione.");
            }
    }

}
