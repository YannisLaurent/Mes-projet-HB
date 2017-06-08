package fr.humanbooster.liaison.business;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class Personne {
	
	private long id;
	
	private Civilite civilite;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	@Size(min=5, max=12, message="Le mot de passe doit être au minimun de 5 caractère et au maximum de 12")
	private String motDePasse;
	
	private Date dateInscription;
	
	@Past(message="Merci de rentrer une date de naissance valide")
	private Date dateNaissance;
	
	@NotNull(message="Merci de préciser la ville où vous résidez")
	private Ville ville;
	
	
	private List<Personne> reseau;
	 	
	public Personne(String nom, String prenom, String email, Date dateInscription, Date dateNaissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dateInscription = dateInscription;
		this.dateNaissance = dateNaissance;
	}
	
	public Personne(
			Civilite civilite, 
			String nom, 
			String prenom, 
			String email, 
			String motDePasse, 
			Date dateInscription, 
			Date dateNaissance, 
			Ville ville) {
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.dateInscription = dateInscription;
		this.dateNaissance = dateNaissance;
		this.ville = ville;
	}
		
	public Personne() {
		// TODO Auto-generated constructor stub
	}

	// GETTERS
	public long getId() {
		return id;
	}
	public Civilite getCivilite() {
		return civilite;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public String getEmail() {
		return email;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public Date getDateInscription() {
		return dateInscription;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public Ville getVille() {
		return ville;
	}

	// SETTERS
	public void setId(Long id) {
		this.id = id;
	}	
	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
	// GET & SET Reseau de personnes
	public List<Personne> getReseau() {
		return reseau;
	}
	public void setReseau(List<Personne> reseau) {
		this.reseau = reseau;
	}

	// ToString
	public String toString() {
		return "Personne [civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", motDePasse=" + motDePasse + ", dateInscription=" + dateInscription + ", dateNaissance="
				+ dateNaissance + ", ville=" + ville + "]";
	}
	
}
