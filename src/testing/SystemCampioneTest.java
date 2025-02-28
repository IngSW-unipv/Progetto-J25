package testing;
import modello.archiviazioneCampione.*;

import view.archiviazioneCampione.CampioneView;



import controller.CampioneController;

import jdbc.FacadeSingletonDB;


public class SystemCampioneTest {

	public static void main(String[] args) {

	
		 SystemCampione sys = FacadeSingletonDB.getInstance().getSystemCampione();
		 
		 CampioneController campioneController = new CampioneController(sys);
		 CampioneView campioneView = new CampioneView(campioneController);
		 
	        
	}

}
