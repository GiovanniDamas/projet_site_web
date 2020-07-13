package com.intiformation.GestionAppCommerce.Dao;

import com.intiformation.GestionAppCommerce.Modele.LigneCommande;

public interface ILigneCommandeDAO extends IGenericDAO<LigneCommande> {

	public LigneCommande getByIdCP(LigneCommande LigneC);

}//END INTERFACE
