package com.intiformation.GestionAppCommerce.Controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;

import com.intiformation.GestionAppCommerce.Dao.IUserDAO;
import com.intiformation.GestionAppCommerce.Dao.UserDAOImpl;
import com.intiformation.GestionAppCommerce.Modele.Role;
import com.intiformation.GestionAppCommerce.Modele.User;

/**
 * ManagedBean pour la gestion des user
 * 
 * @author giovanni
 *
 */
@ManagedBean(name = "roleBean")
@SessionScoped
public class GestionRoleBean implements Serializable {

	private Role roleName;
	private User user;
	
	private String[] role = { "AdminCat", "AdminProd" };
	
	//DAO
	private IUserDAO userDAO;
	
	
	/*___________________ CTOR ______________*/
	/**
	 * ctor vide
	 */
	public GestionRoleBean() {
		userDAO = new UserDAOImpl();
	}
	
	// __________________ METHODE _______________ //
	
	public boolean updateRole() {

		return userDAO.updateRole(user.getRoleName(), user.getIdUser());
	}

	public void onCellEdit(CellEditEvent event) {

		String oldValue = (String) event.getOldValue();
		String newValue = (String) event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable table = (DataTable) event.getSource();
			String roleName = (String) table.getRowData();

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	// _______________ GETTER/SETTER ______________ //
	
	public Role getRoleName() {
		return roleName;
	}

	public void setRole(Role roleName) {
		this.roleName = roleName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<String> getRole() {
		return Arrays.asList(role);
	}

	public void setRole(String[] role) {
		this.role = role;
	}
	
	

}//END CLASS
