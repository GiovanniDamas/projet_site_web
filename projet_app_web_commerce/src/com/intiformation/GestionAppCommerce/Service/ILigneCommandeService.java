package com.intiformation.GestionAppCommerce.Service;

import java.util.List;

import com.intiformation.GestionAppCommerce.Modele.LigneCommande;

/**
 * Interface couche service pour la gestion ligneCommande
 * @author giovanni
 *
 */
public interface ILigneCommandeService {

	
	public List<LigneCommande> getAll();
	
	public LigneCommande getByIdCP(LigneCommande LigneC);
	
	public boolean ajoutLigneCommande(LigneCommande ligne);
	
	public List<LigneCommande> getByIdCommande(int idCommande);
	
	
}//END CLASS
