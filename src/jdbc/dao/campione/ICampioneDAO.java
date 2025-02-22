package jdbc.dao.campione;
import java.time.LocalDate;
import java.util.ArrayList;
import modello.archiviazioneCampione.*;


public interface ICampioneDAO {
	
	boolean insertCampione(int id, String stato, LocalDate ld);
	Campione trovaCampionePerId(int id);
	ArrayList <Campione> selectAllCampioni();
	boolean updateCampione(int id, String stato);
	boolean eliminaCampione(int id);
	ArrayList <Integer> trovaCampioneNonAnalizzato();
}