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

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;

import com.intiformation.GestionAppCommerce.Dao.IUserDAO;
import com.intiformation.GestionAppCommerce.Dao.UserDAOImpl;
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
	private Role role;
	private String roleName;

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

		if (userDAO.deleteRole(idUser)) {
			userDAO.delete(idUser);

		}

	}// END suppUser

	/**
	 * methode pour modifier un admin
	 * 
	 * @return
	 */
	public void modifierUser(ActionEvent event) {

		FacesContext context = FacesContext.getCurrentInstance();
		
			boolean verifUpdateUser = userDAO.update(user);

			if (verifUpdateUser == true) {

				// if modif ok //
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification d'un admin",
						" - L'admin a bien été modifiée "));

			} else {
				// ajout not ok//
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Modification d'un admin",
						" - La modification de l'admin à échouée ! "));

			} // END ELSE
	}// END modifierCat

	public void initialiserUser(ActionEvent event) {

		// instantiation nouvel objet
		User userAdd = new User();
		Role role =  new Role();
		
		// Affectation nouvel cat au formulaire ajout
		setUser(userAdd);
		setRole(role);
	}// END initialiser

	public void ajouterUser(ActionEvent event) {

		FacesContext context = FacesContext.getCurrentInstance();
		
			boolean verifAddUser = userDAO.add(user);

			if (verifAddUser == true) {

					// ajout ok //
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajout d'un admin  ",
							" - Le nouvel admin à bien été ajouté "));

				} else {
					// ajout not ok //
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ajout d'un admin  ",
							" - L'ajout du nouvel admin à échoué ! "));

				} // END ELSE
		
	}// END ajouterUser()

	public boolean updateRole() {

		return userDAO.updateRole(user.getRoleName(), user.getIdUser());
	}

	// __________ GETTER/SETTERS __________//
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

}// END CLASS
