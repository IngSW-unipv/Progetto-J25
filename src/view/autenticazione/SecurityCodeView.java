package view.autenticazione;

import controller.AutenticazioneController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SecurityCodeView extends JFrame {
    private JTextField codiceField;
    private AutenticazioneController autenticazioneController;

    public SecurityCodeView(AutenticazioneController autenticazioneController, String email) {
        this.autenticazioneController = autenticazioneController;

        setTitle("CODICE OTP");
        setSize(400,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));

        JLabel codiceLabel = new JLabel("Codice:");
        codiceField = new JTextField();
        JButton verifica = new JButton("Verifica");

        verifica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codice = codiceField.getText();
                boolean codiceCorretto = false;
                try {
                    codiceCorretto = autenticazioneController.verificaCodice(codice);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (codiceCorretto) {
                    new RegistrazioneView(autenticazioneController, email);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Codice non corretto, riprova");
                }

            }
        });

        panel.add(codiceLabel);
        panel.add(codiceField);
        panel.add(verifica);
        add(panel);

        setVisible(true);



    }


}
