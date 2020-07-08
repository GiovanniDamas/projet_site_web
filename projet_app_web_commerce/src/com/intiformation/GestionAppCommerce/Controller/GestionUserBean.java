package com.intiformation.GestionAppCommerce.Controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.intiformation.GestionAppCommerce.Dao.IUserDAO;
import com.intiformation.GestionAppCommerce.Dao.UserDAOImpl;
import com.intiformation.GestionAppCommerce.Modele.User;

/**
 * ManagedBean pour la gestion des user 
 * @author giovanni
 *
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class GestionUserBean implements Serializable {

	//_______________ PROPS _________________//
	private List<User> listeUserBdd;
	private User user;
	
	private static String[] role;
	private static String[] actif;
	
	static {
	role = new String [2]; 
	role[1] = "AdminCat";	 
	role[2] = "AdminProd";	
	
	actif = new String[2];
	actif[1] = "Oui";
	actif[2] = "Non";
	}
	
	//DAO 
	IUserDAO userDAO;
	
	//_______________ CTOR _________________//
	/**
	 * ctor vide pour le serveur
	 */
	public GestionUserBean() {
		userDAO = new UserDAOImpl();
	}
	
	
	//______________ METHODE ______________//
	/**
	 * Méthode pour afficher user bdd
	 */
	public List<User> findAllUser() {
		
		listeUserBdd = userDAO.getAll();
		
		return listeUserBdd;
	}//END METHOD
	
	
	/**
	 * Méthode pour l'ajout d'un User
	 */
	
	
	
	//__________ GETTER/SETTERS __________//
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}//END CLASS
