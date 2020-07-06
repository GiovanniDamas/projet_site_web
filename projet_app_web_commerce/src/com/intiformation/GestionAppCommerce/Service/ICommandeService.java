package com.intiformation.GestionAppCommerce.Service;

import java.util.List;

import com.intiformation.GestionAppCommerce.modele.Commande;


public interface ICommandeService {
	
	public boolean ajouterCommande(Commande commande);
	
	public boolean modifierCommande(Commande commande);

	public boolean supprimerCommande(Integer pId);

	public List<Commande> findAllCommande();
	
	public Commande findCommandeById(Integer pId);

}
