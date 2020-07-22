package com.intiformation.GestionAppCommerce.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.GestionAppCommerce.Modele.LigneCommande;

/**
 * Classe implémentant l'interface ILigneCommandeDAO pour implémenter les
 * fonctionnalités
 * 
 * @author giovanni
 *
 */
public class LigneCommandeDAOImpl implements ILigneCommandeDAO {

	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public boolean add(LigneCommande ligne) {

		try {
			// Déclaration requete
			ps = this.connection.prepareStatement(
					"INSERT INTO ligneCommande(quantite,prix,produitID,commandeID,idPanier) VALUES (?,?,?,?,?)");

			// passage param
			ps.setInt(1, ligne.getQuantite());
			ps.setDouble(2, ligne.getPrix());
			ps.setInt(3, ligne.getProduitId());
			ps.setInt(4, ligne.getCommandeId());
			ps.setInt(5, ligne.getIdPanier());

			int verifajout = ps.executeUpdate();

			return verifajout == 1;
		} catch (SQLException e) {
			System.out.println("... Erreur lors de l'ajout de la ligne de commande dans la DAO");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // end try/catch

		} // end finally

		return false;
	}// END METHODE

	@Override
	public boolean update(LigneCommande t) {
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		return false;
	}

	@Override
	public List<LigneCommande> getAll() {

		try {
			// définition requete
			ps = this.connection.prepareStatement("SELECT nomProduit, lc.quantite, p.prix\n"
					+ "FROM produit as p, ligneCommande as lc\n" + "WHERE p.idProduit = lc.produitID");

			rs = ps.executeQuery();
			LigneCommande ligneCommande = new LigneCommande();
			List<LigneCommande> listeLigneC = new ArrayList<>();

			while (rs.next()) {
				ligneCommande = new LigneCommande(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4),
						rs.getString(5));
				listeLigneC.add(ligneCommande);

			} // end while

			return listeLigneC;

		} catch (SQLException e) {
			System.out.println("Erreur lors de la récupération de la liste des lignes de commandes");
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public LigneCommande getByIdCP(LigneCommande LigneC) {
		try {

			ps = this.connection.prepareStatement("SELECT * FROM ligneCommande WHERE produitID = ? AND commandeID = ?");

			ps.setInt(1, LigneC.getProduitId());
			ps.setInt(1, LigneC.getCommandeId());

			rs = ps.executeQuery();

			LigneCommande ligneCommande = null;

			rs.next();
			
			ligneCommande = new LigneCommande(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5));

			return ligneCommande;

		} catch (SQLException e) {
			System.out.println("Erreur lors de la récupération de la ligne de commande GetById");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // end try/catch

		} // end finally
		return null;
	}

	@Override
	public LigneCommande getById(Integer id) {
		return null;
	}

	@Override
	public List<LigneCommande> getByIdCommande(int idCommande) {
		try {

			ps = this.connection.prepareStatement("SELECT lc.produitID, lc.quantite, lc.prix FROM ligneCommande lc where commandeID = ?");

			ps.setInt(1, idCommande);

			rs = ps.executeQuery();

			LigneCommande ligneCommande = new LigneCommande();
			List<LigneCommande> listeLigneC = new ArrayList<>();

			while (rs.next()) {
				ligneCommande = new LigneCommande(rs.getInt(1), rs.getInt(2), rs.getDouble(3));
				
				listeLigneC.add(ligneCommande);

			} // end while

			return listeLigneC;

		} catch (SQLException e) {
			System.out.println("Erreur lors de la récupération de la liste de ligne de commande GetById");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)rs.close();
				if (ps != null)ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // end try/catch

		} // end finally
		return null;
	}

}// END CLASS
