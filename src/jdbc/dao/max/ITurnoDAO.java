
package jdbc.dao.max;
import java.util.ArrayList;
import java.util.List;
import modello.prenotazioneInsaccatore.*;

public interface ITurnoDAO extends GenericoDAO<Turno,Integer>{
	 public ArrayList<Turno> recuperaTurniGiorno(Giorno giorno);
}

