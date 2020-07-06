package com.intiformation.GestionAppCommerce.modele;

public class Categorie {
	
	//Propriete
	
	private int idCategorie;
	private String nomCategorie, photo, description;// photo a voir ?
	
	//Constructeur
	
	public Categorie() {
	}
	
	
	public Categorie(int idCategorie, String nomCategorie, String photo, String description) {
		this.idCategorie = idCategorie;
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}

	public Categorie(String nomCategorie, String photo, String description) {
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}

	//Getter Setter

	public int getIdCategorie() {
		return idCategorie;
	}


	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}


	public String getNomCategorie() {
		return nomCategorie;
	}


	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

}
