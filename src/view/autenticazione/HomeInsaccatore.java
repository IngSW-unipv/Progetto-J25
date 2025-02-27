package view.autenticazione;

import controller.AutenticazioneController;
import controller.PrenotaInsacController;
import modello.Insaccatore;
import modello.Utente;
import view.prenotazioneInsaccatore.ViewInsaccatore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HomeInsaccatore extends JFrame {
        private AutenticazioneController controller;
        
        private int idInsaccatore;
  

        public HomeInsaccatore(AutenticazioneController controller, Insaccatore utente) {
            this.controller = controller;
            //System.out.println(utente.getId());
           this.idInsaccatore = utente.getId();

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
            
            //BOTTONE PER ACCEDERE AL CALENDARIO DEI TURNI:
            JButton botCalendario = new JButton("Calendario Turni");

            // creo l'oggetto ActionListener per aprire la finestra del calendario
            ActionListener interazioneCalendario = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ViewInsaccatore(idInsaccatore);
                }
            };
            botCalendario.addActionListener(interazioneCalendario); // aggiungo l'azione al pulsante
            

            
            
            panel.add(btnModificaPassword);
            panel.add(btnInserisciIban);
            panel.add(botCalendario);
            add(panel);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
           // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           // JLabel label = new JLabel("Benvenuto Insaccatore", SwingConstants.CENTER);
           // add(label);



        }
    }

