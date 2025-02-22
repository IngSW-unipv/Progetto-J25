package view.autenticazione;

import javax.swing.*;

    public class HomeInsaccatore extends JFrame {
        public HomeInsaccatore() {
            setTitle("Home Insaccatore");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel label = new JLabel("Benvenuto Insaccatore", SwingConstants.CENTER);
            add(label);
            setLocationRelativeTo(null); // Centra la finestra
            setVisible(true);
        }
    }

