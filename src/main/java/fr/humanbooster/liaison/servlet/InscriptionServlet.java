package fr.humanbooster.liaison.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.humanbooster.liaison.business.Civilite;
import fr.humanbooster.liaison.business.Ville;
import fr.humanbooster.liaison.service.CiviliteService;
import fr.humanbooster.liaison.service.PersonneService;
import fr.humanbooster.liaison.service.VilleService;
import fr.humanbooster.liaison.service.impl.CiviliteServiceImpl;
import fr.humanbooster.liaison.service.impl.PersonneServiceImpl;
import fr.humanbooster.liaison.service.impl.VilleServiceImpl;
import fr.humanbooster.liaison.utils.Dates;

/**
 * Servlet implementation class InscriptionServlet
 */
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PersonneService ps;
	private CiviliteService cs;
	private VilleService vs;
	
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() throws ClassNotFoundException, SQLException {
        super();
        ps = new PersonneServiceImpl();
        cs = new CiviliteServiceImpl();
        vs = new VilleServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println(cs.findAllCivilities());
			// On enrichit l'objet request avec l'objet 'civilites'
			request.setAttribute("civilites", cs.findAllCivilities());
			
			System.out.println(vs.findAllCities());
			// On enrichit l'objet request avec l'objet 'villes'
			request.setAttribute("villes", vs.findAllCities());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Une personne souhaite s'inscrire sur Liaison. Elle a rempli le formulaire html d'inscription et a cliqué sur le bouton 'Inscription'");
		
		// Récupérer les parameters de post
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		
		// Civilite : String to Object
		String civiliteStr = request.getParameter("civilite");
		
		
		Civilite civiliteObj = null;
		List<Civilite> civilites;
		try {
			civilites = cs.findAllCivilities();
			for(Civilite c:civilites) {
				System.out.println(c);
				if(c.getId() == Long.parseLong(civiliteStr)){
					civiliteObj = c;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		
		// Mot de passe
		String motDePasse = request.getParameter("motDePasse");
		
		// Ville : String to Object
		String villeStr = request.getParameter("ville");
		Ville villeObj = null;
		List<Ville> villes;
		try {
			villes = vs.findAllCities();
			for(Ville v:villes) {
				if(v.getId() == Long.parseLong(villeStr)) {
					villeObj = v;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// Date inscription
		//Date dateInscription = new Dates().getDateFromString(request.getParameter("dateInscription"));		
		Date dateInscription = new Date();
		
		// Date naissance
		Date dateNaissance = new Dates().getDateFromString(request.getParameter("dateNaissance"));
		
		// Create Personne
		ps.ajouterPersonne(civiliteObj, nom, prenom, email, motDePasse, dateInscription, dateNaissance, villeObj);
		
		
		doGet(request, response);
		
		
	}

}
