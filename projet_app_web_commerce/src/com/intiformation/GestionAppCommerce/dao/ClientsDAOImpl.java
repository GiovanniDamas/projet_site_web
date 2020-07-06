package com.intiformation.GestionAppCommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.intiformation.GestionAppCommerce.modele.Clients;



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

}//END CLASS
