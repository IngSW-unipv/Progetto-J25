package jdbc.dao.max;
import modello.prenotazioneInsaccatore.*;

public interface IGiornoDAO extends GenericoDAO<Giorno, GiorniSettimana>{
		public void svuotaSettimana();
}
