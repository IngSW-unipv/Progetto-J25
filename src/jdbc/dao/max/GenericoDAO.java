package jdbc.dao.max;

import java.util.ArrayList;

public interface GenericoDAO<T>{
	// operazioni BASE:
	void aggiungi(T t);
	T trova(int id);
	ArrayList<T> trovaTutti(); //uso List per flessibilità
	void aggiorna(T t);
	void rimuovi(T t);

}
