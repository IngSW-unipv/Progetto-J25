package view.autenticazione;

import controller.AutenticazioneController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EmailRegistrazioneView extends JFrame {
    private JTextField emailField;
    private AutenticazioneController controller;

    public EmailRegistrazioneView(AutenticazioneController controller) throws SQLException {
        this.controller = controller;

        setTitle("Registrazione-Email");
        setSize(400,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));

        JLabel emailLabel = new JLabel("Inserisci Email: ");
        emailField = new JTextField();
        JButton registrazioneButton = new JButton("Invio");

        registrazioneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                boolean emailValida = false;
                try {
                    emailValida = controller.attivaEmail(email);
                    dispose();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if(emailValida) {
                    new SecurityCodeView(controller, email);
                    } else {
                    JOptionPane.showMessageDialog(null, "Email non valido");
                    new MainView(controller);
                    dispose();
                }
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(registrazioneButton);
        add(panel);
        setVisible(true);
    }



}
