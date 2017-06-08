package fr.humanbooster.liaison.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.humanbooster.liaison.business.Personne;
import fr.humanbooster.liaison.service.InvitationService;
import fr.humanbooster.liaison.service.PersonneService;
import fr.humanbooster.liaison.service.impl.InvitationServiceImpl;
import fr.humanbooster.liaison.service.impl.PersonneServiceImpl;


public class InvitationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static InvitationService is;
	private static PersonneService ps;
	
    public InvitationServlet()throws ClassNotFoundException, SQLException {
        super();
        is = new InvitationServiceImpl();
        ps = new PersonneServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String msg = request.getParameter("message");
		Personne invitee = ps.trouverPersonneAvecId(Long.valueOf(request.getParameter("PersonneInvitee").toString()));
		
		//on recupère l'objet personne en utilisant l'objet de session.
		Long idInvitante = Long.parseLong(request.getSession().getAttribute("idPersonne").toString());
		Personne invitante = ps.trouverPersonneAvecId(idInvitante);
		 try {
			is.ajouterInvitation(msg, new Date(), new Date(), true, invitante , invitee);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
