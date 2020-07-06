package com.intiformation.GestionAppCommerce.modele;

public class User {
	
	//Propriete
	
	private int idUser;
	private String userName, password;
	private boolean actived;
	
	//Constructeur
	
	public User() {
	}
	
	public User(int idUser, String userName, String password, boolean actived) {
		this.idUser = idUser;
		this.userName = userName;
		this.password = password;
		this.actived = actived;
	}
	
	public User(String userName, String password, boolean actived) {
		this.userName = userName;
		this.password = password;
		this.actived = actived;
	}
	
	//Getter Setter
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}

}
