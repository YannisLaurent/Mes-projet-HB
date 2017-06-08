package fr.humanbooster.liaison.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.humanbooster.liaison.business.Civilite;
import fr.humanbooster.liaison.business.Personne;
import fr.humanbooster.liaison.business.Ville;
import fr.humanbooster.liaison.dao.PersonneDao;
import fr.humanbooster.liaison.dao.impl.PersonneDaoImpl;
import fr.humanbooster.liaison.service.PersonneService;

public class PersonneServiceImpl implements PersonneService {
	private static PersonneDao pdao;
	
	// Constructeur + initialisation pdao 
	public PersonneServiceImpl() throws ClassNotFoundException, SQLException {
		pdao = new PersonneDaoImpl();
	}

	// Ajout personne
	public Personne ajouterPersonne(Civilite civilite, String nom, String prenom, String email, String motDePasse, Date dateInscription, Date dateNaissance, Ville ville) {
		Personne personne = new Personne(civilite, nom, prenom, email, motDePasse, dateInscription, dateNaissance, ville);
		try {
			pdao.createPersonne(personne);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return  personne;
	}
	
	// Liste des personnes
	public List<Personne> getAllPersons() {
		List<Personne> personnes = new ArrayList<>();
		
		try {
			personnes = pdao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return personnes;		
	}

	// Personne par email et motDePasse.
	public Personne verifierPersonne(String email, String motDePasse){
		Personne p = pdao.findByEmailAndPassword(email, motDePasse);
		return p;
	}

	@Override
	public Personne trouverPersonneAvecId(long idPersonne) {
		Personne p = pdao.findById(idPersonne);
		return p;
	}

	@Override
	public boolean changerMotDePasse(String motDePasse, Long idPersonne) {
		return pdao.changePassword(motDePasse, idPersonne);
		
	}

}
