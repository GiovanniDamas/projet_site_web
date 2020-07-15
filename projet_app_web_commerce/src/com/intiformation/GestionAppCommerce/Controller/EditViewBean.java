package com.intiformation.GestionAppCommerce.Controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import com.intiformation.GestionAppCommerce.Modele.User;
import com.intiformation.GestionAppCommerce.Service.IUserService;
import com.intiformation.GestionAppCommerce.Service.UserServiceImpl;

@ManagedBean(name = "editCellBean")
@ViewScoped
public class EditViewBean {

	IUserService userService = new UserServiceImpl();
	
	private List<User> listeUserBdd = userService.getAll();
	
	private GestionUserBean userBean;

	public void onCellEdit(CellEditEvent event) {

		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		FacesContext context = FacesContext.getCurrentInstance();

		System.out.println(oldValue);
		System.out.println(newValue);

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}// END METHODE

	public List<User> getListeUserBdd() {
		return listeUserBdd;
	}

	public void setListeUserBdd(List<User> listeUserBdd) {
		this.listeUserBdd = listeUserBdd;
	}
	
	public List<String> getRoleName() {
        return userBean.getPRoleName();
    }
	

}
