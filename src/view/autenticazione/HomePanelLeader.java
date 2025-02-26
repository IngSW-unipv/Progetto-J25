package view.autenticazione;
import controller.AutenticazioneController;
import controller.CampioneController;
import controller.SondaggioController;
import modello.Utente;
import modello.archiviazioneCampione.SystemCampione;
import view.archiviazioneCampione.CampioneView;
import view.creazioneSondaggio.SondaggioView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HomePanelLeader extends JFrame {
    private AutenticazioneController controller;

    public HomePanelLeader(AutenticazioneController controller, Utente utente) {
        this.controller = controller;

        setTitle("Home Panel Leader");
        setSize(400, 200);

        //JLabel label = new JLabel("Benvenuto Panel Leader", SwingConstants.CENTER);
        //add(label);

        setLocationRelativeTo(null); // Centra la finestra

        JPanel panel = new JPanel(new GridLayout(4,1));
        JButton btnCreazioneSondaggio = new JButton("Creazione Sondaggio");
        btnCreazioneSondaggio.addActionListener(e -> new SondaggioView(new SondaggioController()));
        JButton btnModificaPassword = new JButton("Modifica Password");
        JButton btnArchiviazioneCampioni = new JButton("archiviazione campione");
        btnArchiviazioneCampioni.addActionListener(e -> new CampioneView(new CampioneController(new SystemCampione())));
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

        JButton btnCambiaRuolo = new JButton("Cambia Ruolo");
        btnCambiaRuolo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ruolo = JOptionPane.showInputDialog("Inserisci il nuovo ruolo");
                String idUtente = JOptionPane.showInputDialog("Inserisci l'id dell'utente");
                try {
                    boolean successo = controller.cambioRuolo(Integer.parseInt(idUtente), ruolo);
                    if(successo){
                        JOptionPane.showMessageDialog(null, "Ruolo inserito correttamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Errore nel cambiamento del ruolo");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
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
        panel.add(btnCambiaRuolo);
        panel.add(btnCreazioneSondaggio);
        panel.add(btnArchiviazioneCampioni);
        panel.add(btnLogout);
        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setVisible(true);
    }
}


