package com.intiformation.GestionAppCommerce.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.GestionAppCommerce.Modele.Produit;

public class ProduitDAOImp implements IProduitDAO{
	
	PreparedStatement ps = null;
	ResultSet rs =null;

	@Override
	public boolean add(Produit produit) {
		try {
			String requeteAddProduit = "insert into produit (nomProduit,description,prix,quantite,selectionner,photo,categorieID) values (?,?,?,?,?,?,?);";
			ps = this.connection.prepareStatement(requeteAddProduit);
			
			ps.setString(1, produit.getNomProduit());
			ps.setString(2, produit.getDescription());
			ps.setDouble(3, produit.getPrix());
			ps.setInt(4, produit.getQuantite());
			ps.setBoolean(5, produit.isSelectionne());
			ps.setString(6, produit.getPhoto());
			ps.setInt(7, produit.getIdCategorie());
					
			
			int verifAddCategorie = ps.executeUpdate();
			
			return verifAddCategorie==1;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de l'ajout d'un produit");
			e.printStackTrace();
		}finally {
				try {
					if (ps!=null)ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}//end try/catch
				
		}//end finally
		return false;
	}

	@Override
	public boolean update(Produit produit) {
		try {
			String requeteUpdateProduit = "update produit set nomProduit=?,description=?,prix=?,quantite=?,selectionner=?,photo=?,categorieID=? where idProduit=?;";
			ps = this.connection.prepareStatement(requeteUpdateProduit);
			
			ps.setString(1, produit.getNomProduit());
			ps.setString(2, produit.getDescription());
			ps.setDouble(3, produit.getPrix());
			ps.setInt(4, produit.getQuantite());
			ps.setBoolean(5, produit.isSelectionne());
			ps.setString(6, produit.getPhoto());
			ps.setInt(7, produit.getIdCategorie());
			ps.setInt(8, produit.getIdProduit());
					
			
			int verifUpdateCategorie = ps.executeUpdate();
			
			return verifUpdateCategorie==1;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de l'update d'un produit");
			e.printStackTrace();
		}finally {
				try {
					if (ps!=null)ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}//end try/catch
				
		}//end finally
		return false;
	}

	@Override
	public boolean delete(Integer idProduit) {
		try {
			String requeteUpdateProduit = "delete from produit where idProduit=?;";
			ps = this.connection.prepareStatement(requeteUpdateProduit);
			
			ps.setInt(1, idProduit);
					
			int verifDeleteCategorie = ps.executeUpdate();
			
			return verifDeleteCategorie==1;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors du delete d'un produit");
			e.printStackTrace();
		}finally {
				try {
					if (ps!=null)ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}//end try/catch
				
		}//end finally
		return false;
	}

	@Override
	public List<Produit> getAll() {
		try {
			String requeteGetAllProduit = "select idProduit,quantite,categorieID,nomProduit, description,photo,prix,selectionner from produit;";
			ps = this.connection.prepareStatement(requeteGetAllProduit);
			
			rs = ps.executeQuery();
			Produit produit = null;
			List<Produit> listeP =new ArrayList<>();
			
			while (rs.next()) {
				produit = new Produit(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getBoolean(8));
				listeP.add(produit);
			}
			
			return listeP;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de la recuperation de la liste des produits");
			e.printStackTrace();
		}finally {
				try {
					if (rs!=null)rs.close();
					if (ps!=null)ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}//end try/catch
				
		}//end finally
		return null;
	}

	@Override
	public Produit getById(Integer idProduit) {
		try {
			String requeteGetByIdProduit = "select idProduit,quantite,categorieID,nomProduit, description,photo,prix,selectionner from produit where idProduit=?;";
			ps = this.connection.prepareStatement(requeteGetByIdProduit);
			ps.setInt(1, idProduit);
			
			rs = ps.executeQuery();
			Produit produit = null;

			rs.next();
			produit = new Produit(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getBoolean(8));
			
			return produit;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de la r�cuperation du produit GetById");
			e.printStackTrace();
		}finally {
				try {
					if (rs!=null)rs.close();
					if (ps!=null)ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}//end try/catch
				
		}//end finally
		return null;
	}

	@Override
	public List<Produit> getByCategorie(String nomCategorie) {
		try {
			String requeteGetByCategorie = "select p.idProduit,p.quantite,p.categorieID,p.nomProduit, p.description,p.photo,p.prix,p.selectionner from produit p , categorie c where p.categorieID=c.idCategorie and c.nomCategorie=?";
			ps = this.connection.prepareStatement(requeteGetByCategorie);
			ps.setString(1, nomCategorie);
			
			rs = ps.executeQuery();
			Produit produit = null;
			List<Produit> listeP = new ArrayList<>();

			while(rs.next()) {
				produit = new Produit(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getBoolean(8));
				listeP.add(produit);
			}
			return listeP;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de la r�cuperation du produit getByCategorie");
			e.printStackTrace();
		}finally {
				try {
					if (rs!=null)rs.close();
					if (ps!=null)ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}//end try/catch
				
		}//end finally
		return null;
	}

	@Override
	public List<Produit> getByKeywords(String motsCles) {
		try {
			String requeteGetByCategorie = "select idProduit,quantite,categorieID,nomProduit, description,photo,prix,selectionner from produit where description regexp ?;";
			ps = this.connection.prepareStatement(requeteGetByCategorie);
			ps.setString(1, motsCles);
			
			rs = ps.executeQuery();
			Produit produit = null;
			List<Produit> listeP = new ArrayList<>();

			while(rs.next()) {
				produit = new Produit(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getBoolean(8));
				listeP.add(produit);
			}
			return listeP;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de la r�cuperation du produit getByKeywords");
			e.printStackTrace();
		}finally {
				try {
					if (rs!=null)rs.close();
					if (ps!=null)ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}//end try/catch
				
		}//end finally
		return null;
	}

	@Override
	public List<Produit> getBySelectionne() {
		try {
			String requeteGetByCategorie = "select idProduit,quantite,categorieID,nomProduit, description,photo,prix,selectionner from produit where selectionner=1;";
			ps = this.connection.prepareStatement(requeteGetByCategorie);
			
			rs = ps.executeQuery();
			Produit produit = null;
			List<Produit> listeP = new ArrayList<>();

			while(rs.next()) {
				produit = new Produit(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getBoolean(8));
				listeP.add(produit);
			}
			return listeP;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de la r�cuperation du produit getBySelectionne");
			e.printStackTrace();
		}finally {
				try {
					if (rs!=null)rs.close();
					if (ps!=null)ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}//end try/catch
				
		}//end finally
		return null;
	}

	@Override
	public List<Produit> getByKeywordsAndCategorie(String motsCles, String nomCategorie) {
		try {
			String requeteGetByCategorie = "select p.idProduit,p.quantite,p.categorieID,p.nomProduit, p.description,p.photo,p.prix,p.selectionner from produit p, categorie c where p.description regexp ? and (p.categorieID=c.idCategorie and c.nomCategorie=?);";
			ps = this.connection.prepareStatement(requeteGetByCategorie);
			ps.setString(1, motsCles);
			ps.setString(2, nomCategorie);
			
			
			rs = ps.executeQuery();
			Produit produit = null;
			List<Produit> listeP = new ArrayList<>();

			while(rs.next()) {
				produit = new Produit(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getBoolean(8));
				listeP.add(produit);
			}
			return listeP;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de la r�cuperation du produit getBySelectionne");
			e.printStackTrace();
		}finally {
				try {
					if (rs!=null)rs.close();
					if (ps!=null)ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}//end try/catch
				
		}//end finally
		return null;
	}
	
}
