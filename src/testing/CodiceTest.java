package testing;
import modello.autenticazione.SystemAutenticazione;
import jdbc.bean.UserDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class CodiceTest {
    public static void main(String[] args) throws SQLException {
        UserDAO userDAO= new UserDAO();
        SystemAutenticazione systemAutenticazione = new SystemAutenticazione(userDAO);
        /*System.out.println("Inserire email:   ");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        systemAutenticazione.attivazioneEmail(email); */
        Scanner sc = new Scanner(System.in);
       // System.out.println("Inserire codice attivazione ");

       /* String input = sc.nextLine();

        systemAutenticazione.inizioRegistrazione(input);
        System.out.println("Inserire password:   ");
        String input2 = sc.nextLine();
        while(true){
            String risultatoValidazione = systemAutenticazione.controllaPassword(input2);
            if(risultatoValidazione.equals("VALIDA")) {
                System.out.println("Password valida!");
            break;
        } else {
            System.out.println("Errore: " + risultatoValidazione);
            System.out.println("Reinserire password ");
            input2 = sc.nextLine();
        }}
        System.out.println("Inserire luogo di nascita:   ");
        String input3 = sc.nextLine();
        System.out.println("Inserire data di nascita:    ");
        LocalDate input4 = LocalDate.parse(sc.nextLine());
        System.out.println("Inserire codice fiscale:    ");
        String input5 = sc.nextLine();
        System.out.println("Inserire residenza:    ");
        String input6 = sc.nextLine();
        System.out.println("Inserire nickname:    ");
        String input7 = sc.nextLine();
        systemAutenticazione.registrazioneUtente(input, email, input2, input3, input4, input5, input6, input7);
         */

        System.out.println("Inserire nickname o emaill:  ");
        String input8 = sc.nextLine();
        System.out.println("Inserire password:    ");
        String input9 = sc.nextLine();
        systemAutenticazione.login(input8,input9);
        sc.close();
}}


