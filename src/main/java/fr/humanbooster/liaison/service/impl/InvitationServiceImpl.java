package fr.humanbooster.liaison.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.humanbooster.liaison.business.Invitation;
import fr.humanbooster.liaison.business.Personne;
import fr.humanbooster.liaison.dao.InvitationDao;
import fr.humanbooster.liaison.dao.impl.InvitationDaoImpl;
import fr.humanbooster.liaison.service.InvitationService;
import fr.humanbooster.liaison.utils.Mailer;

public class InvitationServiceImpl implements InvitationService {
	private static InvitationDao idao;
	// Constructeur + initialisation idao 
	public InvitationServiceImpl() throws ClassNotFoundException, SQLException {
		//InvitationServiceImpl.idao = new InvitationDaoImpl(connection);
		idao = new InvitationDaoImpl();
	}
	
	// Add invitation
	public Invitation ajouterInvitation(String message, Date dateEnvoi, Date dateReponse, boolean estAcceptee, Personne personneInvitante, Personne personneInvitee) throws SQLException {
		Invitation invitation = new Invitation(message, dateEnvoi, dateReponse, estAcceptee, personneInvitante, personneInvitee);
		idao.createInvitation(invitation);	
		//On envoi un mail à la personne invitée
		//La classe Mailer sera chargé d'envoyer le mail.
//		Mailer m = new Mailer();
//		m.envoyerMail("ylaurent@humanbooster.com", "vrecasens@humanbooster.com", "Bonjour", "on les detecte peut être pas à l'infrarouge");
		return invitation;
	}

	@Override
	public Invitation recupererInvitation(int idPersonne) {
		return null;
	}

	// Get all invitations
	public List<Invitation> getAllInvitations() {
		List<Invitation> invitations = new ArrayList<>();		
		invitations = idao.findAll();		
		return invitations;
	}

	@Override
	public void effacerInvitation(int id) {
		// TODO Auto-generated method stub	
	}

	@Override
	public boolean repondreInvitation(Invitation invitation) {
		// TODO Auto-generated method stub
		idao.updateInvitation(invitation);
		return false;
	}

	@Override
	public Invitation ajouterInvitation(Invitation invitation) {
		
		try {
			return this.ajouterInvitation(invitation.getMessage(), invitation.getDateEnvoi(), invitation.getDateReponse(),
					invitation.isEstAcceptee(),invitation.getPersonneInvitant(), invitation.getPersonneInvite());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
