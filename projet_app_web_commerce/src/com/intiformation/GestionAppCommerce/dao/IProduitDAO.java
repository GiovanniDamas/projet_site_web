package com.intiformation.GestionAppCommerce.dao;

import java.util.List;

import com.intiformation.GestionAppCommerce.modele.Produit;

public interface IProduitDAO extends IGenericDAO<Produit>{
	
	public List<Produit> getByCategorie(String nomCategorie);
	
	public List<Produit> getByKeywords(String motsCles);
	
	public List<Produit> getBySelectionne();

}
