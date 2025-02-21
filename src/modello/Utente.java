package modello;

import modello.email.IObserver;
import static modello.email.EmailSender.sendEmail;

import java.time.LocalDate;

public class Utente implements IObserver {

	private int id;
	private String email;
	private String nome;
	private String cognome;
	private String luogoNascita;
	private LocalDate dataNascita;
	private String codiceFiscale;
	private String residenza;


	public Utente(int id, String email, String nome, String cognome, String luogoNascita, LocalDate dataNascita,
			String codiceFiscale, String residenza) {

		this.id = id;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.luogoNascita = luogoNascita;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.residenza = residenza;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}

	@Override
	public void aggiorna(String subject, String body) {
		sendEmail(email,subject, body);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}





}
