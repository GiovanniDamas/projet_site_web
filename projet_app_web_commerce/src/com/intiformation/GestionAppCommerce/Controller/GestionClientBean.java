package com.intiformation.GestionAppCommerce.Controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.intiformation.GestionAppCommerce.Modele.Clients;
import com.intiformation.GestionAppCommerce.Modele.Produit;
import com.intiformation.GestionAppCommerce.Service.ClientsServiceImpl;
import com.intiformation.GestionAppCommerce.Service.IClientsService;

@ManagedBean(name = "clientBean")
@SessionScoped
public class GestionClientBean implements Serializable{
	
	private Clients client;
	
	private IClientsService clientService;

	public GestionClientBean() {
		clientService = new ClientsServiceImpl();
	}
	
	public void initialiserClient() {
		Clients ClientsToAdd = new Clients();
		setClient(ClientsToAdd);
	}
	
	public String ajouterClient() {
		clientService.ajoutClients(client);
		return null;
	}
	
	

	public Clients getClient() {
		return client;
	}

	public void setClient(Clients client) {
		this.client = client;
	}

	public IClientsService getClientService() {
		return clientService;
	}

	public void setClientService(IClientsService clientService) {
		this.clientService = clientService;
	}
	
	
	
	

}
