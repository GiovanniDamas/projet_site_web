package com.intiformation.GestionAppCommerce.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.GestionAppCommerce.Modele.Categorie;

public class CategorieDAOImp implements ICategorieDAO{
	
	PreparedStatement ps = null;
	ResultSet rs =null;

	@Override
	public boolean add(Categorie categorie) {

		try {
			String requeteAddCategorie = "insert into categorie (nomCategorie,photo,description) values (?,?,?);";
			ps = this.connection.prepareStatement(requeteAddCategorie);
			
			ps.setString(1, categorie.getNomCategorie());
			ps.setString(2, categorie.getPhoto());
			ps.setString(3, categorie.getDescription());
			
			int verifAddCategorie = ps.executeUpdate();
			
			return verifAddCategorie==1;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de l'ajout d'une cat�gorie");
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
	public boolean update(Categorie categorie) {
		try {
			String requeteUpdateCategorie = "update categorie set nomCategorie=? , photo=? , description=? where idCategorie=?;";
			ps = this.connection.prepareStatement(requeteUpdateCategorie);
			
			ps.setString(1, categorie.getNomCategorie());
			ps.setString(2, categorie.getPhoto());
			ps.setString(3, categorie.getDescription());
			ps.setInt(4, categorie.getIdCategorie());
			
			int verifAddCategorie = ps.executeUpdate();
			
			return verifAddCategorie==1;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de l'update d'une cat�gorie");
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
	public boolean delete(Integer idCategorie) {
		try {
			String requeteDeleteCategorie = "delete from categorie where idCategorie=?;";
			ps = this.connection.prepareStatement(requeteDeleteCategorie);
			
			ps.setInt(1, idCategorie);
			
			int verifDeleteCategorie = ps.executeUpdate();
			
			return verifDeleteCategorie==1;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de l'update d'une cat�gorie");
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
	public List<Categorie> getAll() {
		try {
			String requeteGetAllCategorie = "select * from categorie;";
			ps = this.connection.prepareStatement(requeteGetAllCategorie);
			
			rs = ps.executeQuery();
			Categorie categorie = null;
			List<Categorie> listeC =new ArrayList<>();
			
			while (rs.next()) {
				categorie = new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				listeC.add(categorie);
			}
			
			return listeC;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de la r�cuperation de la liste des cat�gories");
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
	public Categorie getById(Integer idCategorie) {
		try {
			String requeteGetByIdCategorie = "select * from categorie where idCategorie=?;";
			ps = this.connection.prepareStatement(requeteGetByIdCategorie);
			ps.setInt(1, idCategorie);
			
			rs = ps.executeQuery();
			
			Categorie categorie = null;
			
			rs.next();
			categorie = new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			
			return categorie;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de la r�cuperation de la cat�gorie GetById");
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
