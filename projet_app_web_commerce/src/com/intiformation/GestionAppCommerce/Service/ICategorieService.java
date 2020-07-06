package com.intiformation.GestionAppCommerce.Service;

import java.util.List;

import com.intiformation.GestionAppCommerce.Modele.Categorie;

public interface ICategorieService {
	
	public boolean ajouterCategorie(Categorie categorie);
	
	public boolean modifierCategorie(Categorie categorie);

	public boolean supprimerCategorie(Integer pId);

	public List<Categorie> findAllCategorie();
	
	public Categorie findCategorieById(Integer pId);

}
