package com.intiformation.GestionAppCommerce.Controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.GestionAppCommerce.Dao.IUserDAO;
import com.intiformation.GestionAppCommerce.Dao.UserDAOImpl;
import com.intiformation.GestionAppCommerce.Modele.Categorie;
import com.intiformation.GestionAppCommerce.Modele.Role;
import com.intiformation.GestionAppCommerce.Modele.User;

/**
 * ManagedBean pour la gestion des user
 * 
 * @author giovanni
 *
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class GestionUserBean implements Serializable {

	// _______________ PROPS _________________//
	private List<User> listeUserBdd;
	private User user;
	private String roleName;

	private String[] actif = { "Oui", "Non" };

	private String[] role = { "AdminCat", "AdminProd" };

	// DAO
	IUserDAO userDAO;

	// _______________ CTOR _________________//
	/**
	 * ctor vide pour le serveur
	 */
	public GestionUserBean() {
		userDAO = new UserDAOImpl();
	}

	// ______________ METHODE ______________//
	/**
	 * Méthode pour afficher user bdd
	 */
	public List<User> findAllUser() {

		listeUserBdd = userDAO.getAll();

		return listeUserBdd;
	}// END METHOD
	
	
	/**
	 * méthode pour recupérer une admin par son id
	 */
	public void selectUser(ActionEvent event) {

		UIParameter component = (UIParameter) event.getComponent().findComponent("updateID");
		UIParameter componentRole = (UIParameter) event.getComponent().findComponent("roleName");

		int idUser = (int) component.getValue();
		String roleName = (String) componentRole.getValue();

		User user = userDAO.getById(idUser);

		setUser(user);
		setRoleName(roleName);

	}// END selectCat

	/**
	 * Méthode pour supprimer un admin
	 * 
	 * @param event
	 */
	public void suppUser(ActionEvent event) {

		UIParameter component = (UIParameter) event.getComponent().findComponent("deleteID");
		int idUser = (int) component.getValue();

		userDAO.delete(idUser);

	}// END suppCat

	/**
	 * methode pour modifier un admin
	 * 
	 * @return
	 */
	public String modifierUser() {

		FacesContext context = FacesContext.getCurrentInstance();

		if (userDAO.update(user) && userDAO.updateRole(user.getRoleName(), user.getIdUser())) {

			// if modif ok //
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification d'un admin",
					" - L'admin a bien été modifiée "));

			return "gestionUser.xhtml";

		} else {
			// ajout not ok//
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Modification d'un admin",
					" - La modification de l'admin à échouée ! "));

			return "modifierUser.xhtml";

		} // END ELSE

	}// END modifierCat

	public void initialiserUser(ActionEvent event) {

		// instantiation nouvel objet
		this.user = new User();

		// Affectation nouvel cat au formulaire ajout
		setUser(user);
		setRoleName(roleName);
	}// END initialiser

	public void ajouterUser(ActionEvent event) {

		FacesContext context = FacesContext.getCurrentInstance();

	  //UIParameter component = (UIParameter) event.getComponent().findComponent("userID");
		UIParameter componentRole = (UIParameter) event.getComponent().findComponent("roleName");

	  //int idUser = (int) component.getValue();
		String roleName = (String) componentRole.getValue();

		// boolean verifAdd = userDAO.add(user);

		// boolean verifAddRole = userDAO.attribuerRole(user.getRoleName(),

		if (userDAO.add(user)) {

			// ajout ok //
			if (user.getIdUser() != 0) {
				userDAO.attribuerRole(roleName, user.getIdUser());

				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajout d'un admin  ",
						" - Le nouvel admin à bien été ajouté "));

			} else {
				// ajout not ok //
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ajout d'un admin  ",
						" - L'ajout du nouvel admin à échoué ! "));

			}//END IF
		} // END ELSE

	}// END ajouter()

	// __________ GETTER/SETTERS __________//
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String[] getActif() {
		return actif;
	}

	public void setActif(String[] actif) {
		this.actif = actif;
	}


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public List<String> getRole() {
		return Arrays.asList(role);
	}

	public void setRole(String[] role) {
		this.role = role;
	}
	

}// END CLASS
