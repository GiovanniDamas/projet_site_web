package com.intiformation.GestionAppCommerce.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;


import com.intiformation.GestionAppCommerce.Dao.CategorieDAOImp;
import com.intiformation.GestionAppCommerce.Dao.ICategorieDAO;
import com.intiformation.GestionAppCommerce.Modele.Categorie;

/**
 * managedBean pour la gestion des catégories
 * 
 * @author giovanni
 *
 */
@ManagedBean(name = "categorieBean")
@SessionScoped
public class GestionCategorieBean implements Serializable {

	//////// PROPS
	private List<Categorie> listeCatBdd;
	private Categorie categorie;

	private UploadedFile uploadedFile;

	// DAO
	private ICategorieDAO categorieDAO;

	//////// CTOR
	/**
	 * ctor vide
	 */
	public GestionCategorieBean() {
		categorieDAO = new CategorieDAOImp();
	}

	// methodes
	public List<Categorie> findAllCat() {
		listeCatBdd = categorieDAO.getAll();

		return listeCatBdd;
	}// END findAllCat

	public void initialiserCat(ActionEvent event) {

		// instantiation nouvel objet
		this.categorie = new Categorie();

		// Affectation nouvel cat au formulaire ajout
		setCategorie(categorie);
	}// END initialiser

	public void saveCat() {

		FacesContext context = FacesContext.getCurrentInstance();
		
		// CAS AJOUT CATEGORIE
		if(uploadedFile != null) {

			try {
				String fileName = FilenameUtils.getName(uploadedFile.getFileName());

				// affectation image
				categorie.setPhoto(fileName);

				InputStream input = uploadedFile.getInputstream();
				String filePath = "/projet_app_web_commerce/WebContent/resources/images/";
				
				File targetFile = new File(filePath, fileName);

				
				// instanciation du flux de sortie vers le fichier image
				OutputStream output = new FileOutputStream(targetFile);

				byte[] buf = new byte[1024];
				int len;

				while ((len = input.read(buf)) > 0) {
					output.write(buf, 0, len);
				}

				output.close();

			} catch (Exception e) {
				Logger.getLogger(GestionCategorieBean.class.getName()).log(Level.SEVERE, null, e);

			} // END CATCH

		} // END IF

	}// END saveCat

	public String ajouter() {

		FacesContext context = FacesContext.getCurrentInstance();

		boolean verifAdd = categorieDAO.add(categorie);

		if (verifAdd) {
			// ajout ok//
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajouter une nouvelle catégorie",
					" - La nouvelle catégorie à bien été ajoutée "));

			return "gestionCategories.xhtml";

		} else {
			// ajout not ok//
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ajouter une nouvelle catégorie",
					" - L'ajout de la nouvelle catégorie à échouée ! "));

			return "ajouterCategories.xhtml";
		} // END ELSE
		
	}//END ajouter()
	
	public void suppCat(ActionEvent event) {
		
		UIParameter component = (UIParameter) event.getComponent().findComponent("deleteID");
		int idCat = (int) component.getValue();
		
		categorieDAO.delete(idCat);
	
	}//END suppCat
	
	

	///////// getter/setters ///////////////////
	
	public Categorie getCategorie() {
		if (categorie == null) {
			categorie = new Categorie();
		}
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

}// END CLASS
