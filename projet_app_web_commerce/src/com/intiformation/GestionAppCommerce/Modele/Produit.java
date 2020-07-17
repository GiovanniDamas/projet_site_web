package com.intiformation.GestionAppCommerce.Modele;

public class Produit {
	
	//Propri�t�s
	private int idProduit, quantite, idCategorie;
	private String nomProduit, description, photo;
	private double prix;
	private boolean selectionne;
	
	
	//Constructeurs
	
	public Produit() {
	}


	public Produit(int idProduit, int quantite, int idCategorie, String nomProduit, String description, String photo,
			double prix, boolean selectionne) {
		this.idProduit = idProduit;
		this.quantite = quantite;
		this.idCategorie = idCategorie;
		this.nomProduit = nomProduit;
		this.description = description;
		this.photo = photo;
		this.prix = prix;
		this.selectionne = selectionne;
	}


	public Produit(int quantite, int idCategorie, String nomProduit, String description, String photo, double prix,
			boolean selectionne) {
		super();
		this.quantite = quantite;
		this.idCategorie = idCategorie;
		this.nomProduit = nomProduit;
		this.description = description;
		this.photo = photo;
		this.prix = prix;
		this.selectionne = selectionne;
	}

	
	//Getter Setter

	public int getIdProduit() {
		return idProduit;
	}


	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	public int getIdCategorie() {
		return idCategorie;
	}


	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}


	public String getNomProduit() {
		return nomProduit;
	}


	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public boolean isSelectionne() {
		return selectionne;
	}


	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}


	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", quantite=" + quantite + ", idCategorie=" + idCategorie
				+ ", nomProduit=" + nomProduit + ", description=" + description + ", photo=" + photo + ", prix=" + prix
				+ ", selectionne=" + selectionne + "]";
	}
	
	
	

}//end class
