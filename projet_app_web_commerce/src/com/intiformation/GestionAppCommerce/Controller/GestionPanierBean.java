package com.intiformation.GestionAppCommerce.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.intiformation.GestionAppCommerce.Modele.LigneCommande;
import com.intiformation.GestionAppCommerce.Modele.Produit;
import com.intiformation.GestionAppCommerce.Service.IProduitService;
import com.intiformation.GestionAppCommerce.Service.ProduitServiceImp;



@ManagedBean(name = "panierBean")
@SessionScoped
public class GestionPanierBean implements Serializable {

	private int quantite, produitID;
	private Double prix;
	private List<LigneCommande> listeLigneCommande;
	private LigneCommande ligneCommande;
	
	private IProduitService produitService;

	public GestionPanierBean() {
		produitService = new ProduitServiceImp();
	}

	public void ajouterLigneCommande(ActionEvent event) {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		if (quantite!=0) {
		
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
	}
	

	public double sommePrix() {

		double prixTotal = 0;

		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

		listeLigneCommande = (List<LigneCommande>) session.getAttribute("listeLigneCommande");

		if (listeLigneCommande != null) {

			for (LigneCommande lc : listeLigneCommande) {

				prixTotal = prixTotal + lc.getPrix() * lc.getQuantite();

			}
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

		if (listeLigneCommande.isEmpty()) {

			listeLigneCommande = null;
		}

		session.setAttribute("listeLigneCommande", listeLigneCommande);

	}

	public void viderPanier() {

		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

		listeLigneCommande = (List<LigneCommande>) session.getAttribute("listeLigneCommande");

		listeLigneCommande.clear();

		if (listeLigneCommande.isEmpty()) {

			listeLigneCommande = null;
		}

		session.setAttribute("listeLigneCommande", listeLigneCommande);
		
	}//END METHODE
	
	
	public String enregistrerPanier() {
		
		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		listeLigneCommande = (List<LigneCommande>) session.getAttribute("listeLigneCommande");
		
		List<LigneCommande> listeTrie = listeLigneCommande.stream()
														  .sorted((lc1,lc2)-> lc1.getProduitId() - lc2.getProduitId())
														  .collect(Collectors.toList());
		int testQuantite = 0;
		int nouvelleQuantite;
		int chgtCommande=0;
		Produit produitTest = new Produit();
		LigneCommande ligneCommandeModif;
		
		for (LigneCommande ligneCommande : listeTrie) {

			if (produitTest.getIdProduit()!=(ligneCommande.getProduitId())) {
				testQuantite = 0;
			}
			
			produitTest = produitService.findProduitById(ligneCommande.getProduitId());

			testQuantite = testQuantite + ligneCommande.getQuantite();

			if (testQuantite > produitTest.getQuantite()) {
				
				chgtCommande++;

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Quantit� demand�e indisponible",
						" - La quantit� demand�e est sup�rieure aux stocks");
				context.addMessage(null, message);
				
				ligneCommandeModif = ligneCommande; 
				nouvelleQuantite = produitTest.getQuantite()-(testQuantite-ligneCommande.getQuantite());

				if (nouvelleQuantite <= 0) {
					
					listeLigneCommande.remove(listeLigneCommande.indexOf(ligneCommande));
					
				} else {
					
					ligneCommandeModif.setQuantite(nouvelleQuantite);
					testQuantite=produitTest.getQuantite();
					listeLigneCommande.set(listeLigneCommande.indexOf(ligneCommande), ligneCommandeModif);

				}
				
			}
		}
		
		if (chgtCommande== 0) {
			for (LigneCommande ligneCommande : listeLigneCommande) {
				
				Produit produitToEdit = produitService.findProduitById(ligneCommande.getProduitId());
				
				int quantiteToEdit = produitToEdit.getQuantite()-ligneCommande.getQuantite();
				
				produitService.updateQttAndSelectionne(quantiteToEdit, true, ligneCommande.getProduitId());
			}

			 return "ValidationCommande.xhtml";
			
		}else {
			session.setAttribute("listeLigneCommande", listeLigneCommande);
			return "panier";
		}	
			
	}//
	
	public void detruireLigneCommande() {
		
		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		session.removeAttribute("listeLigneCommande");
		
		listeLigneCommande=null;
		ligneCommande=null;
					
	}//

	

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