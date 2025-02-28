package testing;
import modello.archiviazioneCampione.*;
import view.archiviazioneCampione.CampioneView;

import java.sql.Connection;
import java.time.LocalDate;

import controller.CampioneController;
<<<<<<< HEAD
import jdbc.dao.campione.*;
import jdbc.ConnessioneDB;
=======
>>>>>>> branch 'main' of https://github.com/IngSW-unipv/Progetto-J25
import jdbc.FacadeSingletonDB;

public class SystemCampioneTest {

	public static void main(String[] args) {

	
		 SystemCampione sys = FacadeSingletonDB.getInstance().getSystemCampione();
		 
		 CampioneController campioneController = new CampioneController(sys);
		 CampioneView campioneView = new CampioneView(campioneController);
		 
	        
	}

}
