package fr.humanbooster.liaison.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.humanbooster.liaison.business.Invitation;
import fr.humanbooster.liaison.dao.ConnexionBdd;
import fr.humanbooster.liaison.dao.InvitationDao;
import fr.humanbooster.liaison.dao.PersonneDao;
import fr.humanbooster.liaison.dao.Requetes;
import fr.humanbooster.liaison.utils.Dates;


public class InvitationDaoImpl implements InvitationDao {
	private static Connection connection;	
	private static PersonneDao pdao;

	
	public InvitationDaoImpl() throws ClassNotFoundException, SQLException {
		pdao = new PersonneDaoImpl();
		connection = ConnexionBdd.getConnection();
	}

	/**
	 * Create invitation
	 */
	public boolean createInvitation(Invitation invitation) throws SQLException {
		boolean isCreated = false;	

		String query = Requetes.AJOUT_INVITATION;
		PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		
		// Set params
		ps.setString(1, invitation.getMessage());
		java.sql.Date sqlDateEnvoi = new Dates().convertUtilDateToSqlDate(invitation.getDateEnvoi());
		ps.setDate(2, sqlDateEnvoi);
		java.sql.Date sqlDateReponse = new Dates().convertUtilDateToSqlDate(invitation.getDateReponse());
		ps.setDate(3, sqlDateReponse);
		ps.setBoolean(4, invitation.isEstAcceptee());
		
		// On navigue de l'objet Invitation à Personne
		ps.setLong(5, invitation.getPersonneInvitant().getId());
		
		// On navigue de l'objet Invitation à Personne
		ps.setLong(6, invitation.getPersonneInvite().getId());
		
		// Execute requete
		if(ps.executeUpdate() == 1) {
			isCreated = true;
			System.out.println("\nUne invitation a été crée");
		} else {
			System.out.println("\nAucune invitation n'a été crée");
		}
		
		return isCreated;
	}

	
	/**
	 * Get All invitations
	 */
	public List<Invitation> findAll() {
		List<Invitation> invitations = new ArrayList<>();
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(Requetes.TOUTES_LES_INVITATIONS);
			
			while(rs.next()) {
				Invitation invitation = new Invitation(
					rs.getString("message"), 
					rs.getDate("dateEnvoi"),
					rs.getDate("dateReponse"), 
					rs.getBoolean("estAcceptee"), 
					pdao.findById(rs.getLong("idPersonne")), 
					pdao.findById(rs.getLong("idPersonne_Personne"))					
				);
				invitations.add(invitation);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return invitations;
	}

	
	/**
	 * Update Invitation
	 */
	public void updateInvitation(Invitation invitation) {
		// MAJ_INVITATION = "UPDATE invitation SET estAceptee = ? WHERE idInvitation = ?"
		String query = Requetes.MAJ_INVITATION;
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setBoolean(1, invitation.isEstAcceptee());
			pst.setLong(2, invitation.getId());
			
			if(pst.executeUpdate() == 1) {
				System.out.println("\nMise à jour invitation avec succès");
			}			
		} catch (SQLException e) {
			System.out.println("\nEchec mise à jour invitation");
			e.printStackTrace();
		}
		
	}

	
	
	@Override
	public Invitation findInvitationById(Long idInvitation) {
		PreparedStatement pst;
		Invitation invitation = null;
		String query = Requetes.INVITATION_PAR_ID;
		try {
			pst = connection.prepareStatement(query);
			pst.setLong(1, idInvitation);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			rs.next();
			
			invitation = new Invitation(
				rs.getString("message"), 
				rs.getDate("dateEnvoi"),
				rs.getDate("dateReponse"), 
				rs.getBoolean("estAcceptee"), 
				pdao.findById(rs.getLong("idPersonne")), 
				pdao.findById(rs.getLong("idPersonne_Personne"))					
			);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return invitation;
	}
}
