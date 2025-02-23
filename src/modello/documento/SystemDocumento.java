package modello.documento;

import jdbc.bean.IUserDAO;
import jdbc.bean.UserDAO;
import jdbc.dao.documento.IOreLavoroDAO;
import jdbc.dao.documento.OreLavoroDAO;
import modello.Panelista;

public class SystemDocumento {
	
	IUserDAO userDAO;
	IOreLavoroDAO oreLavoroDAO;
	
	public SystemDocumento(IUserDAO userDAO, IOreLavoroDAO oreLavoroDAO) {
		
		this.userDAO = userDAO;
		this.oreLavoroDAO = oreLavoroDAO;
		
		
	}
	
	public Panelista restituisciPanelista(int id) {
		
		return userDAO.selectPanelista(id);
	}
	
	public double oreLavoroPanelista(int id, String mese) {
		
		return oreLavoroDAO.selectOreLavoro(id, mese);
	}
	
	
	
}
