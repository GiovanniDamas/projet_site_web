package com.intiformation.GestionAppCommerce.Dao;

import java.util.List;

import com.intiformation.GestionAppCommerce.Modele.LigneCommande;

public interface ILigneCommandeDAO extends IGenericDAO<LigneCommande> {

	public LigneCommande getByIdCP(LigneCommande LigneC);
	
	public List<LigneCommande> getByIdCommande(int idCommande);

}//END INTERFACE
