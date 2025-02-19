package testing;
public class Main {
    	public static void main(String[] args) {
	        Connection conn = null;
	        try {
	            // Prova a connetterti al database
	            conn = ConnessioneDB.startConnection(conn, "osmotech");

	            if (conn != null) {
	                System.out.println("✅ Connessione riuscita!");
	            } else {
	                System.out.println("❌ Connessione fallita!");
	            }
	        } catch (Exception e) {
	            System.out.println("❌ Errore durante la connessione:");
	            e.printStackTrace();
	        } finally {
	            // Chiudi la connessione dopo il test
	            conn = ConnessioneDB.closeConnection(conn);
	            if (conn == null) {
	                System.out.println("✅ Connessione chiusa correttamente!");
	            }
	        }
	        File file = new File("properties/properties");
	        System.out.println("📂 Percorso assoluto: " + file.getAbsolutePath());
	        System.out.println("📂 Esiste? " + file.exists());
	    }
}
