package jdbc;

import modello.Utente;
import modello.email.NotificaMessage;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;


public class ConnessioneDB {

    private static final String PROPERTYDBDRIVER = "DBDRIVER";
    private static final String PROPERTYDBURL = "DBURL";
    private static final String PROPERTYNAME = "db_usn";
    private static final String PROPERTYPSW = "db_psw";
    private static String username;
    private static String password;
    private static String dbDriver;
    private static String dbURL;
    //private static ConnessioneDB conn;

    private static void init() {
        Properties p = new Properties(System.getProperties());
        try {
            p.load(new FileInputStream("properties/properties"));
            username=p.getProperty(PROPERTYNAME);
            password=p.getProperty(PROPERTYPSW);
            dbDriver =p.getProperty(PROPERTYDBDRIVER);
            dbURL =p.getProperty(PROPERTYDBURL);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection startConnection(Connection conn, String schema)
    {
        init();
        System.out.println(dbURL);


        if ( isOpen(conn) )
            closeConnection(conn);

        try
        {

            dbURL=String.format(dbURL,schema);
            System.out.println(dbURL);
            Class.forName(dbDriver);

            conn = DriverManager.getConnection(dbURL, username, password);// Apertura connessione
            conn.setAutoCommit(true);

        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return conn;
    }

    public static boolean isOpen(Connection conn)
    {
        if (conn == null)
            return false;
        else
            return true;
    }

    public static Connection closeConnection(Connection conn)
    {
        if ( !isOpen(conn) )
            return null;
        try
        {

            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return conn;
    }
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

	