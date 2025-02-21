package view.creazioneSondaggio;

import controller.SondaggioController;
import modello.creazionePanel.Macchinario;
import modello.creazionePanel.Sondaggio;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CaricamentoViewAuto extends JFrame {
    public CaricamentoViewAuto(SondaggioController controller, int numCampioni, LocalDate data, ArrayList<Macchinario> macchinari) {
        setTitle("Elaborazione...");
        setSize(200, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Creazione automatica in corso...");
        add(label);

        Timer timer = new Timer(2000, null);
        timer.addActionListener(e -> {
            System.out.println("Timer scattato: creazione sondaggio in corso...");

            Sondaggio sondaggio = controller.creaSondaggioAuto(numCampioni, data, macchinari);

            System.out.println("Sondaggio creato con ID: " + sondaggio.getId());

            new PubblicazioneSondaggioView(controller, sondaggio);

            timer.stop(); // 🔴 Ferma il timer dopo il primo scatto
            dispose();
        });
        timer.setRepeats(false); // 🔴 Impedisce che il timer si ripeta
        timer.start();



        setVisible(true);
    }
}

