package jdbc.dao;

import java.util.ArrayList;

public interface GenericoDAO<T>{
	// operazioni CRUD (Create, Read, Update, Delete):
	void add(T t);
	T get(int id);
	ArrayList<T> getAll(); //uso List per flessibilità
	void update(T t);
	void remove(int id);

}
