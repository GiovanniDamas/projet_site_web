package com.intiformation.GestionAppCommerce.Modele;

public class Role {
	
	//Propriete
	
	private int idRole, idUser;
	private String roleName;
	
	//Constructeur
	
	public Role() {
	}
	
	public Role(int idRole, int idUser, String roleName) {
		this.idRole = idRole;
		this.idUser = idUser;
		this.roleName = roleName;
	}
	
	public Role(int idUser, String roleName) {
		this.idUser = idUser;
		this.roleName = roleName;
	}
	
	//Getter Setter
	
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
