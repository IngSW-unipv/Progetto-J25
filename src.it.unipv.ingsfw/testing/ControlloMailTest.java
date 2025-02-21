package testing;
import jdbc.dao.autenticazione.UserDAO;

import java.sql.SQLException;

public class ControlloMailTest {
    public static void main(String[] args) {
        try{
            UserDAO userDAO = new UserDAO();
            String email = "test@example.com";
            boolean risultato = userDAO.controlloEmail(email);
            System.out.println(risultato);
    } catch(SQLException e){
        e.printStackTrace();
    }
} }
