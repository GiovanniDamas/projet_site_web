package com.intiformation.GestionAppCommerce.Service;

import java.util.List;

import com.intiformation.GestionAppCommerce.Modele.Clients;
import com.intiformation.GestionAppCommerce.Modele.LigneCommande;

/**
 * interface de la couche service pour le client <br/>
 * Permet d'afficher les fonctionnalités de ce dernier lié à la couche DAO
 * @author giovanni
 *
 */
public interface IClientsService {
	
	 public boolean ajoutClients(Clients pClients);

	    public boolean updateClients(Clients pClients);

	    public boolean suppClients(Integer pIdClients);

	    public List<Clients> getAll();

	    public Clients getById(Integer id);
	    
	    public int validationClientCommande(Clients pClient, List<LigneCommande> listeLC);
	    
	    public int validationClientCommande(int idClient, List<LigneCommande> listeLC);

}//END INTERFACE
