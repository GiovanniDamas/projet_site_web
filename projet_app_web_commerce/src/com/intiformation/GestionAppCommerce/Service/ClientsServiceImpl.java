package com.intiformation.GestionAppCommerce.Service;

import java.util.List;

import com.intiformation.GestionAppCommerce.Dao.ClientsDAOImpl;
import com.intiformation.GestionAppCommerce.Dao.IClientDAO;
import com.intiformation.GestionAppCommerce.Modele.Clients;
import com.intiformation.GestionAppCommerce.Modele.LigneCommande;

/**
 * Classe implémentant l'interface IClientService
 * @author giovanni
 *
 */
public class ClientsServiceImpl implements IClientsService{

	//déclaration de la DAO
	private IClientDAO clientsDAO;
	
	//initialisation de la DAO
	public ClientsServiceImpl() {
		clientsDAO = new ClientsDAOImpl();
	}
	
	@Override
	public boolean ajoutClients(Clients pClients) {
		return clientsDAO.add(pClients);
	}

	@Override
	public boolean updateClients(Clients pClients) {
		return clientsDAO.update(pClients);
	}

	@Override
	public boolean suppClients(Integer pIdClients) {
		return clientsDAO.delete(pIdClients);
	}

	@Override
	public List<Clients> getAll() {
		return null;
	}

	@Override
	public Clients getById(Integer id) {
		return null;
	}

	@Override
	public int validationClientCommande(Clients pClient, List<LigneCommande> listeLC) {
		return clientsDAO.validationClientCommande(pClient, listeLC);
	}

}//END CLASS
