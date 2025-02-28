package modello.ConfermaPresenzaPanel;
/*
 * Questa classe rappresenta il livello di business logic per gestire le operazioni
 * relative ai panel, ovvero la conferma della presenza di un panelista.
 * Questa classe è stata creata per implementare il caso d'uso 
 * UC11: Conferma presenza panelista
 * UC12: Visualizzazione Ore Lavorate
 */

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Map;

import jdbc.bean.PanelDAO;
import jdbc.bean.UserDAO;
import jdbc.dao.documento.OreLavoroDAO;



public class SystemPresenzaPanel {

    public SystemPresenzaPanel() {}

    public boolean presenzaEChiusuraPanel(int panelId, Map<Integer, Boolean> presenze, LocalTime orarioFine) {

        PanelDAO panelDAO = new PanelDAO();
        OreLavoroDAO oreLavoroDAO = new OreLavoroDAO();

        // Chiudo il panel
        boolean panelChiuso = panelDAO.chiudiPanel(panelId, orarioFine);
        if (!panelChiuso) {
            return false; // Se non riesco a chiudere il panel, esco
        }

        // Recupero l'orario di inizio del panel per calcolare le ore lavorate
        LocalTime orarioInizio = panelDAO.getOrarioInizio(panelId);
        if (orarioInizio == null) {
            return false; // Se non riesco a recuperare l'orario di inizio, esco
        }
        double oreLavorate = ChronoUnit.HOURS.between(orarioInizio, orarioFine);

        // Aggiornamento delle ore lavorate dai panelisti presenti
        String meseCorrente = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM", Locale.ITALIAN));
        for (Map.Entry<Integer, Boolean> entry : presenze.entrySet()) {

            int idPanelista = entry.getKey();
            boolean presente = entry.getValue();

            if (presente) {

                // Controllo che ci sia un record nelle OreLavorate per questo panelista
                oreLavoroDAO.aggiungeOreLavoroSeNonEsiste(idPanelista, meseCorrente, 0);

                // Aggiornamento delle ore lavorate sommando le nuove ore dopo la chiusura del panel
                double orePrecedenti = oreLavoroDAO.selectOreLavoro(idPanelista, meseCorrente);
                boolean aggiornato = oreLavoroDAO.aggiornaOreLavoro(idPanelista, meseCorrente, orePrecedenti + oreLavorate);

                if (!aggiornato) {
                    return false; // Se non riesco ad aggiornare le ore lavorate, esco
                }
            }
        }

        return true;
    }

    public int getPanelistaIdByEmail (String email) {
        UserDAO panelDAO = new UserDAO();
        return panelDAO.getPanelistaIdByEmail(email);
    }


    
}
