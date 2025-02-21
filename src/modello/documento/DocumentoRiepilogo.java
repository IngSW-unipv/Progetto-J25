package modello.documento;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import modello.Panelista;

public class DocumentoRiepilogo {
	
	private Panelista panelista;
	private LocalDate localDate;
	private String mese;
	private double stipendioLordo;
	private double stipendioNetto;
	private double trattenute;
	
	public DocumentoRiepilogo(Panelista panelista, LocalDate localDate, String mese, double stipendioLordo,
								double stipendioNetto, double trattenute) {
		
		this.panelista = panelista;
		this.localDate = localDate;
		this.mese = mese;
		this.stipendioLordo = stipendioLordo;
		this.stipendioNetto = stipendioNetto;
		this.trattenute = trattenute;
	}
	
	public void riepilogoUtente() {
		
		String nomeFile = panelista.getNome() + "_riepilogo.txt";
	
		try (FileWriter fileWriter = new FileWriter(nomeFile);
					PrintWriter writer = new PrintWriter(fileWriter)) {

			
			 	writer.println("COMMITTENTE\n\nEcol Studio S.p.A\nVia Lanzone 31\n20123 Milano MI");
			 	
			    writer.println("\n\nRicevuta di compenso per prestazione occasionale");
			    
			    writer.println("\n\nData: "+this.localDate);
			    
			    writer.println("\nIl/La sottoscritto/a " + panelista.getCognome()+ " "+ panelista.getNome());
			    
			    writer.println("Nato/a " + panelista.getLuogoNascita());
			    
			    writer.println("Codice fiscale " + panelista.getCodiceFiscale());
			    
			    writer.println("Residente in " + panelista.getResidenza());
			    
			    writer.println("\ndichiara di ricevere per prestazione occasionale relativa al mese di " + this.mese);
			    
			    writer.println("\nCompenso lordo                                " + this.stipendioLordo);
			    
			    writer.println("Ritenuta d'acconto 20%                        " + this.trattenute);

			    writer.println("Compenso netto                                " + this.stipendioNetto);
			    
			   

			    System.out.println("File salvato correttamente: " + nomeFile);

			} catch (IOException e) {
			    System.out.println("Errore nella scrittura del file: " + e.getMessage());
			    e.printStackTrace();
			}
		
	}
	

	
}



