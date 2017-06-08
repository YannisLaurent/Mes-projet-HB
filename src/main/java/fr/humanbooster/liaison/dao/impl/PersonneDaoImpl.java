package fr.humanbooster.liaison.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.humanbooster.liaison.business.Personne;
import fr.humanbooster.liaison.dao.CiviliteDao;
import fr.humanbooster.liaison.dao.ConnexionBdd;
import fr.humanbooster.liaison.dao.PersonneDao;
import fr.humanbooster.liaison.dao.Requetes;
import fr.humanbooster.liaison.dao.VilleDao;
import fr.humanbooster.liaison.utils.Dates;

public class PersonneDaoImpl implements PersonneDao {
	private static Connection connection;
	private static CiviliteDao cdao;
	private static VilleDao vdao;

	public PersonneDaoImpl() throws ClassNotFoundException, SQLException {
		connection = ConnexionBdd.getConnection();
		cdao = new CiviliteDaoImpl();
		vdao = new VilleDaoImpl();
	}

	// Create personne
	public boolean createPersonne(Personne personne) throws SQLException {
		boolean isCreated = false;

		String query = Requetes.AJOUT_PERSONNE;
		PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		// Set param
		ps.setLong(1, personne.getCivilite().getId());
		ps.setString(2, personne.getNom());
		ps.setString(3, personne.getPrenom());
		ps.setString(4, personne.getEmail());
		ps.setString(5, personne.getMotDePasse());

		java.sql.Date sqlDateInscription = new Dates().convertUtilDateToSqlDate(personne.getDateInscription());
		ps.setDate(6, sqlDateInscription);

		java.sql.Date sqlDateNaissance = new Dates().convertUtilDateToSqlDate(personne.getDateNaissance());
		ps.setDate(7, sqlDateNaissance);

		ps.setLong(8, personne.getVille().getId());

		// Execute requete
		if (ps.executeUpdate() == 1) {
			isCreated = true;
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			personne.setId(rs.getLong(1));
			System.out.println("Une personne est crée");
		} else {
			System.out.println("Aucune personne n'est crée");
		}

		return isCreated;
	}

	// Delete personne
	public boolean deletePersonne(Personne personne) throws SQLException {

		boolean isDeleted = false;
		new Requetes();
		String query = Requetes.SUPPR_PERSONNE_PAR_ID;

		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set param
		ps.setDouble(1, personne.getId());

		// Execute requete
		if (ps.executeUpdate() == 1) {
			isDeleted = true;
			System.out.println("Une personne a éte supprimée");
		} else {
			System.out.println("Aucune personne n'a éte supprimée");
		}

		return isDeleted;
	}

	// Update Personne
	public Personne updatePersonne(Personne personne) {
		// TODO Auto-generated method stub
		return null;
	}

	// All personnes
	public List<Personne> findAll() throws SQLException {
		List<Personne> personnes = new ArrayList<>();
		String query = Requetes.TOUTES_LES_PERSONNES;
		Personne personne;

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			personne = new Personne(rs.getString("nom"), rs.getString("prenom"), rs.getString("email"),
					rs.getDate("dateInscription"), rs.getDate("dateNaissance"));

			personne.setId(rs.getLong("idPersonne"));
			personne.setCivilite(cdao.findCivilityById(rs.getLong("idCivilite")));
			personne.setVille(vdao.findCityById(rs.getLong("idVille")));

			personnes.add(personne);
		}
		return personnes;
	}

	// Personne by id
	public Personne getPersonneById(Long idPersonne) {
		PreparedStatement pst;
		Personne personne = null;
		String query = Requetes.PERSONNE_PAR_ID;
		try {
			pst = connection.prepareStatement(query);
			pst.setLong(1, idPersonne);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			rs.next();

			personne = new Personne(rs.getString("nom"), rs.getString("prenom"), rs.getString("email"),
					rs.getDate("dateInscription"), rs.getDate("dateNaissance"));
			personne.setId(rs.getLong("idPersonne"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return personne;
	}

	@Override
	public Personne findByEmailAndPassword(String email, String motDePasse) {
		PreparedStatement pst;
		Personne personne = null;
		System.out.println(email + ", motDePasse: " + motDePasse);
		String query = Requetes.PERSONNE_MAIL_MDP;
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, motDePasse);

			ResultSet rs = pst.executeQuery();
			System.out.println(rs.toString());
			rs.next();

			personne = new Personne(rs.getString("nom"), rs.getString("prenom"), rs.getString("email"),
					rs.getDate("dateInscription"), rs.getDate("dateNaissance"));

			personne.setId(rs.getLong("idPersonne"));
			personne.setCivilite(cdao.findCivilityById(rs.getLong(2)));
			personne.setVille(vdao.findCityById(rs.getLong(9)));

		} catch (SQLException e) {
			System.out.println(e);

		}
		return personne;
	}

	@Override
	public Personne findById(long idPersonne) {
		Personne p;
		PreparedStatement pstmt;
		String query = Requetes.PERSONNE_PAR_ID;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setLong(1, idPersonne);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			p = new Personne(rs.getString("nom"), rs.getString("prenom"), rs.getString("email"),
					rs.getDate("dateInscription"), rs.getDate("dateNaissance"));
			p.setId(rs.getLong("idPersonne"));
			p.setMotDePasse(rs.getString("motDePasse"));
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean changePassword(String motDePasse, Long idPersonne) {
		try {
			PreparedStatement pstmt = connection.prepareStatement(Requetes.MODIFIER_MDP);
			pstmt.setString(1, motDePasse);
			pstmt.setLong(2, idPersonne);
			pstmt.executeUpdate();
			System.out.println("Votre mot de passe a bien été modifier");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
