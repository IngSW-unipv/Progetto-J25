package modello.email;

import modello.Utente;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class EmailSender {

    public static void sendEmail(String to, String subject, String body) {
        final String username = "progetto.j25@gmail.com"; // l'indirizzo email del nosto sistema :)
        final String password = "hjjf rsqy vynk rpmh"; // password per app

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Email inviata con successo!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
// TEST DI NOTIFICAMESSAGE
    public static void main(String[] args) {
        Utente utente1 = new Utente("khawlaouaadou1@gmail.com");
        Utente utente2 = new Utente("khawla.ouaadou01@universitadipavia.it");
        ArrayList<Utente> utentes = new ArrayList<>();
        utentes.add(utente1);
        utentes.add(utente2);
        NotificaMessage notifica = new NotificaMessage(utentes, "Test Email", "Questa è un'email di prova.");
        notifica.notificaObserver();
    }
}
