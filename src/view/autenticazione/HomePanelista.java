package view.autenticazione;

import javax.swing.*;

    public class HomePanelista extends JFrame {
        public HomePanelista() {
            setTitle("Home Panelista");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel label = new JLabel("Benvenuto Panelista", SwingConstants.CENTER);
            add(label);
            setLocationRelativeTo(null); // Centra la finestra
            setVisible(true);
        }
    }

