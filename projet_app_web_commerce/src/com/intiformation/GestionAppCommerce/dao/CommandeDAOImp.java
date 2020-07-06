package com.intiformation.GestionAppCommerce.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.GestionAppCommerce.modele.Commande;

public class CommandeDAOImp implements ICommandeDAO{
	
	PreparedStatement ps = null;
	ResultSet rs =null;

	@Override
	public boolean add(Commande commande) {
		try {
			String requeteAddCommande = "insert into commande (dateCommande,clientID) values (?,?);";
			ps = this.connection.prepareStatement(requeteAddCommande);
			
			ps.setDate(1, (Date) commande.getDateCommande());
			ps.setInt(2, commande.getIdClient());
			
			int verifAddCommande = ps.executeUpdate();
			
			return verifAddCommande==1;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de l'ajout d'une commande");
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
	public boolean update(Commande commande) {
		try {
			String requeteUpdateCommande = "update commande set dateCommande=? , clientID=? where idCommande=? ;";
			ps = this.connection.prepareStatement(requeteUpdateCommande);
			
			ps.setDate(1, (Date) commande.getDateCommande());
			ps.setInt(2, commande.getIdClient());
			ps.setInt(3, commande.getIdCommande());
			
			int verifUpdateCommande = ps.executeUpdate();
			
			return verifUpdateCommande==1;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de l'update d'une commande");
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
	public boolean delete(Integer idCommande) {
		try {
			String requeteDeleteCommande = "delete from commande where idCommande=? ;";
			ps = this.connection.prepareStatement(requeteDeleteCommande);
			
			ps.setInt(1, idCommande);
			
			int verifDeleteCommande = ps.executeUpdate();
			
			return verifDeleteCommande==1;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors du delete d'une commande");
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
	public List<Commande> getAll() {
		try {
			String requeteGetAllCommande = "select idCommande,clientID,dateCommande from commande;";
			ps = this.connection.prepareStatement(requeteGetAllCommande);
			
			rs = ps.executeQuery();
			Commande commande = null;
			List<Commande> listeC =new ArrayList<>();
			
			while (rs.next()) {
				commande = new Commande(rs.getInt(1), rs.getInt(2), rs.getDate(3));
				listeC.add(commande);
			}
			
			return listeC;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de la récuperation de la liste des commandes");
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
	public Commande getById(Integer idCommande) {
		try {
			String requeteGetByIdCommande = "select idCommande,clientID,dateCommande from commande where idCommande=? ;";
			ps = this.connection.prepareStatement(requeteGetByIdCommande);
			ps.setInt(1, idCommande);
			
			rs = ps.executeQuery();
			Commande commande = null;
			rs.next();
			commande = new Commande(rs.getInt(1), rs.getInt(2), rs.getDate(3));

			return commande;
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de la récuperation de la commande");
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
