package com.intiformation.GestionAppCommerce.service;

import java.util.List;

import com.intiformation.GestionAppCommerce.modele.Clients;

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

}//END INTERFACE
