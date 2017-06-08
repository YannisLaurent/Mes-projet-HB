package fr.humanbooster.liaison.dao;

public class Requetes {

	public static final String TOUTES_LES_VILLES = "SELECT * FROM ville";
	public static final String VILLE_PAR_ID = "SELECT * FROM ville WHERE idVille = ?";
	
	public static final String TOUTES_LES_CIVILITES = "SELECT * FROM civilite";
	public static final String CIVILITE_PAR_ID = "SELECT * FROM civilite WHERE idCivilite = ?";
	
	public static final String TOUTES_LES_PERSONNES = "SELECT * FROM personne";
	public static final String PERSONNE_PAR_ID = "SELECT * FROM personne WHERE idPersonne = ?";
	public static final String AJOUT_PERSONNE = "INSERT INTO personne (idCivilite, nom, prenom, email, motDePasse, dateInscription, dateNaissance, idVille) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";	
	public static final String SUPPR_PERSONNE_PAR_ID = "DELETE FROM PERSONNE WHERE idPersonne = ?";
	public static final String MODIFIER_MDP = "UPDATE personne SET motDePasse = ? WHERE idPersonne = ?";
		
	public static final String TOUTES_LES_INVITATIONS = "SELECT * FROM invitation";
	public static final String INVITATION_PAR_ID = "select * FROM invitation WHERE idInvitation = ?";
	public static final String AJOUT_INVITATION = "INSERT INTO invitation (message, dateEnvoi, dateReponse, estAcceptee, idPersonne, idPersonne_Personne) VALUES (?, ?, ?, ?, ?, ?)";	
	public static final String MAJ_INVITATION = "UPDATE invitation SET estAcepte = ? WHERE idInvitation = ?";
	
	public static final String PERSONNE_MAIL_MDP = "SELECT * FROM personne WHERE email=? and motDePasse=?";
	
	public static final String AFFICHER_MON_RESEAU = "SELECT * FROM reseau where idPersonne = ?";
	
}
