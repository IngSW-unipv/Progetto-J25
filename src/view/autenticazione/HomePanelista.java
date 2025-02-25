package view.autenticazione;

import controller.AutenticazioneController;
import jdbc.FacedeSingletonDB;
import modello.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HomePanelista extends JFrame {
        private AutenticazioneController controller;
        public HomePanelista(AutenticazioneController controller, Utente utente) {
            this.controller = controller;

            setTitle("Home Panelista");
            setSize(400, 200);
            setLocationRelativeTo(null);
            //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           // JLabel label = new JLabel("Benvenuto Panelista", SwingConstants.CENTER);
            //add(label);
           // setLocationRelativeTo(null); // Centra la finestra
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
                    String ibanCorrente = null;
                    try {
                        ibanCorrente = FacedeSingletonDB.getInstance().getUserDAO().getIban(utente);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Errore nel recupero dell'IBAN corrente.");
                        ex.printStackTrace();
                        return;
                    }

                    int scelta = JOptionPane.showConfirmDialog(
                            null,
                            "Il tuo IBAN corrente è il seguente: " + (ibanCorrente != null ? ibanCorrente : "Non presente") + "\nVuoi cambiarlo?",
                            "Conferma modifica IBAN",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (scelta == JOptionPane.YES_OPTION) {
                        String nuovoIban = JOptionPane.showInputDialog("Inserisci il nuovo IBAN:");

                        if (nuovoIban == null || nuovoIban.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Inserimento IBAN annullato.");
                            return;
                        }

                        try {
                            boolean successo = controller.inserisciIban(nuovoIban, utente);
                            if (successo) {
                                JOptionPane.showMessageDialog(null, "IBAN inserito/modificato con successo!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Errore nell'inserimento/modifica dell'IBAN.");
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Errore durante l'aggiornamento dell'IBAN.");
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "IBAN non modificato.");
                    }
                }
            });

            panel.add(btnModificaPassword);
            panel.add(btnInserisciIban);
            add(panel);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        }
    }

