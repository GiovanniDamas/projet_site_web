package com.intiformation.GestionAppCommerce.Service;

import java.util.List;

import com.intiformation.GestionAppCommerce.Dao.IProduitDAO;
import com.intiformation.GestionAppCommerce.Dao.ProduitDAOImp;
import com.intiformation.GestionAppCommerce.Modele.Produit;

public class ProduitServiceImp implements IProduitService{
	
	IProduitDAO produitDAO = new ProduitDAOImp();

	@Override
	public boolean ajouterProduit(Produit produit) {
		return produitDAO.add(produit);
	}

	@Override
	public boolean modifierProduit(Produit produit) {
		return produitDAO.update(produit);
	}

	@Override
	public boolean supprimerProduit(Integer pId) {
		return produitDAO.delete(pId);
	}

	@Override
	public List<Produit> findAllProduit() {
		return produitDAO.getAll();
	}

	@Override
	public Produit findProduitById(Integer pId) {
		return produitDAO.getById(pId);
	}

	@Override
	public List<Produit> findByCategorie(String nomCategorie) {
		return produitDAO.getByCategorie(nomCategorie);
	}

	@Override
	public List<Produit> findByKeywords(String motsCles) {
		motsCles.replace(" ", "|");
		return produitDAO.getByKeywords(motsCles);
	}

	@Override
	public List<Produit> findBySelectionne() {
		return produitDAO.getBySelectionne();
	}

}
