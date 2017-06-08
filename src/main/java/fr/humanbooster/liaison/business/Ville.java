package fr.humanbooster.liaison.business;

public class Ville {
	private Long id;
	private String nom;
	
	public Ville(String nom) {
		this.nom = nom;
	}
	
	// GETTERS & SETTERS
	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	// ToString
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + "]";
	}

	
	
	
}
