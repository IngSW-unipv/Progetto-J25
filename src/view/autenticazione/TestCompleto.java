package view.autenticazione;

import controller.AutenticazioneController;

import java.sql.SQLException;

public class TestCompleto {
    public static void main(String[] args) {

        try {
            MainView main = new MainView(new AutenticazioneController());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
