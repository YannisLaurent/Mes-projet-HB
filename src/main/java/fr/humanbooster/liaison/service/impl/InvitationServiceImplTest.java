package fr.humanbooster.liaison.service.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import fr.humanbooster.liaison.business.Invitation;
import fr.humanbooster.liaison.dao.InvitationDao;
import fr.humanbooster.liaison.dao.PersonneDao;
import fr.humanbooster.liaison.dao.impl.InvitationDaoImpl;
import fr.humanbooster.liaison.dao.impl.PersonneDaoImpl;
import fr.humanbooster.liaison.service.InvitationService;

public class InvitationServiceImplTest {

	// private static Connection connection;
	
	public static void main(String[] args) throws SQLException {
		// GET DATABASE CONNECTION
//		try {		
//			connection = ConnexionBdd.getConnection();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
		
		InvitationService is = null;
		try {
			is = new InvitationServiceImpl();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Calendar cal = Calendar.getInstance();
		
		// Add an invitation
		PersonneDao pdao;
		try {
			pdao = new PersonneDaoImpl();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Personne personneInvitante = pdao.getPersonneById(2L);
//		Personne personneInvitee = pdao.getPersonneById(3L);
		
//		System.out.println(personneInvitante);
//		System.out.println(personneInvitee);
		
		cal.set(2017,3,15);
		//is.ajouterInvitation(Invitation.getMessageDefault(), new Date(), cal.getTime(), false, personneInvitante, personneInvitee);
		
		// Get all invitations
		System.out.println("\nToutes les invitations: ");
		List<Invitation> invitations = is.getAllInvitations();
		for(Invitation invitation:invitations) {
			System.out.println(invitation);			
		}
		
		// Update invitation
		InvitationDao idao = null;
		try {
			idao = new InvitationDaoImpl();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Invitation invitation = idao.findInvitationById(1L);
		System.out.println("\nInvitation trouvée : " + invitation);
		invitation.setEstAcceptee(false);
		is.repondreInvitation(invitation);
		

	}

}
