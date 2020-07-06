package com.intiformation.GestionAppCommerce.modele;

import java.util.Date;

public class Commande {
	
	//Prorpiete
	
	private int idCommande, idClient;
	private Date dateCommande;
	
	//Constructeur
	
	public Commande() {
	}

	public Commande(int idClient, Date dateCommande) {
		this.idClient = idClient;
		this.dateCommande = dateCommande;
	}

	public Commande(int idCommande, int idClient, Date dateCommande) {
		this.idCommande = idCommande;
		this.idClient = idClient;
		this.dateCommande = dateCommande;
	}
	
	//Getter Setter

	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	
	
	
	
	
	
	
	
	
	
	

}
