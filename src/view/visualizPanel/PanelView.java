package view.visualizPanel;


import controller.PanelController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


import jdbc.FacadeSingletonDB;
import modello.Panelista;
import modello.Utente;
import modello.creazionePanel.Panel;
import modello.prenotazionePanel.SystemPrenotazione;

public class PanelView extends JFrame {
    private PanelController controller;
    private Panelista panelista;
    private JPanel panelContainer;

    public PanelView(PanelController controller, Panelista panelista) {
        this.controller = controller;
        this.panelista = panelista;

        setTitle("Lista Panel");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Pannello contenitore con layout FlowLayout (ogni panel sarà uno sopra l'altro o affiancato)
        panelContainer = new JPanel();
        panelContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // Allineamento a sinistra con 20px di spazio tra i pannelli

        // Scorrimento per contenere molti panel
        JScrollPane scrollPane = new JScrollPane(panelContainer);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Carica i dati dei panel
        caricaDatiPanel();

        setVisible(true);
    }

    private void caricaDatiPanel() {
        panelContainer.removeAll(); // Pulisce prima di ricaricare

        ArrayList<Panel> panels = controller.getPanels(); // Supponiamo che il controller restituisca una lista di panel

        for (Panel panel : panels) {
            panelContainer.add(creaColonnaPanel(panel)); // Aggiungi una colonna per ogni panel
        }

        panelContainer.revalidate();  // Rende visibili i cambiamenti nel contenitore
        panelContainer.repaint();  // Rende visibili i cambiamenti visivi
    }

    private JPanel creaColonnaPanel(Panel panel) {
        JPanel panelColonna = new JPanel();
        panelColonna.setLayout(new BoxLayout(panelColonna, BoxLayout.Y_AXIS)); // Layout verticale per ogni panel

        // Imposta il bordo e lo sfondo per migliorare l'aspetto visivo
        panelColonna.setBorder(BorderFactory.createTitledBorder("Panel " + panel.getId()));
        panelColonna.setBackground(panel.getEmergenza() ? new Color(255, 182, 193) : new Color(211, 211, 211)); // Colore di emergenza (rosa) o grigio chiaro
        panelColonna.setPreferredSize(new Dimension(300, 400)); // Impostiamo una dimensione preferita per ogni colonna

        // Aggiungi la rData, ID del Panel, ID del Macchinario
        panelColonna.add(creaLabel("Data: " + panel.getData()));
        panelColonna.add(creaLabel("ID Panel: " + panel.getId()));
        panelColonna.add(creaLabel("Macchinario ID: " + panel.getMacchinario().getId()));

        // Lista utenti
        panelColonna.add(new JLabel("👤 Utenti:"));
        StringBuilder builder = new StringBuilder();

        for (Panelista user : panel.getListaPanelisti()) {
            if (user != null) {
                builder.append(user.getEmail()).append("\n");
            }
        }
        panelColonna.add(creaLabel(builder.toString()));

        // Se il panel è in emergenza, aggiungi il bottone per prenotarsi
        if (panel.getEmergenza()) {
            JButton btnPrenota = new JButton("Prenotati!");
            btnPrenota.setBackground(Color.CYAN); // Colore del bottone
            btnPrenota.setFont(new Font("Arial", Font.BOLD, 14)); // Font del bottone
            btnPrenota.addActionListener(e -> prenotaUtente(panel.getId()));
            panelColonna.add(btnPrenota);
        } else{
            JButton btnCancella = new JButton("Cancellati");
            btnCancella.setBackground(Color.CYAN);
            btnCancella.setFont(new Font("Arial", Font.BOLD, 14));
            btnCancella.addActionListener(e -> {
                cancellaUtente(panel.getId());
            });
            panelColonna.add(btnCancella);
        }

        return panelColonna;
    }

    private JLabel creaLabel(String testo) {
        JLabel label = new JLabel(testo);
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Allinea il testo al centro
        label.setFont(new Font("Arial", Font.PLAIN, 12)); // Impostiamo un font di base
        label.setForeground(Color.DARK_GRAY); // Colore del testo
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Spazio attorno al testo
        return label;
    }

    private void prenotaUtente(int panelId) {
        boolean success = controller.prenotaUtente(panelId, panelista);
        if (success) {
            JOptionPane.showMessageDialog(null, "Prenotazione avvenuta con successo!");
            caricaDatiPanel(); // Ricarica i panel
        } else {
            JOptionPane.showMessageDialog(null, "Errore nella prenotazione.");
        }
    }

    public void cancellaUtente(int panelId){
        boolean success = controller.cancellaUtente(panelId, panelista);
        if (success) {
            JOptionPane.showMessageDialog(null, "Cancellazione avvenuta con successo!");
            caricaDatiPanel(); // Ricarica i panel
        } else {
            JOptionPane.showMessageDialog(null, "Errore nella Cancellazione.");
        }
    }

    public static void main(String[] args) {

    }
}

