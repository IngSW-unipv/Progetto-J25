package jdbc.dao;

import jdbc.ConnessioneDB;
import modello.creazionePanel.Sondaggio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class SondaggioDAO implements ISondaggioDAO {

    public List<Sondaggio> selectAllSondaggi(){
        List<Sondaggio> sondaggi = new ArrayList<>();
        Connection conn = ConnessioneDB.startConnection(null, "osmotech");

        String query = "SELECT ID_SONDAGGIO, SLOT_DISPONIBILI, DATA, ORARIO_INIZIO " +
                "FROM SONDAGGIO WHERE STATO = TRUE AND ORARIO_FINE IS NULL";

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int idSondaggio = rs.getInt("ID_SONDAGGIO");
                int slotDisponibili = rs.getInt("SLOT_DISPONIBILI");
                LocalDate data = rs.getDate("DATA").toLocalDate();
                LocalTime orarioInizio = rs.getTime("ORARIO_INIZIO").toLocalTime();

                Sondaggio sondaggio = new Sondaggio();
                sondaggio.setId(idSondaggio);
                sondaggio.setSlotDisponili(slotDisponibili);
                sondaggio.setData(data);
                sondaggio.setOraInizio(orarioInizio);
                sondaggi.add(sondaggio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnessioneDB.closeConnection(conn);
        }
        return sondaggi;
    }

    public Sondaggio selectSondaggioById(int id) {
        Connection conn = ConnessioneDB.startConnection(null, "osmotech");
        Sondaggio sondaggio = new Sondaggio();

        String query = "SELECT ID_SONDAGGIO, SLOT_DISPONIBILI, DATA, ORARIO_INIZIO " +
                "FROM SONDAGGIO WHERE ID_SONDAGGIO = ? AND STATO = TRUE AND ORARIO_FINE IS NULL";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int slotDisponibili = rs.getInt("SLOT_DISPONIBILI");
                LocalDate data = rs.getDate("DATA").toLocalDate();
                LocalTime orarioInizio = rs.getTime("ORARIO_INIZIO").toLocalTime();

                sondaggio.setId(id);
                sondaggio.setData(data);
                sondaggio.setOraInizio(orarioInizio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnessioneDB.closeConnection(conn);
        }

        return sondaggio;
    }
    public boolean insertSondaggio(Sondaggio sondaggio) {
        Connection conn = ConnessioneDB.startConnection(null, "osmotech");
        String query = "INSERT INTO SONDAGGIO (SLOT_DISPONIBILI, DATA, ORARIO_INIZIO, STATO) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, sondaggio.getSlotDisponili());
            pstmt.setDate(2, java.sql.Date.valueOf(sondaggio.getData()));
            pstmt.setTime(3, java.sql.Time.valueOf(sondaggio.getOraInizio()));
            pstmt.setBoolean(4, sondaggio.getStato()); // Indica se il sondaggio è attivo

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Restituisce true se l'inserimento è avvenuto con successo
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnessioneDB.closeConnection(conn);
        }
    }

    public boolean chiudiSondaggio(Sondaggio sondaggio) {
        Connection conn = ConnessioneDB.startConnection(null, "osmotech");
        String query = "UPDATE SONDAGGIO SET STATO = FALSE, ORARIO_FINE = ? WHERE ID_SONDAGGIO = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setTime(1, java.sql.Time.valueOf(LocalTime.now())); // Imposta l'orario di fine attuale
            pstmt.setInt(2, sondaggio.getId()); // Identifica il sondaggio da chiudere

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Restituisce true se l'update è avvenuto con successo
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnessioneDB.closeConnection(conn);
        }
    }


}
