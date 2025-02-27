package testing;
import modello.archiviazioneCampione.*;
import view.archiviazioneCampione.CampioneView;

import java.sql.Connection;
import java.time.LocalDate;

import controller.CampioneController;
import jdbc.dao.campione.*;
import jdbc.ConnessioneDB;
import jdbc.FacadeSingletonDB;

public class SystemCampioneTest {

	public static void main(String[] args) {
	
		 ISystemCampione sys = FacadeSingletonDB.getInstance().getSystemCampione();
		 
		 CampioneController campioneController = new CampioneController(sys);
		
		
	}

}
