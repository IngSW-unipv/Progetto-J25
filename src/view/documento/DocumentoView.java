package view.documento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.DocumentoController;

public class DocumentoView {

	private JFrame frame;
	private JButton btnDocumento;
	private DocumentoController documentoController;
	
	
	public DocumentoView(DocumentoController documentoController) {
		
		this.documentoController = documentoController;
		
		frame = new JFrame("Generazione documento di riepilogo");
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnDocumento = new JButton("Genera documento");
		
		btnDocumento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			
					int id = Integer.parseInt(JOptionPane.showInputDialog("Inserisci il tuo id"));
					String mese = JOptionPane.showInputDialog("Insersci il mese");
                  
                
                    documentoController.generaDocumento(id, mese);
                    JOptionPane.showMessageDialog(null, "Documento generato");
                       
        
				
				
			}
			
			
		});
		
		frame.getContentPane().add(btnDocumento);
		frame.setVisible(true);
	}
	
	
}
