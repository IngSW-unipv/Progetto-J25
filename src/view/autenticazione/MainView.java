package view.autenticazione;

import controller.AutenticazioneController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainView extends JFrame {
    private AutenticazioneController controller;
    public MainView(AutenticazioneController controller ) {
        this.controller = controller;


        setTitle("Autenticazione");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));

        JButton btnLogin = new JButton("Login");
        JButton btnRegistrazione = new JButton("Registrazione");

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                   new LoginView(controller);
                   dispose();
            }
        });

        btnRegistrazione.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new EmailRegistrazioneView(controller);
                    dispose();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(btnLogin);
        panel.add(btnRegistrazione);
        add(panel);
        setVisible(true);
    }
   /* public static void main(String[] args) {
        new MainView();
    } */
}



