package com.intiformation.GestionAppCommerce.modele;

public class Clients {
	
	//Propriete
	
	private int idClient;
	private String nomClient, adresse,email, tel;
	
	//Constructeur
	
	public Clients() {
	}
	
	public Clients(int idClient, String nomClient, String adresse, String email, String tel) {
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
	}
	
	public Clients(String nomClient, String adresse, String email, String tel) {
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
	}
	
	//Getter Setter
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

}//end class
