package com.intiformation.GestionAppCommerce.Service;

import java.util.List;

import com.intiformation.GestionAppCommerce.dao.CategorieDAOImp;
import com.intiformation.GestionAppCommerce.dao.ICategorieDAO;
import com.intiformation.GestionAppCommerce.modele.Categorie;

public class CategorieServiceImp implements ICategorieService{
	
	ICategorieDAO categorieDAO = new CategorieDAOImp();

	@Override
	public boolean ajouterCategorie(Categorie categorie) {
		return categorieDAO.add(categorie);
	}

	@Override
	public boolean modifierCategorie(Categorie categorie) {
		return categorieDAO.update(categorie);
	}

	@Override
	public boolean supprimerCategorie(Integer pId) {
		return categorieDAO.delete(pId);
	}

	@Override
	public List<Categorie> findAllCategorie() {
		return categorieDAO.getAll();
	}

	@Override
	public Categorie findCategorieById(Integer pId) {
		return categorieDAO.getById(pId);
	}

}
