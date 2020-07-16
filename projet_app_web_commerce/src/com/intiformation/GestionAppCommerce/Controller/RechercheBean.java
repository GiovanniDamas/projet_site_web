package com.intiformation.GestionAppCommerce.Controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.intiformation.GestionAppCommerce.Modele.Produit;
import com.intiformation.GestionAppCommerce.Service.IProduitService;
import com.intiformation.GestionAppCommerce.Service.ProduitServiceImp;

@ManagedBean(name="rechercheBean")
@SessionScoped
public class RechercheBean implements Serializable{
	
	private String keywords,nomCategorie;
	private List<Produit> listeFiltreProduit;
	
	private IProduitService produitService;

	public RechercheBean() {
		produitService = new ProduitServiceImp();
	}
	
	
	public List<Produit> getListeSelonFiltre() {
		if((nomCategorie.isEmpty() || nomCategorie==null) && !keywords.isEmpty()) {
			listeFiltreProduit= produitService.findByKeywords(keywords);
		}
		if(keywords.isEmpty() && !nomCategorie.isEmpty()) {
			listeFiltreProduit=produitService.findByCategorie(nomCategorie);
		}
		
		if(!keywords.isEmpty() && !nomCategorie.isEmpty()) {
			listeFiltreProduit = produitService.findByKeywordsAndCategorie(keywords, nomCategorie);
		}
		
		if(keywords.isEmpty() && (nomCategorie.isEmpty()||nomCategorie==null)) {
			listeFiltreProduit = produitService.findAllProduit();
		}
		
		return listeFiltreProduit;
	}
	
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}	

	public String getNomCategorie() {
		return nomCategorie;
	}


	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}


	public List<Produit> getListeFiltreProduit() {
		return listeFiltreProduit;
	}

	public void setListeFiltreProduit(List<Produit> listeFiltreProduit) {
		this.listeFiltreProduit = listeFiltreProduit;
	}

}
