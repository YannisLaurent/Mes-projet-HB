package fr.humanbooster.liaison.coordination;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.humanbooster.liaison.business.Personne;
import fr.humanbooster.liaison.service.PersonneService;
import fr.humanbooster.liaison.service.impl.PersonneServiceImpl;

@ManagedBean(name = "connect")
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Personne personne;
	private PersonneService ps;

	public LoginBean() {
		System.out.println("Et j'ai cassé mon code");
		personne = new Personne();
		try {
			ps = new PersonneServiceImpl();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Personne getPersonne() {
		return this.personne;
	}

	public String validateLoginPassword() {
		System.out.println("test validate");
		Personne p = ps.verifierPersonne(personne.getEmail(), personne.getMotDePasse());
		if (p != null) {
			HttpSession session = SessionBean.getSession();
			session.setAttribute("personne", p);
			return "invitation";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Login ou mot de passe incorrect", "Veuillez saisir les bons identifiants"));
			return "login";
		}
	}

	public String logout() {
		HttpSession session = SessionBean.getSession();
		session.invalidate();
		return "admin";
	}
}
