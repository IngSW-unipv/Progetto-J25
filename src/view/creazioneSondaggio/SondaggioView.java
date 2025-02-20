package view.creazioneSondaggio;

import controller.SondaggioController;
import modello.creazionePanel.Macchinario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class SondaggioView extends JFrame {
    private JTextField campioniField;
    private JTextField dataField;
    private JPanel macchinariPanel;
    private JButton avantiButton;
    private SondaggioController controller;

    public SondaggioView(SondaggioController controller) {
        this.controller = controller;
        setTitle("Creazione Sondaggio");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Spaziatura tra i componenti

        // Pannello superiore per i campi di input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margini tra i componenti
        gbc.anchor = GridBagConstraints.WEST;

        // Numero Campioni
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Numero Campioni:"), gbc);
        gbc.gridx = 1;
        campioniField = new JTextField();
        campioniField.setPreferredSize(new Dimension(150, 25)); // Dimensione ridotta
        inputPanel.add(campioniField, gbc);

        // Data
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Data:"), gbc);
        gbc.gridx = 1;
        dataField = new JTextField();
        dataField.setPreferredSize(new Dimension(150, 25));
        inputPanel.add(dataField, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // Pannello per i macchinari disponibili
        macchinariPanel = new JPanel();
        macchinariPanel.setLayout(new BoxLayout(macchinariPanel, BoxLayout.Y_AXIS));
        macchinariPanel.setBorder(BorderFactory.createTitledBorder("Seleziona Macchinari"));

        ArrayList<Macchinario> macchinari = controller.getMacchinariDisponibili();

        for (Macchinario macchinario : macchinari) {
            JCheckBox checkBox = new JCheckBox("ID: " + macchinario.getId());
            macchinariPanel.add(checkBox);
        }

        JScrollPane scrollPane = new JScrollPane(macchinariPanel);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        add(scrollPane, BorderLayout.CENTER);

        // Pulsante Avanti
        avantiButton = new JButton("Avanti");
        avantiButton.setPreferredSize(new Dimension(100, 30)); // Dimensione ridotta
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(avantiButton);
        add(buttonPanel, BorderLayout.SOUTH);

        avantiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaiAllaModalita(macchinari);
            }
        });

        setLocationRelativeTo(null); // Centra la finestra
        setVisible(true);
    }

    private void vaiAllaModalita(ArrayList<Macchinario> macchinari) {
        int campioni = Integer.parseInt(campioniField.getText());
        LocalDate data = LocalDate.parse(dataField.getText());

        ArrayList<Macchinario> macchinariInUso = new ArrayList<>();
        for (Component comp : macchinariPanel.getComponents()) {
            if (comp instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) comp;
                if (checkBox.isSelected()) {
                    for (Macchinario macchinario : macchinari) {
                        if (checkBox.getText().contains(String.valueOf(macchinario.getId()))) {
                            macchinariInUso.add(macchinario);
                        }
                    }
                }
            }
        }

        new ModalitaView(controller, campioni, data, macchinariInUso);
        dispose();
    }

    public static void main(String[] args) {
        new SondaggioView(new SondaggioController());
    }
}

