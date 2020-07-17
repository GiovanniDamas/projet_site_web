package com.intiformation.GestionAppCommerce.Service;

import java.util.List;

import com.intiformation.GestionAppCommerce.Modele.Produit;

public interface IProduitService {
	
	public boolean ajouterProduit(Produit produit);
	
	public boolean modifierProduit(Produit produit);

	public boolean supprimerProduit(Integer pId);

	public List<Produit> findAllProduit();
	
	public Produit findProduitById(Integer pId);
	
	public List<Produit> findByCategorie(String nomCategorie);
	
	public List<Produit> findByKeywords(String motsCles);
	
	public List<Produit> findBySelectionne();
	
	public List<Integer> getListeQuantite(Integer idProduit);
	
	public List<Produit> findByKeywordsAndCategorie(String motsCles, String nomCategorie);
	
	public boolean updateQttAndSelectionne(int quantite, boolean selectionne, int idProduit);


}
