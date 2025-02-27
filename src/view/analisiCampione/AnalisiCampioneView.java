package view.analisiCampione;


import controller.AnalisiController;
import controller.CampioneController;
import modello.analisiCampione.AnalisiCampione;
import modello.archiviazioneCampione.SystemCampione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;


public class AnalisiCampioneView  {

    private JFrame frame;
    private JToolBar toolBar;
    private JButton btnInserisci, btnModifica, btnElimina;
    private AnalisiController analisiController;
    private CampioneController campioneController;
    private JTextArea displayArea;


    public AnalisiCampioneView(AnalisiController analisiController, CampioneController campioneController) {
       
        this.analisiController = analisiController;
        this.campioneController = campioneController;
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

            // Recupero gli ID dei campioni non analizzati
            ArrayList<Integer> idCampioniNonAnalizzati = campioneController.getSystemCampione().getCampioniNonAnalizzati();
            

            if(idCampioniNonAnalizzati.isEmpty()) {
                displayArea.append("Non ci sono campioni da analizzare\n");
                return;
            }

            // Creo la JComboBox per selezionare il campione
            JComboBox<Integer> idCampioneComboBox = new JComboBox<>(idCampioniNonAnalizzati.toArray(new Integer[0]));


            // Creo i campi di input

            JTextField idPanelField = new JTextField();
            JTextField inizioAnalisiField = new JTextField();
            JTextField fineAnalisiField = new JTextField();
            JTextField gradazioneField = new JTextField();

            // Creo il pannello per l'inserimento dei dati
            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("ID Campione"));
            panel.add(idCampioneComboBox);
            panel.add(new JLabel("ID Panel"));
            panel.add(idPanelField);
            panel.add(new JLabel("Orario Inizio Analisi  (hh:mm:ss)"));
            panel.add(inizioAnalisiField);
            panel.add(new JLabel("Orario Fine Analisi  (hh:mm:ss)"));
            panel.add(fineAnalisiField);
            panel.add(new JLabel("Gradazione"));
            panel.add(gradazioneField);

