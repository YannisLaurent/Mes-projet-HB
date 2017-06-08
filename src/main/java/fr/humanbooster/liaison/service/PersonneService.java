package fr.humanbooster.liaison.service;

import java.util.Date;
import java.util.List;

import fr.humanbooster.liaison.business.Civilite;
import fr.humanbooster.liaison.business.Personne;
import fr.humanbooster.liaison.business.Ville;

public interface PersonneService {
	
	public Personne ajouterPersonne(Civilite civilite, String nom, String prenom, String email, String motDePasse, Date dateInscription, Date dateNaissance, Ville ville);
	
	public List<Personne> getAllPersons();
	
	public Personne verifierPersonne(String email, String motDePasse);

	public Personne trouverPersonneAvecId(long idPersonne);
	
	public boolean changerMotDePasse(String MotDePasse, Long idInvitante);
}
