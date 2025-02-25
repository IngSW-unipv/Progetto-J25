package view.autenticazione;

import controller.AutenticazioneController;
import modello.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginView extends JFrame {
    private AutenticazioneController autenticazioneController;
    private JTextField emailOrNicknameField;
    private JPasswordField passwordField;

    public LoginView(AutenticazioneController autenticazioneController ) {
        this.autenticazioneController = autenticazioneController;

        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3,2));

        JLabel lblEmailOrNickname = new JLabel("Email o nickname");
        emailOrNicknameField = new JTextField();
        JLabel lblPassword = new JLabel("Password");
        passwordField = new JPasswordField();

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String emailOrNickname = emailOrNicknameField.getText(); // da passare al metodo
                String password = new String(passwordField.getPassword());
                try {
                    Utente utente = autenticazioneController.login(emailOrNickname, password);
                    if (utente != null) {
                        switch (utente.getRuolo()) {
                            case "pl":
                                new HomePanelLeader(autenticazioneController, utente);
                                break;
                            case "pa":
                                new HomePanelista(autenticazioneController, utente);
                                break;
                            case "in":
                                new HomeInsaccatore(autenticazioneController, utente);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Ruolo non valido");
                                dispose();

                        }
                    } else {
                        JOptionPane.showMessageDialog(LoginView.this, "Credenziali errate.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginView.this, "Errore durante il login");

                }
            }
            });
        panel.add(lblEmailOrNickname);
        panel.add(emailOrNicknameField);
        panel.add(lblPassword);
        panel.add(passwordField);
        panel.add(btnLogin);
        add(panel);
        setVisible(true);
    }
}
