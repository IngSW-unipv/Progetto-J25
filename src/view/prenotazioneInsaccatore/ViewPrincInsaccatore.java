package view.prenotazioneInsaccatore;

import javax.swing.*;
import controller.PrenotaInsacController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ViewPrincInsaccatore extends JFrame {
    // attributi
    private int idInsaccatore;
    private PrenotaInsacController controller;

    public ViewPrincInsaccatore(int idInsaccatore) {
        super("Sezione Insaccatore");
        this.idInsaccatore = idInsaccatore;

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout()); // flowlayout mi serve per disporre gli elementi in modo orizzontale

        // creo il pulsante che permette di accedere al calendario dei turni
        JButton botCalendario = new JButton("Calendario Turni");

        // creo l'oggetto ActionListener per aprire la finestra del calendario
        ActionListener interazioneCalendario = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.apriViewInsaccatore();
            }
        };
        botCalendario.addActionListener(interazioneCalendario); // aggiungo l'azione al pulsante
        add(botCalendario);

        // pulsante per generare i turni
        JButton botGenTurni = new JButton("Genera i turni");

        // creo l'oggetto ActionListener per generare i turni
        ActionListener interazioneTurni = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // richiesta della data del prossimo lunedì
                String dataIn;
                LocalDate data = null;
                boolean dataValida = false;

                while (!dataValida) {
                    dataIn = JOptionPane.showInputDialog("Inserisci il prossimo lunedì (yyyy-MM-dd):");
                    if (dataIn == null) return; // utente ha premuto "Annulla"

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        data = LocalDate.parse(dataIn, formatter);
                        dataValida = true; // se il parsing va a buon fine, esce dal ciclo
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(null, "Formato data non valido. Usa yyyy-MM-dd.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // creazione della combo box per la durata
                String[] duratePossibili = {"30", "60", "90", "120"};
                JComboBox<String> durataComboBox = new JComboBox<>(duratePossibili);

                // mostra il JOptionPane con la combo box
                int result = JOptionPane.showConfirmDialog(null, durataComboBox, "Seleziona la durata in minuti", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    try {
                        int durata = Integer.parseInt((String) durataComboBox.getSelectedItem());
                        controller.generaSettimanaTurni(data, durata);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Errore durante la generazione dei turni.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };

        botGenTurni.addActionListener(interazioneTurni);
        add(botGenTurni);

        setVisible(true);
    }

    // metodo per impostare il controller nella vista principale
    public void setController(PrenotaInsacController controller) {
        this.controller = controller;
    }

    // getter e setter
    public int getIdInsaccatore() {
        return idInsaccatore;
    }

    public void setIdInsaccatore(int idInsaccatore) {
        this.idInsaccatore = idInsaccatore;
    }
}
