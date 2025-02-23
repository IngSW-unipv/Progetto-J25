package controller;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import modello.documento.Azienda;
import view.documento.DocumentoView;

public class DocumentoController {
	
	private Azienda azienda;
	private DocumentoView documentoView;
	
	public DocumentoController(Azienda azienda) {
		
		this.azienda = azienda;
		this.documentoView = new DocumentoView(this);
	}
	
	public void generaDocumento(int id, String mese) {
		
		azienda.generaDocumento(id, LocalDate.now(), mese);
		JOptionPane.showMessageDialog(null, "Documento generato con successo");
	}
	
	
}
