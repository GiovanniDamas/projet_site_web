package com.intiformation.GestionAppCommerce.Controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.intiformation.GestionAppCommerce.Modele.Commande;

import com.intiformation.GestionAppCommerce.Service.CommandeServiceImp;
import com.intiformation.GestionAppCommerce.Service.ICommandeService;

@ManagedBean(name = "commandeBean")
@SessionScoped
public class CommandeBean {

	private Commande commande;
	private List<Commande> listecommande;

	private ICommandeService commandeService;

	public CommandeBean() {
		commandeService = new CommandeServiceImp();
	}
	
	public List<Commande> findAll(){
		listecommande = commandeService.findAllCommande();
		return listecommande;
	}

	public String editerFacture() {

		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		setCommande(commandeService.findCommandeById((int) session.getAttribute("idCommande")));

		return "toFacture";
	}

	public void detruireCommande() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

		Commande commandeToEdit = commandeService.findCommandeById((int) session.getAttribute("idCommande"));
		session.removeAttribute("idCommande");
		commande = null;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

}
