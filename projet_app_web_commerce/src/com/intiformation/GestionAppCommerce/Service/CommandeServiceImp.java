package com.intiformation.GestionAppCommerce.Service;

import java.util.List;

import com.intiformation.GestionAppCommerce.Dao.CommandeDAOImp;
import com.intiformation.GestionAppCommerce.Dao.ICommandeDAO;
import com.intiformation.GestionAppCommerce.Modele.Commande;

public class CommandeServiceImp implements ICommandeService {
	
	ICommandeDAO commandeDAO = new CommandeDAOImp();

	@Override
	public boolean ajouterCommande(Commande commande) {
		return commandeDAO.add(commande);
	}

	@Override
	public boolean modifierCommande(Commande commande) {
		return commandeDAO.update(commande);
	}

	@Override
	public boolean supprimerCommande(Integer pId) {
		return commandeDAO.delete(pId);
	}

	@Override
	public List<Commande> findAllCommande() {
		return commandeDAO.getAll();
	}

	@Override
	public Commande findCommandeById(Integer pId) {
		return commandeDAO.getById(pId);
	}

}
