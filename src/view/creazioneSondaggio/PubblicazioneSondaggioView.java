package view.creazioneSondaggio;

import controller.SondaggioController;
import modello.creazionePanel.Sondaggio;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class PubblicazioneSondaggioView extends JFrame {

    public PubblicazioneSondaggioView(SondaggioController controller, Sondaggio sondaggio) {
        setTitle("Sondaggio Generato");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Pannello info sondaggio (senza ID)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 1)); // Ora ha solo 1 riga
        infoPanel.add(new JLabel("📅 Data: " + sondaggio.getData()));

        add(infoPanel, BorderLayout.NORTH);

        // Formattatore per gli orari
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Estrai e formatta gli orari
        List<String> orariSlot = sondaggio.getSlots().keySet().stream()
                .map(time -> time.format(timeFormatter))
                .sorted()
                .collect(Collectors.toList());

        // Converti la lista in un array per JList
        JList<String> listOrari = new JList<>(orariSlot.toArray(new String[0]));
        listOrari.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(listOrari);

        add(scrollPane, BorderLayout.CENTER);

        // Pulsante di pubblicazione
        JButton confermaButton = new JButton("✅ Pubblica Sondaggio");
        confermaButton.addActionListener(e -> {
            controller.pubblicaSondaggio(sondaggio);
            JOptionPane.showMessageDialog(this, "Sondaggio pubblicato con successo!");
            dispose();
        });

        add(confermaButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
