package com.intiformation.GestionAppCommerce.service;

import java.util.List;

import com.intiformation.GestionAppCommerce.dao.IUserDAO;
import com.intiformation.GestionAppCommerce.dao.UserDAOImpl;
import com.intiformation.GestionAppCommerce.modele.Role;
import com.intiformation.GestionAppCommerce.modele.User;

/**
 * Classe qui implémente la couche service de l'user
 * @author giovanni
 *
 */
public class UserServiceImpl implements IUserService{

	//déclaration DAO 
	private IUserDAO userDAO;
	
	//initilisation DAO
	public UserServiceImpl() {
		userDAO = new UserDAOImpl();
	}
	
	@Override
	public boolean ajoutUser(User pUser) {
		return userDAO.add(pUser);
	}

	@Override
	public boolean updateUser(User pUser) {
		return userDAO.update(pUser);
	}

	@Override
	public boolean deleteUser(Integer pIdUser) {
		return userDAO.delete(pIdUser);
	}

	@Override
	public List<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	public User getById(Integer pIdUser) {
		return userDAO.getById(pIdUser);
	}

	@Override
	public boolean isUserExists(String pIdentifiant, String pMotDePasse) {
		return userDAO.isUserExists(pIdentifiant, pMotDePasse);
	}

	@Override
	public boolean attribuerRole(Role pRole, Integer pIdUser) {
		return userDAO.attribuerRole(pRole, pIdUser);
	}

	@Override
	public boolean updateRole(Role pRole, Integer pIdUser) {
		return userDAO.updateRole(pRole, pIdUser);
	}

}//END CLASS
