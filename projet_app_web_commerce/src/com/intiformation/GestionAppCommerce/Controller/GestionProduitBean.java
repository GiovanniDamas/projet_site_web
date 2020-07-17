package com.intiformation.GestionAppCommerce.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import com.intiformation.GestionAppCommerce.Modele.Produit;
import com.intiformation.GestionAppCommerce.Service.IProduitService;
import com.intiformation.GestionAppCommerce.Service.ProduitServiceImp;

@ManagedBean(name = "produitBean")
@SessionScoped
public class GestionProduitBean implements Serializable {

	/* __________________ props ____________________ */
	private List<Produit> listeProduitBDD;
	private List<Integer> listeQuantite;
	private Produit produit;
	private Part uploadedFile;
	private IProduitService produitService;

	/* __________________ ctor _____________________ */
	public GestionProduitBean() {
		produitService = new ProduitServiceImp();
	}

	/* __________________ methodes _________________ */

	public List<Produit> findAllProduit() {

		listeProduitBDD = produitService.findAllProduit();
		return listeProduitBDD;
	}

	public void supprimerProduit(ActionEvent event) {

		UIParameter uip = (UIParameter) event.getComponent().findComponent("deleteID");

		int produitID = (int) uip.getValue();

		FacesContext context = FacesContext.getCurrentInstance();

		if (produitService.supprimerProduit(produitID)) {

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression du produit",
					" - Le produit a �t� supprim� avec succ�s"));
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Suppression du produit",
					" - La suppression du produit a �chou�"));
		}

	}// end supprimerProduit()

	public void selectionnerProduit(ActionEvent event) {

		UIParameter uip = (UIParameter) event.getComponent().findComponent("updateID");

		int produitID = (int) uip.getValue();

		Produit produitAModifier = produitService.findProduitById(produitID);

		setProduit(produitAModifier);
	}// end selectionnerProduit()

	public void modifierProduit(ActionEvent event) {

		FacesContext context = FacesContext.getCurrentInstance();

		if (uploadedFile != null) {

			String fileNameToUpdate = uploadedFile.getSubmittedFileName();

			if (!"".equals(fileNameToUpdate) && fileNameToUpdate != null) {

				// affectation du nouveau nom à la prop urlImage du livre
				produit.setPhoto(fileNameToUpdate);

				try {
					InputStream imageContent = uploadedFile.getInputStream();

					String pathTmp = context.getExternalContext().getInitParameter("file-upload");

					String filePath = context.getExternalContext().getRealPath(pathTmp);

					File targetFile = new File(filePath, fileNameToUpdate);

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
		}
		if (produitService.modifierProduit(produit)) {

			FacesMessage messageOk = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification r�ussie",
					" - Le produit a �t� modifi� avec succ�s");

			context.addMessage(null, messageOk);

		} else {
			FacesMessage messageNotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de la modification",
					" - La modification du produit a �chou�");

			context.addMessage(null, messageNotOk);
		}

	}// end modifierProduit()

	public void initialiserProduit(ActionEvent event) {

		Produit produitToAdd = new Produit();
		setProduit(produitToAdd);

	}// end initialiserLivre

	public String ajouterProduit() {

		FacesContext context = FacesContext.getCurrentInstance();

		if (produit.getIdProduit() == 0) {

			try {
				String fileName = uploadedFile.getSubmittedFileName();
				produit.setPhoto(fileName);

				InputStream imageContent = uploadedFile.getInputStream();

				String pathTmp = context.getExternalContext().getInitParameter("file-upload");

				String filePath = context.getExternalContext().getRealPath(pathTmp);

				File targetFile = new File(filePath, fileName);

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

		boolean verifAjout = produitService.ajouterProduit(produit);

		if (verifAjout) {

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajout d'un nouveau produit",
					" - Le produit a été ajouté avec succés"));

			return "GestionProduit.xhtml";

		} else {

			FacesMessage messageAddNotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ajout d'un nouveau produit",
					" - L'ajout du produit a échoué");

			return "editerProduit.xhtml";
		}

	}// end ajouterProduit()

	public List<Integer> getListeQuantite(int idProduit) {
		listeQuantite = produitService.getListeQuantite(idProduit);
		return listeQuantite;
	}

	// Getter Setter

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}// end class
