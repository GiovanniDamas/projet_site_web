package com.intiformation.GestionAppCommerce.Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utilitaire de connexion pour se connecter à la BDD
 * @author giovanni
 *
 */
public class ConnexionBdd {
	
	private static String URL_BDD = "jdbc:mysql://localhost:3306/db_gestion_site";
	private static String USER_BDD = "root";
	private static String USER_PASSWORD = "root";
	private static String MYSQL_JDBC_DRIVER_CLASS="com.mysql.jdbc.Driver";
	
	
	private static Connection connexion;
	
	/**
	 * ctor pour interdire d'instancier via DBConnection
	 */
	private ConnexionBdd() {
		
	}//end ctor
	
	
	public static Connection getInstance() {
		
		if (connexion == null) {
			
			try {
				//chargement pilote mysql 
				Class.forName(MYSQL_JDBC_DRIVER_CLASS);
				
				//reucp de la co 
				connexion = DriverManager.getConnection(URL_BDD, USER_BDD, USER_PASSWORD);
				
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println(" Erreur lors de la tentative de connexion avec la BDD ...");
				e.printStackTrace();
			}//END CATCH	
			
		}//END IF 
		
		return connexion;
		
	}//END connexionBdd
		

}//END CLASS
