package view.autenticazione;
import javax.swing.*;
public class HomePanelLeader extends JFrame {
    public HomePanelLeader() {
        setTitle("Home Panel Leader");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Benvenuto Panel Leader", SwingConstants.CENTER);
        add(label);
        setLocationRelativeTo(null); // Centra la finestra
        setVisible(true);
    }
}


