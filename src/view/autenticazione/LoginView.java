package view.autenticazione;

import controller.AutenticazioneController;
import jdbc.FacadeSingletonDB;
import modello.Insaccatore;
import modello.Panelista;
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
                                Panelista pa= new Panelista(utente.getId(), utente.getEmail(), utente.getNome(), utente.getCognome(), utente.getLuogoNascita(), utente.getDataNascita(), utente.getCodiceFiscale(),
                                        utente.getNickname(), utente.getPassword(), utente.getRuolo(), utente.getEmail(), 0);
                                new HomePanelista(autenticazioneController, pa);
                                break;
                            case "in":
                                Insaccatore in = FacadeSingletonDB.getInstance().getUserDAO().getInsaccatore(utente.getId());
                                new HomeInsaccatore(autenticazioneController, in);

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

        JButton btnRecuperaCredenziali = new JButton("Recupera credenziali");
        btnRecuperaCredenziali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Chiede all'utente di inserire la propria email
                String email = JOptionPane.showInputDialog("Inserisci la tua email:");

                if (email != null && !email.isEmpty()) {

                        // Verifica se l'email esiste e invia le credenziali se trovata
                        boolean successo = autenticazioneController.recuperoCredenziali(email);
                        if (successo) {
                            JOptionPane.showMessageDialog(LoginView.this, "Le tue credenziali sono state inviate tramite notifica.");
                        } else {
                            JOptionPane.showMessageDialog(LoginView.this, "Email non trovata.");
                        }


                    }  else  {
                    JOptionPane.showMessageDialog(LoginView.this, "Errore durante il recupero delle credenziali.");
                }
                }

        });


// Aggiungi il pulsante al pannello esistente


        panel.add(lblEmailOrNickname);
        panel.add(emailOrNicknameField);
        panel.add(lblPassword);
        panel.add(passwordField);
        panel.add(btnLogin);
        panel.add(btnRecuperaCredenziali);
        add(panel);
        setVisible(true);
    }
}
