package com.intiformation.GestionAppCommerce.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

import org.apache.commons.io.FileUtils;

import com.intiformation.GestionAppCommerce.Dao.CategorieDAOImp;
import com.intiformation.GestionAppCommerce.Dao.ICategorieDAO;
import com.intiformation.GestionAppCommerce.Modele.Categorie;
import com.intiformation.GestionAppCommerce.Service.CategorieServiceImp;
import com.intiformation.GestionAppCommerce.Service.ICategorieService;

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
	private List<String> listeNomCat;
	private Categorie categorie;
	
	private Part uploadedFile;

	// DAO
	private ICategorieService categorieService;

	//////// CTOR
	/**
	 * ctor vide
	 */
	public GestionCategorieBean() {
		categorieService = new CategorieServiceImp();
	}

	// methodes

	public List<Categorie> findAllCat() {
		listeCatBdd = categorieService.findAllCategorie();

		return listeCatBdd;
	}// END findAllCat

	public List<String> findNomCat() {
		listeCatBdd = categorieService.findAllCategorie();

		for (Categorie cat : listeCatBdd) {
			listeNomCat = new ArrayList<>();
			listeNomCat.add(cat.getNomCategorie());
		}

		return listeNomCat;
	}// END findAllCat

	public void initialiserCat(ActionEvent event) {

		// instantiation nouvel objet
		Categorie categorie = new Categorie();

		// Affectation nouvel cat au formulaire ajout
		setCategorie(categorie);

	}// END initialiser

	public String ajouter() {

		FacesContext context = FacesContext.getCurrentInstance();

		if (categorie.getIdCategorie() == 0) {

			try {
				String fileName = uploadedFile.getSubmittedFileName();
				categorie.setPhoto(fileName);

				InputStream imageContent = uploadedFile.getInputStream();

				File targetFile = new File(
						"/Users/giovanni/Desktop/FormationJAVA/projet_site_web/projet_app_web_commerce/WebContent/resources/images",
						fileName);

				OutputStream outStream = new FileOutputStream(targetFile);
				byte[] buf = new byte[1024];
				int len;

				while ((len = imageContent.read(buf)) > 0) {
					outStream.write(buf, 0, len);
				}

				outStream.close();

			} catch (IOException ex) {
				System.out.println("erreur dans creation image");
			}
		}

		boolean verifAdd = categorieService.ajouterCategorie(categorie);

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

		Categorie categorie = categorieService.findCategorieById(idCat);

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

		categorieService.supprimerCategorie(idCat);

	}// END suppCat

	/**
	 * methode pour modifier une catégorie
	 * 
	 * @return
	 */
	public String modifierCat() {

		FacesContext context = FacesContext.getCurrentInstance();

		if (categorieService.modifierCategorie(categorie)) {

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

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	

}// END CLASS