            int result = JOptionPane.showConfirmDialog(frame, panel, "Inserisci i dati dell'analisi", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {

                int idCampione = (int) idCampioneComboBox.getSelectedItem();
                int idPanel = Integer.parseInt(idPanelField.getText());
                LocalTime inizioAnalisi = LocalTime.parse(inizioAnalisiField.getText());
                LocalTime fineAnalisi = LocalTime.parse(fineAnalisiField.getText());
                double gradazione = Double.parseDouble(gradazioneField.getText());

                AnalisiCampione analisi = new AnalisiCampione(inizioAnalisi, fineAnalisi, gradazione);

                if (analisiController.inserisciAnalisi(idCampione, idPanel, analisi) & campioneController.aggiornaCampione(idCampione, "Analizzato")) {
                    displayArea.append("Analisi inserita correttamente\n");
                } else {
                    displayArea.append("Errore durante l'inserimento dell'analisi\n");
                }
            }

        }catch(Exception e) {
            displayArea.append("Errore durante l'inserimento dell'analisi\n");
        }
    }


    public void modificaAnalisi() {

        try {

            // Recupero gli ID dei campioni non analizzati
            ArrayList<Integer> idCampioniAnalizzati = analisiController.idCampioniAnalizzati();
            ArrayList<Integer> idPanelCampioniAnalizzati = analisiController.idPanelCampioniAnalizzati();
            

            if(idCampioniAnalizzati.isEmpty() & idPanelCampioniAnalizzati.isEmpty()) {
                displayArea.append("Non ci sono campioni analizzati\n");
                return;
            }
            

            // Creo le JComboBox per selezionare l'Analisi corretta
            JComboBox<Integer> idCampioniAnalizzatiComboBox = new JComboBox<>(idCampioniAnalizzati.toArray(new Integer[0]));
            JComboBox<Integer> idPanelCampioniAnalizzatiComboBox = new JComboBox<>(idPanelCampioniAnalizzati.toArray(new Integer[0]));


            // Creo i campi di input
            JTextField inizioAnalisiField = new JTextField();
            JTextField fineAnalisiField = new JTextField();
            JTextField gradazioneField = new JTextField();

            // Creo il pannello per l'inserimento dei dati
            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("ID Campione"));
            panel.add(idCampioniAnalizzatiComboBox);
            panel.add(new JLabel("ID Panel"));
            panel.add(idPanelCampioniAnalizzatiComboBox);
            panel.add(new JLabel("Orario Inizio Analisi  (hh:mm:ss)"));
            panel.add(inizioAnalisiField);
            panel.add(new JLabel("Orario Fine Analisi  (hh:mm:ss)"));
            panel.add(fineAnalisiField);
            panel.add(new JLabel("Gradazione"));
            panel.add(gradazioneField);

            int result = JOptionPane.showConfirmDialog(frame, panel, "Inserisci i dati dell'analisi da modificare", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {

                int idCampione = (int) idCampioniAnalizzatiComboBox.getSelectedItem();
                int idPanel = (int) idPanelCampioniAnalizzatiComboBox.getSelectedItem();
                LocalTime inizioAnalisi = LocalTime.parse(inizioAnalisiField.getText());
                LocalTime fineAnalisi = LocalTime.parse(fineAnalisiField.getText());
                double gradazione = Double.parseDouble(gradazioneField.getText());

                AnalisiCampione analisi = new AnalisiCampione(inizioAnalisi, fineAnalisi, gradazione);

                if(analisiController.modificaAnalisi(idCampione, idPanel, analisi)) {
                    displayArea.append("Analisi modificata correttamente\n");
                } else {
                    displayArea.append("Errore durante la modifica dell'analisi\n");
                }
                
            }

        }catch(Exception e) {
            displayArea.append("Errore durante la modifica dell'analisi\n");
        }
    }
    
    public void eliminaAnalisi() {

        try {
            // Recupero gli ID dei campioni non analizzati
            ArrayList<Integer> idCampioniAnalizzati = analisiController.idCampioniAnalizzati();
            ArrayList<Integer> idPanelCampioniAnalizzati = analisiController.idPanelCampioniAnalizzati();
            

            if(idCampioniAnalizzati.isEmpty() & idPanelCampioniAnalizzati.isEmpty()) {
                displayArea.append("Non ci sono campioni da eliminare\n");
                return;
            }
            

            // Creo le JComboBox per selezionare l'Analisi corretta
            JComboBox<Integer> idCampioniAnalizzatiComboBox = new JComboBox<>(idCampioniAnalizzati.toArray(new Integer[0]));
            JComboBox<Integer> idPanelCampioniAnalizzatiComboBox = new JComboBox<>(idPanelCampioniAnalizzati.toArray(new Integer[0]));


            // Creo i campi di input
            JTextField inizioAnalisiField = new JTextField();
            JTextField fineAnalisiField = new JTextField();
            JTextField gradazioneField = new JTextField();

            // Creo il pannello per l'inserimento dei dati
            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("ID Campione"));
            panel.add(idCampioniAnalizzatiComboBox);
            panel.add(new JLabel("ID Panel"));
            panel.add(idPanelCampioniAnalizzatiComboBox);
            panel.add(new JLabel("Orario Inizio Analisi  (hh:mm:ss)"));
            panel.add(inizioAnalisiField);
            panel.add(new JLabel("Orario Fine Analisi  (hh:mm:ss)"));
            panel.add(fineAnalisiField);
            panel.add(new JLabel("Gradazione"));
            panel.add(gradazioneField);

            int result = JOptionPane.showConfirmDialog(frame, panel, "Inserisci i dati dell'analisi da eliminare", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {

                int idCampione = (int) idCampioniAnalizzatiComboBox.getSelectedItem();
                int idPanel = (int) idPanelCampioniAnalizzatiComboBox.getSelectedItem();
                LocalTime inizioAnalisi = LocalTime.parse(inizioAnalisiField.getText());
                LocalTime fineAnalisi = LocalTime.parse(fineAnalisiField.getText());
                double gradazione = Double.parseDouble(gradazioneField.getText());

                AnalisiCampione analisi = new AnalisiCampione(inizioAnalisi, fineAnalisi, gradazione);

                if(analisiController.eliminaAnalisi(idCampione, idPanel, analisi) & campioneController.aggiornaCampione(idCampione, "Non Analizzato")) {
                    displayArea.append("Analisi eliminata correttamente\n");
                } else {
                    displayArea.append("Errore durante la cancellazione dell'analisi\n");
                }
                
            }
    
        }catch(Exception e) {
            displayArea.append("Errore durante la cancellazione dell'analisi\n");
        }
    }



    public static void main(String[] args) {
        AnalisiController analisiController = new AnalisiController();
        SystemCampione systemCampione = new SystemCampione();  
        CampioneController campioneController = new CampioneController(systemCampione);
        AnalisiCampioneView analisiView = new AnalisiCampioneView(analisiController, campioneController);
        analisiView.frame.setVisible(true);
    }

}













