package com.intiformation.GestionAppCommerce.Controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.intiformation.GestionAppCommerce.Modele.Categorie;
import com.intiformation.GestionAppCommerce.Modele.Produit;
import com.intiformation.GestionAppCommerce.Modele.User;


@ManagedBean(name = "FiltreBean")
@SessionScoped
public class FiltreDataTableBean implements Serializable{
	
	private List<Produit> filteredProduit;
    private List<Categorie> filteredCategorie;
    private List<User> filteredUser;
    
   
	public FiltreDataTableBean() {
	}
	
	public List<Produit> getFilteredProduit() {
		return filteredProduit;
	}
	public void setFilteredProduit(List<Produit> filteredProduit) {
		this.filteredProduit = filteredProduit;
	}
	public List<Categorie> getFilteredCategorie() {
		return filteredCategorie;
	}
	public void setFilteredCategorie(List<Categorie> filteredCategorie) {
		this.filteredCategorie = filteredCategorie;
	}

	public List<User> getFilteredUser() {
		return filteredUser;
	}

	public void setFilteredUser(List<User> filteredUser) {
		this.filteredUser = filteredUser;
	}
    
    

}
