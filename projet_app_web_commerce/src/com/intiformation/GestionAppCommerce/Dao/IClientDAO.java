package com.intiformation.GestionAppCommerce.Dao;

import java.util.List;

import com.intiformation.GestionAppCommerce.Modele.Clients;
import com.intiformation.GestionAppCommerce.Modele.LigneCommande;

/**
 * interface pour la couche CLientDAO qui hérite de IGenericDAO
 * @author giovanni
 *
 */
public interface IClientDAO extends IGenericDAO<Clients>{
	
	public int validationClientCommande(Clients pClient, List<LigneCommande> listeLC);
	
	public int validationClientCommande(int idClient, List<LigneCommande> listeLC);
	
	

}//END CLASS
