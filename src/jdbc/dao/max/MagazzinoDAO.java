package jdbc.dao.max;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jdbc.ConnessioneDB;
import modello.gestioneInventario.Inventario;
import java.sql.ResultSet;



public class MagazzinoDAO implements IMagazzinoDAO {

    private static final String DB = "osmotech";
    private Connection connessione;

    private static final String SACCHE = "UPDATE INVENTARIO SET sacche = ? WHERE id = 1";
    private static final String BUSTE = "UPDATE INVENTARIO SET buste = ? WHERE id = 1";
    private static final String TAPPI = "UPDATE INVENTARIO SET tappi = ? WHERE id = 1";
    private static final String TUBI = "UPDATE INVENTARIO SET tubi = ? WHERE id = 1";
    private static final String ROTOLI_TUBI = "UPDATE INVENTARIO SET rotoliTubi = ? WHERE id = 1";
    private static final String STECCHE_TAPPI = "UPDATE INVENTARIO SET steccheTappi = ? WHERE id = 1";
    private static final String ROTOLI_NALOPHAN = "UPDATE INVENTARIO SET rotoliNalophan = ? WHERE id = 1";

    
    @Override
	public Inventario trovaInventario() {
	    connessione = ConnessioneDB.startConnection(connessione, DB);
	    String sql = "SELECT sacche, buste, tappi, tubi, rotoliTubi, steccheTappi, rotoliNalophan FROM INVENTARIO";
	    Inventario inventario = null;

	    try (PreparedStatement stmt = connessione.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        if (rs.next()) {
	            inventario = new Inventario(
	                rs.getInt("rotoliTubi"),
	                rs.getInt("steccheTappi"),
	                rs.getInt("rotoliNalophan"),
	                rs.getInt("sacche"),
	                rs.getInt("buste"),
	                rs.getInt("tappi"),
	                rs.getInt("tubi")
	            );
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Errore nel recuperare l'inventario.", e);
	    } finally {
	        ConnessioneDB.closeConnection(connessione);
	    }

	    return inventario;
	}    
    
	
    private void aggiornaAttributo(String sql, int valore) {
        connessione = ConnessioneDB.startConnection(connessione, DB);
        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setInt(1, valore);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'aggiornare l'attributo.", e);
        } finally {
            ConnessioneDB.closeConnection(connessione);
        }
    }
    
    @Override
    public void aggiornaSacche(int sacche) {
        aggiornaAttributo(SACCHE, sacche);
    }
    
    @Override
    public void aggiornaBuste(int buste) {
        aggiornaAttributo(BUSTE, buste);
    }

    @Override
    public void aggiornaTappi(int tappi) {
        aggiornaAttributo(TAPPI, tappi);
    }

    @Override
    public void aggiornaTubi(int tubi) {
        aggiornaAttributo(TUBI, tubi);
    }

    @Override
    public void aggiornaRotoliTubi(int rotoliTubi) {
        aggiornaAttributo(ROTOLI_TUBI, rotoliTubi);
    }

    @Override
    public void aggiornaSteccheTappi(int steccheTappi) {
        aggiornaAttributo(STECCHE_TAPPI, steccheTappi);
    }

    @Override
    public void aggiornaRotoliNalophan(int rotoliNalophan) {
        aggiornaAttributo(ROTOLI_NALOPHAN, rotoliNalophan);
    }
}
