package view.autenticazione;

import javax.swing.*;

    public class HomeOperatore extends JFrame {
        public HomeOperatore() {
            setTitle("Home Operatore");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel label = new JLabel("Benvenuto Operatore", SwingConstants.CENTER);
            add(label);
            setLocationRelativeTo(null); // Centra la finestra
            setVisible(true);
        }
    }

