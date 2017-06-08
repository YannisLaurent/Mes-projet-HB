package fr.humanbooster.liaison.coordination;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import fr.humanbooster.liaison.business.Invitation;
import fr.humanbooster.liaison.business.Personne;
import fr.humanbooster.liaison.service.InvitationService;
import fr.humanbooster.liaison.service.PersonneService;
import fr.humanbooster.liaison.service.impl.InvitationServiceImpl;
import fr.humanbooster.liaison.service.impl.PersonneServiceImpl;

@ManagedBean(name = "invitationBean")
@RequestScoped
public class InvitationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private PersonneService ps;
	private InvitationService is;
	// On crée une collection privée de personnes cette collection sera envoyé à
	// la vue.
	private List<Personne> personnes;

	/**
	 * Les données choisies par l'utilisateur vont alimenter un objet
	 * invitation.
	 */
	private Invitation invitation;
	private Long idPersonneInvitee;

	public InvitationBean() {
		try {
			invitation = new Invitation();
			ps = new PersonneServiceImpl();
			is = new InvitationServiceImpl();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cette méthode permet de renvoyer la liste des personnes stockées en base
	 * Pour ce faire on fait appel à un objet de la couche service.
	 * 
	 * @return
	 */
	public List<Personne> getPersonnes() {
		personnes = ps.getAllPersons();
		return personnes;
	}

	public void setPersonnes(List<Personne> personnes) {
		this.personnes = personnes;
	}

	public Invitation getInvitation() {
		return invitation;
	}

	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}

	public Long getIdPersonneInvitee() {
		return idPersonneInvitee;
	}

	public void setIdPersonneInvitee(Long idPersonneInvitee) {
		this.idPersonneInvitee = idPersonneInvitee;
	}

	public String envoyer() {
		Personne personneInvitee = ps.trouverPersonneAvecId(idPersonneInvitee);
		invitation.setPersonneInvite(personneInvitee);
		// on utilise la méthode getSession pour récuperer l'objet personne stocké
		// dans la session
		invitation.setPersonneInvitant((Personne) SessionBean.getSession().getAttribute("personne"));
		// invitation.setPersonneInvitant(personneInvitant);
		// On recupere la valeur de la liste deroulante grace a cette valeur on
		// retrouve lobjet personne correspondant
		// Il nous rreeste a associer cette personne a linvitation
		try {
			is.ajouterInvitation(invitation);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "invitationEnvoyer";
	}
}
