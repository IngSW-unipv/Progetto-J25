package view.autenticazione;

import controller.AutenticazioneController;
import controller.PanelController;
import modello.Insaccatore;
import modello.Utente;

import view.prenotazionePanel.PrenotazioneView;
import view.visualizPanel.PanelView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HomeInsaccatore extends JFrame {
        private AutenticazioneController controller;
        private Insaccatore insaccatore;
        
        public HomeInsaccatore(AutenticazioneController controller, Insaccatore insaccatore) {
            this.controller = controller;

            this.insaccatore = insaccatore;



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
                        boolean successo = controller.modificaPassword(vecchiaPassword, nuovaPassword, insaccatore);
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
                        boolean successo = controller.inserisciIban(iban, insaccatore );
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



            JButton btnVisualizSondaggio = new JButton("Visualizza Sondaggio");
            btnVisualizSondaggio.addActionListener(e -> new PrenotazioneView(insaccatore));
            JButton btnVisualizPanel = new JButton("Visualizza Panel");
            btnVisualizPanel.addActionListener(e -> new PanelView(new PanelController(), insaccatore));




            JButton btnLogout = new JButton("Logout");
            btnLogout.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            panel.add(btnModificaPassword);
            panel.add(btnInserisciIban);
            panel.add(btnLogout);
            panel.add(btnVisualizSondaggio);
            panel.add(btnVisualizPanel);
            add(panel);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
           // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           // JLabel label = new JLabel("Benvenuto Insaccatore", SwingConstants.CENTER);
           // add(label);



        }
    }

