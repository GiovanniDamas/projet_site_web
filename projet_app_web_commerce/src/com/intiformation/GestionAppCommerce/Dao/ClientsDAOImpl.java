package com.intiformation.GestionAppCommerce.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.intiformation.GestionAppCommerce.Modele.Clients;
import com.intiformation.GestionAppCommerce.Modele.LigneCommande;
import com.mysql.jdbc.Statement;



/**
 * Class Client pour la couche DAO qui implémente l'interface IClientDAO
 * @author giovanni
 *
 */
public class ClientsDAOImpl implements IClientDAO{

	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public boolean add(Clients pClient) {
		
		try {
			// def de la requête
			ps = this.connection.prepareStatement("INSERT INTO clients (nomClient,adresse,email,telephone) VALUES (?,?,?,?)");

			// def des param
			ps.setString(1, pClient.getNomClient());
			ps.setString(2, pClient.getAdresse());
			ps.setString(3, pClient.getEmail());
			ps.setString(4, pClient.getTel());


			// envoi requete
			int verifAdd = ps.executeUpdate();

			// verif
			return verifAdd == 1;

		} catch (Exception e) {
			System.out.println("... add() : Erreur lors de l'ajout d'un client dans ClientDAOImpl ");
		} finally {
			try {
				ps.close();
			} catch (Exception e) {

			} // END CATCH
		} // END FINALLY
		return false;
		
	}

	@Override
	public boolean update(Clients pClient) {
		
		try {
			// def de la requête
			ps = this.connection.prepareStatement("UPDATE clients SET nomClient =? ,adresse=? ,email=? ,telephone=?");

			// def des param
			ps.setString(1, pClient.getNomClient());
			ps.setString(2, pClient.getAdresse());
			ps.setString(3, pClient.getEmail());
			ps.setString(4, pClient.getTel());


			// envoi requete
			int verifAdd = ps.executeUpdate();

			// verif
			return verifAdd == 1;

		} catch (Exception e) {
			System.out.println("... update() : Erreur lors de la modification d'un client dans ClientDAOImpl ");
		} finally {
			try {
				ps.close();
			} catch (Exception e) {

			} // END CATCH
		} // END FINALLY
		return false;
	}

	@Override
	public boolean delete(Integer pIdClient) {
		
		try {
			// envoie requete
			ps = this.connection.prepareStatement("DELETE FROM clients WHERE idClient = ?");

			// def des param
			ps.setInt(1, pIdClient);

			// envoi requete
			int verifDelete = ps.executeUpdate();

			// verif
			return verifDelete == 1;

		} catch (Exception e) {
			System.out.println("... delete() : Erreur lors de la suppression d'un client dans ClientDAOImpl ");
		} finally {
			try {
				ps.close();
			} catch (Exception e) {

			} // END CATCH
		} // END FINALLY
		return false;
	}

	@Override
	public List<Clients> getAll() {
		return null;
	}

	@Override
	public Clients getById(Integer id) {
		return null;
	}

	@Override
	public int validationClientCommande(Clients pClient, List<LigneCommande> listeLC) {
		
		try {
			//Ajout Client
			ps = this.connection.prepareStatement("INSERT INTO clients (nomClient,adresse,email,telephone) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, pClient.getNomClient());
			ps.setString(2, pClient.getAdresse());
			ps.setString(3, pClient.getEmail());
			ps.setString(4, pClient.getTel());

			int verifAddClient = ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			rs.next();
			int generatedKey = rs.getInt(1);
			
			//Ajout Commande
			ps = this.connection.prepareStatement("insert into commande (dateCommande,clientID) values (curdate(),?);",Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, generatedKey);
			int verifAddCommande = ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			rs.next();
			generatedKey = rs.getInt(1);
			
			//Ajout Ligne de commande
			int verifAddLC =0;
			for (LigneCommande lc : listeLC) {
				ps = this.connection.prepareStatement("insert into ligneCommande (quantite, prix, produitID, commandeID) values (?,?,?,?);");
				ps.setInt(1, lc.getQuantite());
				ps.setDouble(2, lc.getPrix());
				ps.setInt(3, lc.getProduitId());
				ps.setInt(4, generatedKey);
				verifAddLC = verifAddLC + ps.executeUpdate();
			}
			return generatedKey;

		} catch (Exception e) {
			System.out.println("... Erreur lors de la validation commande add client/commande/lignecommande ");
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (Exception e) {
			} 
		} 
		return 0;
	}//end validation commande

}//END CLASS
