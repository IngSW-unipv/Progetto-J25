package testing;
import jdbc.dao.autenticazione.SystemAutenticazione;
import jdbc.dao.autenticazione.UserDAO;

import java.sql.SQLException;
import java.util.Scanner;

import static jdbc.dao.autenticazione.SystemAutenticazione.*;

public class CodiceTest {
    public static void main(String[] args) throws SQLException {
        UserDAO userDAO= new UserDAO();
        SystemAutenticazione systemAutenticazione = new SystemAutenticazione(userDAO);
        systemAutenticazione.AttivazioneEmail("alessiaricca2209@gmail.com");
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserire codice attivazione    ");
        String input = sc.nextLine();
        sc.close();
        systemAutenticazione.InizioRegistrazione(input);

    }
}
