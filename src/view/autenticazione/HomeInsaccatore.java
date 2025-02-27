package view.autenticazione;

import controller.AutenticazioneController;
import modello.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HomeInsaccatore extends JFrame {
        private AutenticazioneController controller;
        private Utente utente;

        public HomeInsaccatore(AutenticazioneController controller, Utente utente) {
            this.controller = controller;
            this.utente = utente;


            setTitle("Home Insaccatore");
            setSize(400, 200);
            setLocationRelativeTo(null); // Centra la finestra

            JPanel panel = new JPanel(new GridLayout(3,1));
            JButton btnModificaPassword = new JButton("Modifica Password");
            btnModificaPassword.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String vecchiaPassword = JOptionPane.showInputDialog("Inserisci la password attuale");
                    String nuovaPassword = JOptionPane.showInputDialog("Inserisci la nuova password:");

                    try {
                        if(!controller.verificaPassword(nuovaPassword)){
                            JOptionPane.showMessageDialog(null, "Password incorretta, la password deve contenere almeno " +
                                    "una lettera maiuscola, una minuscola, almeno un numero e un carattere speciale (es. !@#$%^&*)");
                            return;
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    try {
                        boolean successo = controller.modificaPassword(vecchiaPassword, nuovaPassword, utente);
                        if(successo){
                            JOptionPane.showMessageDialog(null, "Password modificata correttamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "Errore nella modifica password");
                        }
                    } catch (SQLException ex){
                        ex.printStackTrace();
                    }
                }
            });
            JButton btnInserisciIban = new JButton("Inserisci/modifica IBAN");
            btnInserisciIban.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String iban = JOptionPane.showInputDialog("Inserisci il tuo IBAN");
                    try {
                        boolean successo = controller.inserisciIban(iban, utente );
                        if(successo){
                            JOptionPane.showMessageDialog(null, "IBAN inserito/modificato con successo!" );}
                        else {
                            JOptionPane.showMessageDialog(null, "Errore nella inserisci il tuo IBAN");
                        }
                    } catch (SQLException ex){
                        ex.printStackTrace();
                    }
                }
            });



            JButton btnLogout = new JButton("Logout");
            btnLogout.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            panel.add(btnModificaPassword);
            panel.add(btnInserisciIban);
            panel.add(btnLogout);
            panel.add(btnLogout);
            add(panel);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
           // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           // JLabel label = new JLabel("Benvenuto Insaccatore", SwingConstants.CENTER);
           // add(label);



        }
    }

