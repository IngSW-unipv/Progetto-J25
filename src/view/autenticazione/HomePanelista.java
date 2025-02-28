package view.autenticazione;

import controller.AutenticazioneController;
import controller.PanelController;
import jdbc.FacadeSingletonDB;
import modello.Panelista;
import view.prenotazionePanel.PrenotazioneView;
import view.visualizPanel.PanelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HomePanelista extends JFrame {
        private AutenticazioneController controller;
        private Panelista panelista;
        public HomePanelista(AutenticazioneController controller, Panelista panelista) {
            this.controller = controller;
            this.panelista = panelista;


            setTitle("Home Panelista");
            setSize(400, 200);
            setLocationRelativeTo(null);
            //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           // JLabel label = new JLabel("Benvenuto Panelista", SwingConstants.CENTER);
            //add(label);
           // setLocationRelativeTo(null); // Centra la finestra
            JPanel panel = new JPanel(new GridLayout(3,1));
            JButton btnVisualizPanel = new JButton("Visualizza Panel");
            btnVisualizPanel.addActionListener(e -> new PanelView(new PanelController(), panelista));

            JButton btnVisualizSondaggio = new JButton("Visualizza Sondaggio");
            btnVisualizSondaggio.addActionListener(e -> new PrenotazioneView(panelista));
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
                        boolean successo = controller.modificaPassword(vecchiaPassword, nuovaPassword, panelista);
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
                        ibanCorrente = FacadeSingletonDB.getInstance().getUserDAO().getIban(panelista);
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
                            boolean successo = controller.inserisciIban(nuovoIban, panelista);
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

            JButton btnLogout = new JButton("Logout");
            btnLogout.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            panel.add(btnModificaPassword);
            panel.add(btnVisualizPanel);
            panel.add(btnVisualizSondaggio);
            panel.add(btnModificaPassword);
            panel.add(btnInserisciIban);
            panel.add(btnLogout);
            add(panel);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        }
    }

