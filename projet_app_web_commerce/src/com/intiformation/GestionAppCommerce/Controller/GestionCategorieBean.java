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

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

import org.apache.commons.io.FileUtils;

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

	public void handleFileUpload(FileUploadEvent event) {

		if (categorie.getIdCategorie() == 0) {

			UploadedFile uploadedFile = (UploadedFile) event.getFile();

			String fileName = FilenameUtils.getName(uploadedFile.getFileName());

			// affectation image
			categorie.setPhoto(fileName);

			categorieDAO.add(categorie);

			InputStream imageContent = null;

			try {
				imageContent = uploadedFile.getInputstream();
			} catch (IOException e) {
			} // END CATCH

			String destPath = "/projet_app_web_commerce/WebContent/resources/images/";
			File destFile = new File(destPath);

			try {
				FileUtils.copyInputStreamToFile(imageContent, destFile);
			} catch (IOException ex) {
				// log error
			} // END CATCH
		
		}//END IF

	}// END saveCat

	public String ajouterUser() {

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

	}// END ajouter()

	/**
	 * méthode pour recupérer une categorie par son id
	 */
	public void selectCat(ActionEvent event) {

		UIParameter component = (UIParameter) event.getComponent().findComponent("updateID");
		int idCat = (int) component.getValue();

		Categorie categorie = categorieDAO.getById(idCat);

		setCategorie(categorie);

	}// END selectCat

	/**
	 * Méthode pour supprimer une catégorie
	 * 
	 * @param event
	 */
	public void suppCat(ActionEvent event) {

		UIParameter component = (UIParameter) event.getComponent().findComponent("deleteID");
		int idCat = (int) component.getValue();

		categorieDAO.delete(idCat);

	}// END suppCat

	/**
	 * methode pour modifier une catégorie
	 * 
	 * @return
	 */
	public String modifierCat() {

		FacesContext context = FacesContext.getCurrentInstance();

		if (categorieDAO.update(categorie)) {

			// if modif ok //
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification d'une catégorie",
					" - La catégorie à bien été modifiée "));

			return "gestionCategories.xhtml";

		} else {
			// ajout not ok//
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Modification une catégorie",
					" - La modification de la catégorie à échouée ! "));

			return "modifierCategories.xhtml";

		} // END ELSE

	}// END modifierCat

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
