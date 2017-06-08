package fr.humanbooster.liaison.service.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import fr.humanbooster.liaison.business.Personne;
import fr.humanbooster.liaison.service.PersonneService;

public class PersonneServiceImplTest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		PersonneService ps = new PersonneServiceImpl();	
		Calendar cal = Calendar.getInstance();
		
		// Add a person
		cal.set(1971, 27, 4);	
		//ps.ajouterPersonne("Nguyen", "Minh Tuan", "mt@gmail.com", new Date(), cal.getTime());
		
		// Get all persons
		System.out.println("\nToutes les personnes du réseau : ");
		List<Personne> personnes = ps.getAllPersons();
		for(Personne personne:personnes) {
			System.out.println(personne);			
		}
		

	}

}
