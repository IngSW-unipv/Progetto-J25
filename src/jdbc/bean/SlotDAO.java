package jdbc.bean;

import jdbc.ConnessioneDB;
import modello.creazionePanel.Slot;
import modello.creazionePanel.Sondaggio;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/*CREATE TABLE SLOT (
ID_SLOT INT AUTO_INCREMENT PRIMARY KEY,
ID_SONDAGGIO INT,
ORARIO DATETIME NOT NULL,
FOREIGN KEY (ID_SONDAGGIO) REFERENCES SONDAGGIO(ID_SONDAGGIO)
        );*/

public class SlotDAO implements ISlotDAO {

    public ArrayList<Slot> getSlots(Sondaggio sondaggio) {
        ArrayList<Slot> slots = new ArrayList<>();  // Lista dove verranno aggiunti gli slot
        Connection conn = ConnessioneDB.startConnection(null, "osmotech"); // Crea la connessione al database

        // Query per selezionare tutti gli slot per un sondaggio specifico
        String query = "SELECT ID_SLOT, ORARIO FROM SLOT WHERE ID_SONDAGGIO = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Imposta il parametro della query con l'ID del sondaggio
            pstmt.setInt(1, sondaggio.getId());

            // Esegui la query e ottieni il risultato
            try (ResultSet rs = pstmt.executeQuery()) {
                // Cicla attraverso i risultati
                while (rs.next()) {
                    // Estrai i dati dalla riga del ResultSet
                    int idSlot = rs.getInt("ID_SLOT");
                    LocalDateTime orarioCompleto = rs.getTimestamp("ORARIO").toLocalDateTime(); // Estrai il DATETIME

                    // Estrai la data e l'orario separatamente
                    LocalDate data = orarioCompleto.toLocalDate(); // Solo la data
                    LocalTime orarioInizio = orarioCompleto.toLocalTime(); // Solo l'orario

                    // Crea un oggetto Slot con i dati estratti
                    Slot slot = new Slot(data, orarioInizio);
                    slot.setIdSlot(idSlot); // Imposta l'ID dello slot

                    // Aggiungi lo slot alla lista
                    slots.add(slot);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Stampa l'errore in caso di problemi con l'esecuzione
        } finally {
            ConnessioneDB.closeConnection(conn); // Chiude la connessione
        }

        // Restituisce la lista di slot
        return slots;
    }


    public boolean insertSlots(Map<LocalTime, Slot> slots) {
        Connection conn = ConnessioneDB.startConnection(null, "osmotech");
        String query = "INSERT INTO SLOT (ID_SONDAGGIO, ORARIO) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (Map.Entry<LocalTime, Slot> entry : slots.entrySet()) {
                Slot slot = entry.getValue();

                pstmt.setInt(1, slot.getIdSondaggio()); // Inserisce ID_SONDAGGIO
                pstmt.setTimestamp(2, Timestamp.valueOf(
                        LocalDateTime.of(slot.getData(), slot.getOrarioInizio()) // Converte data + orario in DATETIME
                ));

                pstmt.addBatch(); // Aggiunge al batch per eseguire tutto insieme
            }

            int[] rowsAffected = pstmt.executeBatch(); // Esegue tutti gli INSERT

            // Recupera gli ID generati per gli slot
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                Iterator<Slot> slotIterator = slots.values().iterator();
                while (generatedKeys.next() && slotIterator.hasNext()) {
                    int generatedId = generatedKeys.getInt(1); // Ottiene l'ID generato
                    Slot slot = slotIterator.next();
                    slot.setIdSlot(generatedId); // Assegna l'ID allo Slot
                }
            }

            return Arrays.stream(rowsAffected).anyMatch(rows -> rows > 0); // True se almeno uno slot è stato inserito
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnessioneDB.closeConnection(conn);
        }
    }

}
