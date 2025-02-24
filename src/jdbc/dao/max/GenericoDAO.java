package jdbc.dao.max;

import java.util.ArrayList;

public interface GenericoDAO<T,ID>{
	// operazioni BASE:
	void aggiungi(T t);
	T trova(ID id);
	ArrayList<T> trovaTutti(); //uso List per flessibilità
	void aggiorna(T t);
	void rimuovi(T t);
	
}


