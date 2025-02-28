package view.autenticazione;

import controller.AutenticazioneController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegistrazioneView extends JFrame {
    private JTextField nome, cognome, dataNascita, luogoNascita, residenza, codiceFiscale, nickname;
    private JPasswordField password;
    private AutenticazioneController autenticazioneController;
    private String email;
    public RegistrazioneView(AutenticazioneController autenticazioneController, String email) {
        this.autenticazioneController = autenticazioneController;
        this.email = email;

        setTitle("Registrazione");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9,2, 10,10 ));



        JLabel nomeLabel = new JLabel("Nome");
        nome = new JTextField();
        JLabel cognomeLabel = new JLabel("Cognome");
        cognome = new JTextField();
        JLabel dataNascitaLabel = new JLabel("Data Nascita");
        dataNascita = new JTextField();
        JLabel luogoNascitaLabel = new JLabel("Luogo");
        luogoNascita = new JTextField();
        JLabel residenzaLabel = new JLabel("Residenza");
        residenza = new JTextField();
        JLabel codiceFiscaleLabel = new JLabel("Codice Fiscale");
        codiceFiscale = new JTextField();
        JLabel nicknameLabel = new JLabel("Nickname");
       /* if(codiceFiscale.length()!= 16){
            JOptionPane.showMessageDialog(null, "Il codice fiscale deve essere di 16 caratteri.");
        return;
        } */

        nickname = new JTextField();
        JLabel passwordLabel = new JLabel("Password");
        password = new JPasswordField();

        panel.add(nomeLabel);
        panel.add(nome);
        panel.add(cognomeLabel);
        panel.add(cognome);
        panel.add(dataNascitaLabel);
        panel.add(dataNascita);
        panel.add(luogoNascitaLabel);
        panel.add(luogoNascita);
        panel.add(residenzaLabel);
        panel.add(residenza);
        panel.add(codiceFiscaleLabel);
        panel.add(codiceFiscale);
        panel.add(nicknameLabel);
        panel.add(nickname);
        panel.add(passwordLabel);
        panel.add(password);


        JButton registrazione = new JButton("Registrazione");
        registrazione.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    registraUtente();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(registrazione);
        add(panel);
        setVisible(true);
    }

    public void registraUtente() throws SQLException {
        String nome = this.nome.getText();
        String cognome = this.cognome.getText();
        LocalDate dataNascita = LocalDate.parse(this.dataNascita.getText());
        String luogoNascita = this.luogoNascita.getText();
        String residenza = this.residenza.getText();
        String codiceFiscale = this.codiceFiscale.getText();
        String nickname = this.nickname.getText();
        String password = new String(this.password.getPassword());

        if(!autenticazioneController.verificaPassword(password)){
            JOptionPane.showMessageDialog(this, "Password incorretta, la password deve contenere almeno " +
                    "una lettera maiuscola, una minuscola, almeno un numero e un carattere speciale (es. !@#$%^&*)");
            return;
        }

        autenticazioneController.registraUtente(email,password, luogoNascita, dataNascita, codiceFiscale,
               residenza , nickname);
            JOptionPane.showMessageDialog(this, "Registrazione completata con successo");
            new MainView(autenticazioneController);
            dispose();
    }

}
