package com.intiformation.GestionAppCommerce.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.intiformation.GestionAppCommerce.Modele.LigneCommande;
import com.intiformation.GestionAppCommerce.Modele.Produit;
import com.intiformation.GestionAppCommerce.Service.CommandeServiceImp;
import com.intiformation.GestionAppCommerce.Service.IClientsService;
import com.intiformation.GestionAppCommerce.Service.ICommandeService;
import com.intiformation.GestionAppCommerce.Service.ILigneCommandeService;
import com.intiformation.GestionAppCommerce.Service.LigneCommandeServiceImpl;


@ManagedBean(name = "panierBean")
@SessionScoped
public class GestionPanierBean implements Serializable {
	
	private int quantite, produitID;
	private Double prix;
	private List<LigneCommande> listeLigneCommande;
	private LigneCommande ligneCommande;
	
	private ILigneCommandeService ligneCommandeService;
	private ICommandeService commandeService;
	private IClientsService clientService;

	public GestionPanierBean() {
		ligneCommandeService = new LigneCommandeServiceImpl();
		commandeService = new CommandeServiceImp();
	}
	
	public void ajouterLigneCommande(ActionEvent event) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		Map params = context.getExternalContext().getRequestParameterMap();

		produitID = Integer.parseInt((String) params.get("produitID"));
		prix = Double.parseDouble((String) params.get("prix"));
		
		ligneCommande = new LigneCommande(produitID, quantite, prix);
		
		listeLigneCommande = new ArrayList<>();
		
		if (session.getAttribute("listeLigneCommande") != null) {
			listeLigneCommande = (List<LigneCommande>) session.getAttribute("listeLigneCommande");
		}
		
		listeLigneCommande.add(ligneCommande);

		session.setAttribute("listeLigneCommande", listeLigneCommande);
		
	}
	

	public double sommePrix() {

		double prixTotal = 0;

		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

		listeLigneCommande = (List<LigneCommande>) session.getAttribute("listeLigneCommande");

		for (LigneCommande lc : listeLigneCommande) {

			prixTotal = prixTotal + lc.getPrix()* lc.getQuantite();

		}
		return prixTotal;
		
	}// 
	
	

	public void suppLigneCommande(ActionEvent event) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

		UIParameter uipLigneCommande = (UIParameter) event.getComponent().findComponent("ligneCommande");
		LigneCommande lc = (LigneCommande) uipLigneCommande.getValue();
				
		int indexSupp = listeLigneCommande.indexOf(lc);
		
		listeLigneCommande.remove(indexSupp);
		
		session.setAttribute("listeLigneCommande", listeLigneCommande);

	}
	
	
	public void viderPanier() {
		
		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		listeLigneCommande = (List<LigneCommande>) session.getAttribute("listeLigneCommande");
		
		listeLigneCommande.clear();
			
		session.setAttribute("listeLigneCommande", listeLigneCommande);
		
	}//END METHODE
	
	
	public void enregistrerPanier() {
		
		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		session.setAttribute("listeLigneCommande", listeLigneCommande);
		
		listeLigneCommande = (List<LigneCommande>) session.getAttribute("listeLigneCommande");
		
		listeLigneCommande.clear();
			
	}//END METHO

	
	// __________ GETTER/SETTER ______________ //
	
	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getProduitID() {
		return produitID;
	}

	public void setProduitID(int produitID) {
		this.produitID = produitID;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public List<LigneCommande> getListeLigneCommande() {
		return listeLigneCommande;
	}

	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
		this.listeLigneCommande = listeLigneCommande;
	}
	
}