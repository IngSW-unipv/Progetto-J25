package jdbc.bean;

import jdbc.ConnessioneDB;
import modello.creazionePanel.Sondaggio;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/*CREATE TABLE SONDAGGIO (
        ID_SONDAGGIO INT AUTO_INCREMENT PRIMARY KEY,
        SLOT_DISPONIBILI INT NOT NULL,
        DATA DATE NOT NULL,
        ORARIO_FINE TIME,
        ORARIO_INIZIO TIME,
        STATO BOOLEAN NOT NULL
);*/

public class SondaggioDAO implements ISondaggioDAO {

    public ArrayList<Sondaggio> selectAllSondaggi(){
        ArrayList<Sondaggio> sondaggi = new ArrayList<Sondaggio>();
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
        System.out.println("andato a buon fine");
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
    public Integer insertSondaggio(Sondaggio sondaggio) {
        Connection conn = ConnessioneDB.startConnection(null, "osmotech");
        String query = "INSERT INTO SONDAGGIO (SLOT_DISPONIBILI, DATA, ORARIO_INIZIO, STATO) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, sondaggio.getSlotDisponili());
            pstmt.setDate(2, java.sql.Date.valueOf(sondaggio.getData()));
            pstmt.setTime(3, java.sql.Time.valueOf(sondaggio.getOraInizio()));
            pstmt.setBoolean(4, sondaggio.getStato());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Restituisce l'ID generato
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnessioneDB.closeConnection(conn);
        }
        return null; // Restituisce null se l'inserimento fallisce
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

    public static void main(String[] args) {
        SondaggioDAO dao = new SondaggioDAO();
        ArrayList<Sondaggio> sondaggi = dao.selectAllSondaggi();
        System.out.println(sondaggi.size());
    }


}
