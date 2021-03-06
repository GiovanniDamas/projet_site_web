package com.intiformation.GestionAppCommerce.Controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.intiformation.GestionAppCommerce.Modele.Clients;
import com.intiformation.GestionAppCommerce.Modele.LigneCommande;
import com.intiformation.GestionAppCommerce.Modele.Produit;
import com.intiformation.GestionAppCommerce.Service.ClientsServiceImpl;
import com.intiformation.GestionAppCommerce.Service.IClientsService;
import com.intiformation.GestionAppCommerce.Service.IProduitService;
import com.intiformation.GestionAppCommerce.Service.ProduitServiceImp;

@ManagedBean(name = "clientBean")
@SessionScoped
public class GestionClientBean implements Serializable{
	
	private Clients client;
	private LigneCommande ligneCommande;
	private Produit produit;
	
	private IClientsService clientService;
	private IProduitService produitService;

	public GestionClientBean() {
		clientService = new ClientsServiceImpl();
		produitService = new ProduitServiceImp();
	}
	
	public void initialiserClient() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		
		Clients ClientsToAdd = new Clients();
		
		if (session.getAttribute("client")!= null) {
			ClientsToAdd = (Clients) session.getAttribute("client");
		}
		setClient(ClientsToAdd);
		
	}
	
	public void ajouterClientCommande() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		if(client.getIdClient()!=0) {
			client =clientService.getById(client.getIdClient());
			int idCommande = clientService.validationClientCommande(client.getIdClient(), (List<LigneCommande>) session.getAttribute("listeLigneCommande"));
			session.setAttribute("client", client);
			session.setAttribute("idCommande", idCommande);
			
		}else {
			
			int idCommande = clientService.validationClientCommande(client, (List<LigneCommande>) session.getAttribute("listeLigneCommande"));
			session.setAttribute("client", client);
			session.setAttribute("idCommande", idCommande);
		}
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
