package com.intiformation.GestionAppCommerce.Controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.intiformation.GestionAppCommerce.Modele.LigneCommande;
import com.intiformation.GestionAppCommerce.Modele.Produit;
import com.intiformation.GestionAppCommerce.Service.ILigneCommandeService;
import com.intiformation.GestionAppCommerce.Service.LigneCommandeServiceImpl;

/**
 * ManagedBean pour la gestion des lignes de commandes
 * @author giovanni
 *
 */
@ManagedBean(name = "panierBean")
@SessionScoped
public class GestionPanierBean implements Serializable {

	private List<LigneCommande> listeLigneC;
	private LigneCommande ligneCommande;
	private Produit produit;
	
	//Service
	ILigneCommandeService ligneCommandeService;
	
	public GestionPanierBean() {
		ligneCommandeService = new LigneCommandeServiceImpl();
	}
	
	public List<LigneCommande> findAllLigneC() {
		
		listeLigneC = ligneCommandeService.getAll();
		
		return listeLigneC;	
	}
	

	
	public void ajouterLigneC(ActionEvent event) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		UIParameter uiProduitID = (UIParameter) event.getComponent().findComponent("produitID");
		UIParameter uPrix = (UIParameter) event.getComponent().findComponent("prix");
		UIParameter uQuantite = (UIParameter) event.getComponent().findComponent("qtt");
		
		int idProduit = (int) uiProduitID.getValue();
		Double prix = (Double) uPrix.getValue();
		int quantite = (int) uQuantite.getValue();
		
		System.out.println(idProduit);
		System.out.println(prix);
		System.out.println(quantite);
		
		
		LigneCommande ligneC = new LigneCommande(ligneCommande.getQuantite(), produit.getIdProduit(), produit.getPrix());
		
		
		session.setAttribute("ligneCommande", ligneC);
		
	}
	
	// __________ GETTER/SETTER ______________ //
	
	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	
}//END CLASS
