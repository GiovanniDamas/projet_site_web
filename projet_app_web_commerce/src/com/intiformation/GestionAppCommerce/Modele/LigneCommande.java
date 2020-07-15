package com.intiformation.GestionAppCommerce.Modele;

public class LigneCommande  {
	
	//Propriete
	
	private int quantite, produitId, commandeId, idPanier;
	private double prix;
	private String ligneC = ""+produitId+commandeId;
	
	//Constructeur
	
	public LigneCommande() {
	}

	public LigneCommande(int quantite, int produitId, int commandeId, double prix, String ligneC) {
		this.quantite = quantite;
		this.produitId = produitId;
		this.commandeId = commandeId;
		this.prix = prix;
		this.ligneC = ligneC;
	}

	public LigneCommande(int quantite, int produitId, int commandeId, int idPanier, double prix) {
		this.quantite = quantite;
		this.produitId = produitId;
		this.commandeId = commandeId;
		this.idPanier = idPanier;
		this.prix = prix;
	}
	
	
	public LigneCommande( int produitId, int quantite, double prix) {
		this.produitId = produitId;
		this.quantite = quantite;
		this.prix = prix;

	}

	//Getter Setter

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getProduitId() {
		return produitId;
	}

	public void setProduitId(int produitId) {
		this.produitId = produitId;
	}

	public int getCommandeId() {
		return commandeId;
	}

	public void setCommandeId(int commandeId) {
		this.commandeId = commandeId;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getLigneC() {
		return ligneC;
	}

	public void setLigneC(String ligneC) {
		this.ligneC = ligneC;
	}

	public int getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(int idPanier) {
		this.idPanier = idPanier;
	}

	@Override
	public String toString() {
		return "LigneCommande [quantite=" + quantite + ", produitId=" + produitId + ", commandeId=" + commandeId
				+ ", idPanier=" + idPanier + ", prix=" + prix + ", ligneC=" + ligneC + "]";
	}
	

}//END CLASS
