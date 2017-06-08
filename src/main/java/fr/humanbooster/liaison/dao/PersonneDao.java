package fr.humanbooster.liaison.dao;

import java.sql.SQLException;
import java.util.List;
import fr.humanbooster.liaison.business.Personne;

public interface PersonneDao {
	
	// On fournit les méthodes CRUD
	boolean createPersonne(Personne personne) throws SQLException;
	
	boolean deletePersonne(Personne personne) throws SQLException;
	
	Personne updatePersonne(Personne personne);
	
	List<Personne> findAll() throws SQLException;
	
	Personne findByEmailAndPassword(String email, String motDePasse);
	
	Personne findById(long idPersonne);
	
	boolean changePassword(String motDePasse, Long idPersonne);
	
	
}
