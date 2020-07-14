package com.intiformation.GestionAppCommerce.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import com.intiformation.GestionAppCommerce.Modele.LigneCommande;
import com.intiformation.GestionAppCommerce.Modele.Produit;
import com.intiformation.GestionAppCommerce.Service.ILigneCommandeService;
import com.intiformation.GestionAppCommerce.Service.LigneCommandeServiceImpl;

/**
 * ManagedBean pour la gestion des lignes de commandes
 * 
 * @author giovanni
 *
 */
@ManagedBean(name = "panierBean")
@SessionScoped
public class GestionPanierBean implements Serializable {

	private List<LigneCommande> listeLigneC;
	private LigneCommande ligneCommande;
	private Produit produit;
	private int quantite;
	private double prixTotal;

	// Service
	ILigneCommandeService ligneCommandeService;

	public GestionPanierBean() {
		ligneCommandeService = new LigneCommandeServiceImpl();
	}

	public List<LigneCommande> findAllLigneC() {

		listeLigneC = ligneCommandeService.getAll();

		return listeLigneC;
	}

	public void ajouterLigneC() {

		LigneCommande ligneC = new LigneCommande();

		setLigneCommande(ligneC);

		
		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

		Map params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		String pIdProduit = (String) params.get("produitID");
		String pPrix = (String) params.get("prix");

		int produit = Integer.parseInt(pIdProduit);
		Double prix = Double.parseDouble(pPrix);

		System.out.println(produit);
		System.out.println(prix);
		System.out.println(quantite);

		ligneC = new LigneCommande(produit, quantite, prix);

		System.out.println(ligneC);
		
		
		List<LigneCommande> listeLigneC = new ArrayList<>();
		
		if (session.getAttribute("listeLigneC") != null) {
			listeLigneC = (List<LigneCommande>) session.getAttribute("listeLigneC");	
		}
		
		listeLigneC.add(ligneC);
		
		
		session.setAttribute("listeLigneC", listeLigneC);

	}// END METHODE
	
	
	public double sommePrix() {
		
		prixTotal = 0;
		
		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		listeLigneC =  (List<LigneCommande>) session.getAttribute("listeLigneC");
		
		for (LigneCommande lc : listeLigneC) {
			
			prixTotal = prixTotal + lc.getPrix();	
				
		}
		
		return prixTotal;
		
	}//END METHODE
	
	

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

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}// END CLASS
