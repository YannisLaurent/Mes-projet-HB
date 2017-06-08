package fr.humanbooster.liaison.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.humanbooster.liaison.business.Personne;
import fr.humanbooster.liaison.service.PersonneService;
import fr.humanbooster.liaison.service.impl.PersonneServiceImpl;

/**
 * Servlet implementation class ConnexionServlet
 */
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnectionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Une personne souhaite se connecter.
		PersonneService ps;
		try {
			ps = new PersonneServiceImpl();
			String email = request.getParameter("mail");
			String motDePasse = request.getParameter("motDePasse");
			Personne pers = ps.verifierPersonne(email, motDePasse);
			System.out.println(pers);
			if (pers != null) {
				// La personne a saisi le bon mail et le bon mdp
				// On va creer un objet de session pour cette personne
				// Pour se faire on utilise la methode
				// request.getSession().setAttribute;
				request.getSession().setAttribute("idPersonne", pers.getId());
				// On redirige vers la page tableauDeBord.jsp
				// Au préalabre on enrichit l'objet request en ajoutant en
				// attribut
				// l'objet personne.
				List<Personne> lp = new ArrayList<>();
				lp = ps.getAllPersons();
				request.setAttribute("personne", pers);
				request.setAttribute("listePersonne", lp);
				request.getRequestDispatcher("tableauDeBord.jsp").forward(request, response);

			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
