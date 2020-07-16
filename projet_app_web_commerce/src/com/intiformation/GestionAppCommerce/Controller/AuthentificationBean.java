package com.intiformation.GestionAppCommerce.Controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.intiformation.GestionAppCommerce.Dao.IUserDAO;
import com.intiformation.GestionAppCommerce.Dao.UserDAOImpl;
import com.intiformation.GestionAppCommerce.Modele.User;

/**
 * ManagedBean pour la gestion de l'authentification
 * 
 * @author giovanni
 *
 */
@ManagedBean(name = "connexionBean")
@SessionScoped
public class AuthentificationBean implements Serializable {

	// _____________ PROPS ___________ //
	private String userIdentifiant;
	private String userPassword;
	private User user;
	private String roleName;


	// dao
	private IUserDAO userDAO;

	// ___________________ CTOR _________________ //
	/**
	 * ctor vide
	 */
	public AuthentificationBean() {
		userDAO = new UserDAOImpl();
	}

	
	// _____________ METHODE _______________ //
	public String connexionAdmin() {

		FacesContext context = FacesContext.getCurrentInstance();	

		
		if (userDAO.isUserExists(userIdentifiant, userPassword)) {
			
			// creation session
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			
			session.setAttribute("user_login", userIdentifiant);
			
			user = userDAO.getByMdp(userPassword);
			roleName = user.getRoleName();

			
			switch (roleName) {
			case "AdminCat":
				return "accueilAdmin.xhtml";
				

			case "AdminProd":
				return "accueilAdmin.xhtml";
			
			}
		
		} else {
			// ---------------- l'utilisateur n'existe pas -------------------//
			
			// -> définition du message à envoyer avec un objet de type FacesMessage
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, " Echec de connexion ",
					" Identifiant ou Mot de passe invalide ");

			// envoi du message vers la vue via le context de jsf (l'objet 'FacesContext')
			context.addMessage(null, message);

			// -> navigation vers la page du formulaire d'authentification
			return "authentification.xhtml";
			
		} // END ELSE
		return "authentification.xhtml";

	}//END connexionAdmin
	
	/**
	 * log out user 
	 * @return
	 */
	public String deconnexionAdmin() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		
		session.invalidate();
		
		return "/Magasin.xhtml";
		
	}//end logOut()

	// _____________ GETTER/SETTER ___________ //

	public String getUserIdentifiant() {
		return userIdentifiant;
	}

	public void setUserIdentifiant(String userIdentifiant) {
		this.userIdentifiant = userIdentifiant;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

}// END CLASS
