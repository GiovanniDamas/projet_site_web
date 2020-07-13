package com.intiformation.GestionAppCommerce.Service;

import java.util.List;

import com.intiformation.GestionAppCommerce.Dao.ILigneCommandeDAO;
import com.intiformation.GestionAppCommerce.Dao.LigneCommandeDAOImpl;
import com.intiformation.GestionAppCommerce.Modele.LigneCommande;

/**
 * Classe implémentant la couche service pour la ligne de commande
 * @author giovanni
 *
 */
public class LigneCommandeServiceImpl implements ILigneCommandeService {

	
	ILigneCommandeDAO ligneCommandeDAO = new LigneCommandeDAOImpl();
	
	@Override
	public List<LigneCommande> getAll() {
		return ligneCommandeDAO.getAll();
	}

	@Override
	public LigneCommande getByIdCP(LigneCommande LigneC) {
		return ligneCommandeDAO.getByIdCP(LigneC);
	}

	@Override
	public boolean ajoutLigneCommande(LigneCommande ligne) {
		return ligneCommandeDAO.add(ligne);
	}

	
}//END CLASS
