package fr.humanbooster.liaison.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.humanbooster.liaison.business.Personne;
import fr.humanbooster.liaison.service.PersonneService;
import fr.humanbooster.liaison.service.impl.PersonneServiceImpl;

/**
 * Servlet implementation class ModifierMotDePasseServlet
 */
public class ModifierMotDePasseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PersonneService ps;

	public ModifierMotDePasseServlet() throws ClassNotFoundException, SQLException {
		super();
		ps = new PersonneServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("je suis dans le doGet");
		request.getRequestDispatcher("modifierMotDePasse.jsp").forward(request, response);
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("je suis dans le doPost");
		String ancienMotDePasse = request.getParameter("ancienMotDePasse");
		String nouveauMotDePasse = request.getParameter("nouveauMotDePasse");
		String confirmerNouveauMotDePasse = request.getParameter("confirmerNouveauMotDePasse");
		Long idInvitante = Long.parseLong(String.valueOf(request.getSession().getAttribute("idPersonne")));
		Personne personne = ps.trouverPersonneAvecId(idInvitante);
		
		if ((personne.getMotDePasse().equals(ancienMotDePasse))
				&& (nouveauMotDePasse.equals(confirmerNouveauMotDePasse))) {
			ps.changerMotDePasse(nouveauMotDePasse, idInvitante);
			System.out.println("Votre mot de passe a été changé");
			
			request.getRequestDispatcher("tableauDeBord.jsp").forward(request, response);
		} else {
			doGet(request, response);
			System.out.println("Votre mot de passe n'a pas été modifié");
		}

	}

}
