package fr.humanbooster.liaison.business;

import java.util.Date;

public class Invitation {
	private Long id;
	private Personne personneInvitant;
	private Personne personneInvite;
	private String message;
	private static final String MESSAGE_DEFAULT = "Merci de rejoindre mon réseau personnel";
	private Date dateEnvoi;
	private Date dateReponse;
	private boolean estAcceptee;
	
	public Invitation(String message, Date dateEnvoi, Date dateReponse, boolean estAcceptee) {
		this.message = message;
		this.dateEnvoi = dateEnvoi;
		this.dateReponse = dateReponse;
		this.estAcceptee = estAcceptee;
	}
	
	public Invitation(
		String message, 
		Date dateEnvoi,
		Date dateReponse, 
		boolean estAcceptee, 
		Personne personneInvitant, 
		Personne personneInvite
	) {
		this.message = message;
		this.dateEnvoi = dateEnvoi;
		this.dateReponse = dateReponse;
		this.estAcceptee = estAcceptee;
		this.personneInvitant = personneInvitant;
		this.personneInvite = personneInvite;
	}

	public Invitation() {
		super();
		this.message = MESSAGE_DEFAULT;
		this.dateEnvoi = new Date();
		this.dateReponse = new Date();
		
		
	}

	// GETTERS & SETTERS
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Personne getPersonneInvitant() {
		return personneInvitant;
	}
	public Personne getPersonneInvite() {
		return personneInvite;
	}
	public String getMessage() {
		return message;
	}
	public void setPersonneInvitant(Personne personneInvitant) {
		this.personneInvitant = personneInvitant;
	}
	public void setPersonneInvite(Personne personneInvite) {
		this.personneInvite = personneInvite;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDateEnvoi() {
		return dateEnvoi;
	}
	public void setDateEnvoi(Date envoi) {
		this.dateEnvoi = envoi;
	}
	public Date getDateReponse() {
		return dateReponse;
	}
	public void setDateReponse(Date reponse) {
		this.dateReponse = reponse;
	}
	public boolean isEstAcceptee() {
		return estAcceptee;
	}
	public void setEstAcceptee(boolean estAccepte) {
		this.estAcceptee = estAccepte;
	}
	public static String getMessageDefault() {
		return MESSAGE_DEFAULT;
	}

	// ToString
	public String toString() {
		return "Invitation [id=" + id + ", personneInvitant=" + personneInvitant + ", personneInvite=" + personneInvite
				+ ", message=" + message + ", dateEnvoi=" + dateEnvoi + ", dateReponse=" + dateReponse
				+ ", estAcceptee=" + estAcceptee + "]";
	}
	
	
	
	
	
}
