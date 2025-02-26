package modello.gestioneInventario;

public class Inventario {
	private int sacche;
	private int buste;
	private int tappi;
	private int tubi;
	private int rotoliTubi;
	private int steccheTappi;
	private int rotoliNalophan;

	
	
	public Inventario() {
		
	}


	public Inventario(int rotoloTubi, int steccheTappi, int rotoloNalophan, int sacche, int buste, int tappi,
			int tubi) {
		
		this.rotoliTubi = rotoloTubi;
		this.steccheTappi = steccheTappi;
		this.rotoliNalophan = rotoloNalophan;
		this.sacche = sacche;
		this.buste = buste;
		this.tappi = tappi;
		this.tubi = tubi;
	}
	
	
	//METODI UTILI:
	
	public void aggiornaSacche(int sacche,boolean usoNalophan) {
		this.sacche += sacche;
		tappi-=sacche;
		tubi-=sacche;
		buste-=sacche;
		this.controllo();
	}
	
	public void realizzazioneTappiTubi(int tappi, int tubi, int rotoli, int stecche) {
		this.tappi += tappi;
		this.tubi += tubi;
		this.rotoliTubi -= rotoli;
		this.steccheTappi -= stecche;
		this.controllo();
	}
	
	public void ricicloTappiTubi(int tappi, int tubi) {
		this.tappi += tappi;
		this.tubi += tubi;
		this.controllo();
	}
	
	
	public void controllo() {
	    if (sacche < 0) sacche = 0;
	    if (buste < 0) buste = 0;
	    if (tappi < 0) tappi = 0;
	    if (tubi < 0) tubi = 0;
	    if (rotoliTubi < 0) rotoliTubi = 0;
	    if (steccheTappi < 0) steccheTappi = 0;
	    if (rotoliNalophan < 0) rotoliNalophan = 0;
	}
	
	
	
	
	
	

}
