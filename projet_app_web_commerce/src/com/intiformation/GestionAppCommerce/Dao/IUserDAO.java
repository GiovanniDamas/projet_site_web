package com.intiformation.GestionAppCommerce.Dao;

import com.intiformation.GestionAppCommerce.Modele.Role;
import com.intiformation.GestionAppCommerce.Modele.User;

/**
 * Interface DAO de l'user qui hérite de l'interface IGENERICDAO
 * @author giovanni
 *
 */
public interface IUserDAO extends IGenericDAO<User>{
		
	public boolean isUserExists(String pIdentifiant, String pMotDePasse);
	
	public boolean attribuerRole(Role role, Integer idUser);
	
	public boolean updateRole (Role role, Integer idUser);



}//END INTERFACE
