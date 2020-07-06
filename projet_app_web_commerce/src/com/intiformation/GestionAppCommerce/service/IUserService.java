package com.intiformation.GestionAppCommerce.service;

import java.util.List;

import com.intiformation.GestionAppCommerce.modele.Role;
import com.intiformation.GestionAppCommerce.modele.User;

/**
 * interface de la couche service pour l'user permettant d'afficher les fonctionnalités de l'user
 * @author giovanni
 *
 */

public interface IUserService {
	
	public boolean ajoutUser(User pUser);
	
	public boolean updateUser(User pUser);
	
	public boolean deleteUser(Integer pIdUser);

	public List<User> getAll();

	public User getById(Integer pIdUser);
	
	public boolean isUserExists(String pIdentifiant, String pMotDePasse);
	
	public boolean attribuerRole(Role pRole, Integer pIdUser);
	
	public boolean updateRole (Role pRole, Integer pIdUser);
	
	
}//END INTERFACE
