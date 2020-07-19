package com.intiformation.GestionAppCommerce.Dao;

import com.intiformation.GestionAppCommerce.Modele.User;

/**
 * Interface DAO de l'user qui hérite de l'interface IGENERICDAO
 * @author giovanni
 *
 */
public interface IUserDAO extends IGenericDAO<User>{
		
	public boolean isUserExists(String pIdentifiant, String pMotDePasse);
	
	public boolean attribuerRole(String roleName, Integer idUser);
	
	public boolean updateRole (String roleName, Integer idUser);

	public boolean deleteRole(Integer pIdUser);

	public User getByMdp(String pMdp);



}//END INTERFACE
