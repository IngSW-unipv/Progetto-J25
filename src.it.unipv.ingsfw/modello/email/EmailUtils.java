package modello.email;

import java.util.List;
// non so se possa servirci per ora la lascio qua
public class EmailUtils {

    // Metodo che prende una lista di email e restituisce una stringa separata da virgola
    public static String convertListToString(List<String> emails) {
        return String.join(",", emails);
    }
}