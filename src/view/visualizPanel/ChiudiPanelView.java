package view.visualizPanel;

import javax.swing.*;

import controller.ConfPresenzaController;
import controller.PanelController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChiudiPanelView extends JFrame {
    private JComboBox<Integer> panelDropdown;
    private JPanel panelistiPanel;
    private JButton chiudiButton;
    private PanelController panelController;
    private ConfPresenzaController conf;

    public ChiudiPanelView(PanelController controller, ConfPresenzaController conf) {
        this.panelController = controller;
        this.conf = conf;

        setTitle("Chiusura Panel");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Dropdown per selezionare il panel attivo
        panelDropdown = new JComboBox<>();
        aggiornaPanelDropdown();

        // Pannello per selezionare i panelisti presenti
        panelistiPanel = new JPanel();
        panelistiPanel.setLayout(new GridLayout(0, 1));

        // Bottone per chiudere il panel
        chiudiButton = new JButton("Chiudi Panel");
        chiudiButton.addActionListener(e -> chiudiPanel());

        // Layout
        add(panelDropdown, BorderLayout.NORTH);
        add(new JScrollPane(panelistiPanel), BorderLayout.CENTER);
        add(chiudiButton, BorderLayout.SOUTH);

        // Aggiorna panelisti quando cambia il panel selezionato
        panelDropdown.addActionListener(e -> aggiornaPanelisti());

        aggiornaPanelDropdown();
        setVisible(true);
    }

    private void aggiornaPanelDropdown() {
        ArrayList<Integer> panelIds = panelController.getIdPanelsAttivi();
        panelDropdown.removeAllItems();
        for (int id : panelIds) {
            panelDropdown.addItem(id);
        }
        if (!panelIds.isEmpty()) {
            aggiornaPanelisti();
        }
    }

    private void aggiornaPanelisti() {
        if (panelistiPanel == null) {
            System.err.println("Errore: panelistiPanel è null");
            return;
        }



        panelistiPanel.removeAll();
        Integer panelId = (Integer) panelDropdown.getSelectedItem();
        if (panelId != null) {
            List<String> panelisti = panelController.getPanelisti(panelId);
            for (String email : panelisti) {
                JCheckBox checkBox = new JCheckBox(email, true);
                panelistiPanel.add(checkBox);
            }
        }
        panelistiPanel.revalidate();
        panelistiPanel.repaint();
    }

    private void chiudiPanel() {
        Integer panelId = (Integer) panelDropdown.getSelectedItem();
        if (panelId == null) return;

        Map<Integer, Boolean> presenze = new HashMap<>();
        for (Component comp : panelistiPanel.getComponents()) {
            if (comp instanceof JCheckBox checkBox) {
                int userId = conf.getPanelistaIdByEmail(checkBox.getText());
                presenze.put(userId, checkBox.isSelected());
            }
        }

        boolean success = conf.presenzaEChiusuraPanel(panelId, presenze, LocalTime.now());
        if (success) {
            JOptionPane.showMessageDialog(this, "Panel chiuso con successo!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Errore nella chiusura del panel.");
        }
    }

    public static void main(String[] args) {
        PanelController panelController = new PanelController();
        ConfPresenzaController conf = new ConfPresenzaController();
        new ChiudiPanelView(panelController, conf);

        System.out.println(panelController.getPanels().size());
    }
}
