package view.analisiCampione;


import controller.AnalisiController;
import modello.analisiCampione.AnalisiCampione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;


public class AnalisiCampioneView  {

    private JFrame frame;
    private JToolBar toolBar;
    private JButton btnInserisci, btnModifica, btnElimina;
    private AnalisiController analisiController;
    private JTextArea displayArea;


    public AnalisiCampioneView(AnalisiController analisiController) {
       
        this.analisiController = analisiController;
        frame = new JFrame("Analisi Campioni");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        toolBar = new JToolBar();
        btnInserisci = new JButton("Inserisci Analisi");
        btnModifica = new JButton("Modifica Analisi");
        btnElimina = new JButton("Elimina Analisi");

        toolBar.add(btnInserisci);
        toolBar.add(btnModifica);
        toolBar.add(btnElimina);

        frame.add(toolBar, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        btnInserisci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserisciAnalisi();
            }
        });

        btnModifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificaAnalisi();
            }
        });

        btnElimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaAnalisi();
            }
        });


        frame.setVisible(true);
    }

    public void inserisciAnalisi() {

        try {
            int idCampione = Integer.parseInt(JOptionPane.showInputDialog("Inserisci l'id del campione"));
            int idPanel = Integer.parseInt(JOptionPane.showInputDialog("Inserisci l'id del panel"));
            LocalTime inizioAnalisi = LocalTime.parse(JOptionPane.showInputDialog("Inserisci l'orario di inizio analisi"));
            LocalTime fineAnalisi = LocalTime.parse(JOptionPane.showInputDialog("Inserisci l'orario di fine analisi"));
            double gradazione = Double.parseDouble(JOptionPane.showInputDialog("Inserisci la gradazione"));

            AnalisiCampione analisi = new AnalisiCampione(inizioAnalisi, fineAnalisi, gradazione);

            if(analisiController.inserisciAnalisi(idCampione, idPanel, analisi)) {
                displayArea.append("Analisi inserita correttamente\n");
            } else {
                displayArea.append("Errore durante l'inserimento dell'analisi\n");
            }
        }catch(Exception e) {
            displayArea.append("Errore durante l'inserimento dell'analisi\n");
        }
    }


    public void modificaAnalisi() {

        try {
            int idCampione = Integer.parseInt(JOptionPane.showInputDialog("Inserisci l'id del campione"));
            int idPanel = Integer.parseInt(JOptionPane.showInputDialog("Inserisci l'id del panel"));
            LocalTime inizioAnalisi = LocalTime.parse(JOptionPane.showInputDialog("Inserisci l'orario di inizio analisi"));
            LocalTime fineAnalisi = LocalTime.parse(JOptionPane.showInputDialog("Inserisci l'orario di fine analisi"));
            double gradazione = Double.parseDouble(JOptionPane.showInputDialog("Inserisci la gradazione"));

            AnalisiCampione analisi = new AnalisiCampione(inizioAnalisi, fineAnalisi, gradazione);

            if(analisiController.modificaAnalisi(idCampione, idPanel, analisi)) {
                displayArea.append("Analisi modificata correttamente\n");
            } else {
                displayArea.append("Errore durante la modifica dell'analisi\n");
            }
        }catch(Exception e) {
            displayArea.append("Errore durante la modifica dell'analisi\n");
        }
    }
    
    public void eliminaAnalisi() {

        try {
            int idCampione = Integer.parseInt(JOptionPane.showInputDialog("Inserisci l'id del campione"));
            int idPanel = Integer.parseInt(JOptionPane.showInputDialog("Inserisci l'id del panel"));
            LocalTime inizioAnalisi = LocalTime.parse(JOptionPane.showInputDialog("Inserisci l'orario di inizio analisi"));
            LocalTime fineAnalisi = LocalTime.parse(JOptionPane.showInputDialog("Inserisci l'orario di fine analisi"));
            double gradazione = Double.parseDouble(JOptionPane.showInputDialog("Inserisci la gradazione"));
        
            AnalisiCampione analisi = new AnalisiCampione(inizioAnalisi, fineAnalisi, gradazione);

            if(analisiController.eliminaAnalisi(idCampione, idPanel, analisi)) {
                displayArea.append("Analisi eliminata correttamente\n");
            } else {
                displayArea.append("Errore durante l'eliminazione dell'analisi\n");
            }
    
        }catch(Exception e) {
            displayArea.append("Errore durante l'eliminazione dell'analisi\n");
        }
    }



    public static void main(String[] args) {
        AnalisiController analisiController = new AnalisiController();
        AnalisiCampioneView analisiView = new AnalisiCampioneView(analisiController);
    }

}













