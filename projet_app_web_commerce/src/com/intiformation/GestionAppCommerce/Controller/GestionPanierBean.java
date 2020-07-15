package com.intiformation.GestionAppCommerce.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import com.intiformation.GestionAppCommerce.Modele.LigneCommande;
import com.intiformation.GestionAppCommerce.Modele.Produit;
import com.intiformation.GestionAppCommerce.Service.ILigneCommandeService;
import com.intiformation.GestionAppCommerce.Service.LigneCommandeServiceImpl;

import javafx.beans.binding.MapBinding;

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
	private List<Integer> nombreLigneC;
	private LigneCommande ligneCommande;
	private LigneCommande ligneC;
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

	public void ajouterLigneC(ActionEvent event) {

		LigneCommande ligneC = new LigneCommande();

		setLigneCommande(ligneC);

		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		

		Map params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		String pIdProduit = (String) params.get("produitID");
		String pPrix = (String) params.get("prix");
		
	
		
		int produit = Integer.parseInt(pIdProduit);
		Double prix = Double.parseDouble(pPrix);
		
		
		
		System.out.println(quantite);
		

		ligneC = new LigneCommande(produit, quantite, prix);
		System.out.println(ligneC);

		int index = 1;

		List<LigneCommande> listeLigneC = new ArrayList<>();
		List<Integer> nombreLigneC = new ArrayList<>();

		if (session.getAttribute("listeLigneC") != null) {
			listeLigneC = (List<LigneCommande>) session.getAttribute("listeLigneC");
			nombreLigneC = (List<Integer>) session.getAttribute("nombreLigneC");

			System.out.println(nombreLigneC);

			index = nombreLigneC.size();

			index++;

		}

		listeLigneC.add(ligneC);

		nombreLigneC.add(index);

		System.out.println(listeLigneC);
		System.out.println(nombreLigneC);

		System.out.println(index);

		session.setAttribute("listeLigneC", listeLigneC);
		session.setAttribute("nombreLigneC", nombreLigneC);

	}// END METHODE
	

	public double sommePrix() {

		prixTotal = 0;

		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

		listeLigneC = (List<LigneCommande>) session.getAttribute("listeLigneC");
		System.out.println(listeLigneC);

		for (LigneCommande lc : listeLigneC) {

			prixTotal = prixTotal + lc.getPrix() * lc.getQuantite();

		}

		return prixTotal;

	}// END METHODE

	public void suppLigneC(ActionEvent event) {

		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

		UIParameter uiIndex = (UIParameter) event.getComponent().findComponent("index");
		int index = (int) uiIndex.getValue();


		listeLigneC = (List<LigneCommande>) session.getAttribute("listeLigneC");
		nombreLigneC = (List<Integer>) session.getAttribute("nombreLigneC");

		listeLigneC.remove(index - 1);
		nombreLigneC.remove(nombreLigneC.size() - 1);

		System.out.println(index);

		session.setAttribute("listeLigneC", listeLigneC);
		session.setAttribute("nombreLigneC",nombreLigneC);


	}// END METHODE
	
	public void viderPanier() {
		
		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		listeLigneC = (List<LigneCommande>) session.getAttribute("listeLigneC");
		
		listeLigneC.clear();
			
		session.setAttribute("listeLigneC", listeLigneC);
		
	}//END METHODE

	// __________ GETTER/SETTER ______________ //
	
	
	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public List<LigneCommande> getListeLigneC() {
		return listeLigneC;
	}

	public void setListeLigneC(List<LigneCommande> listeLigneC) {
		this.listeLigneC = listeLigneC;
	}

	public List<Integer> getNombreLigneC() {
		return nombreLigneC;
	}

	public void setNombreLigneC(List<Integer> nombreLigneC) {
		this.nombreLigneC = nombreLigneC;
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

	public LigneCommande getLigneC() {
		return ligneC;
	}

	public void setLigneC(LigneCommande ligneC) {
		this.ligneC = ligneC;
	}

	@Override
	public String toString() {
		return "GestionPanierBean [ligneC=" + ligneC + "]";
	}

}// END CLASS
