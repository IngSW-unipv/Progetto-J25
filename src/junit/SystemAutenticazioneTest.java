package junit;

import modello.Utente;
import modello.autenticazione.SystemAutenticazione;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SystemAutenticazioneTest {

    private SystemAutenticazione systemAutenticazione;
    private Utente testUser;

    @Before
    public void setUp() {
        // Inizializza la classe SystemAutenticazione e crea un utente di test
        systemAutenticazione = new SystemAutenticazione();
        testUser = new Utente(1, "alessia.riccapa@gmail.com", "Alessia", "Ricca", "Verbania", LocalDate.of(2003, 9, 22), "RCCLSS03P62L746R", "Gravellona Toce", "lexy", "password123", "pa");

        // Aggiungi l'utente di test alla lista utenti
        ArrayList<Utente> utenti = new ArrayList<>();
        utenti.add(testUser);
        systemAutenticazione.setUtenti(utenti);
    }

    @Test
    public void testControlloEmail_EmailEsistente() {
        // Verifica che la funzione controlloEmail restituisca true per un'email esistente
        assertTrue(systemAutenticazione.controlloEmail("alessia.riccapa@gmail.com"));
    }

    @Test
    public void testControlloEmail_EmailNonEsistente() {
        // Verifica che la funzione controlloEmail restituisca false per un'email non esistente
        assertFalse(systemAutenticazione.controlloEmail("non.esistente@gmail.com"));
    }

    @Test
    public void testControlloLogin_CredenzialiCorrette() {
        // Verifica che il login sia corretto con email e password corrette
        Utente utenteLoggato = systemAutenticazione.controlloLogin("alessia.riccapa@gmail.com", "password123");
        assertNotNull(utenteLoggato);
        assertEquals(testUser, utenteLoggato);
    }

    @Test
    public void testControlloLogin_CredenzialiErrate() {
        // Verifica che il login fallisca con password errata
        Utente utenteLoggato = systemAutenticazione.controlloLogin("alessia.riccapa@gmail.com", "wrongpassword");
        assertNull(utenteLoggato);
    }

    @Test
    public void testModificaPassword_PasswordCorretta() throws Exception {
        // Testa la modifica della password con la password corretta
        boolean risultato = systemAutenticazione.modificaPassword(testUser, "password123", "newpassword123");
        assertTrue(risultato);
        assertEquals("newpassword123", testUser.getPassword());
    }

    @Test
    public void testModificaPassword_PasswordErrata() throws Exception {
        // Testa che la modifica della password fallisca se la password vecchia è errata
        boolean risultato = systemAutenticazione.modificaPassword(testUser, "wrongpassword", "newpassword123");
        assertFalse(risultato);
        assertEquals("password123", testUser.getPassword()); // La password non dovrebbe cambiare
    }
}

