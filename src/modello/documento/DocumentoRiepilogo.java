package modello.documento;
import modello.Utente;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import modello.Panelista;

public class DocumentoRiepilogo {
	
	private Panelista panelista;
	private LocalDate localDate;
	private String mese;
	
	public DocumentoRiepilogo(Panelista panelista, LocalDate localDate, String mese) {
		
		this.panelista = panelista;
		this.localDate = localDate;
		this.mese = mese;
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
			    
			    writer.println("\nCompenso lordo                                " + panelista.getOreLavoro() * 10);
			    
			    writer.println("Ritenuta d'acconto 20%                        " + (panelista.getOreLavoro() *10)*0.2);

			    writer.println("Compenso netto                                " + stipendio());
			    
			   

			    System.out.println("File salvato correttamente: " + nomeFile);

			} catch (IOException e) {
			    System.out.println("Errore nella scrittura del file: " + e.getMessage());
			    e.printStackTrace();
			}
		
	}
	
	public double stipendio() {
		
		double stipendioLordo = panelista.getOreLavoro() *10;
		double trattenute = panelista.getOreLavoro() *10 * 0.2;
		
		return stipendioLordo - trattenute;
	}
	
	
	public static void main(String[] args) {
		
		LocalDate localDate = LocalDate.of(2003, 02, 16); 
		LocalDate dataEmissione = LocalDate.of(2025, 02, 17); 
		Panelista p = new Panelista("tommaso.ghisolfi003@gmail.com", "Tommaso", "Ghisolfi", "Broni", localDate, "GHSTMS3B16B201L", "Via Paolo Borsellino 28 Bressana Bottarone", 16);
		DocumentoRiepilogo dr = new DocumentoRiepilogo(p, dataEmissione, "FEBBRAIO");
		
		dr.riepilogoUtente();
	
		
	}
	
	
}



