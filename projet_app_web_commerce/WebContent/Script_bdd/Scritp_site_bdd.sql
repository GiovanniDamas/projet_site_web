

CREATE DATABASE db_gestion_site;

USE db_gestion_site;

CREATE TABLE users ( idUser integer auto_increment primary key,
										identifiant varchar (50),
                                        password varchar (50),
                                        actived boolean
);
                                        
                                        
                                        
CREATE TABLE categorie ( idCategorie integer auto_increment primary key,
											 nomCategorie varchar(50),
                                             photo varchar(50),
                                             description varchar (50)
);
                                              
                                        
CREATE TABLE produit ( idProduit integer auto_increment primary key,
										  nomProduit varchar(50),
                                          description varchar (50),
                                          prix double,
                                          quantité integer,
                                          selectionner boolean,
                                          photo varchar(100),
                                          categorieID integer,
				
                CONSTRAINT fk_categorie foreign key (categorieID) references categorie (idCategorie)
                                          
);


CREATE TABLE clients(idClient integer auto_increment primary key,
									  nomClient varchar(50),
                                      adresse varchar(50),
                                      email varchar(50),
                                      telephone varchar(50)
);
     
     
CREATE TABLE commande ( idCommande integer auto_increment primary key,
												 dateCommande date,
                                                 clientID integer,
                     CONSTRAINT fk_clients foreign key(clientID) references clients (idClient)                  

);
                                                 
                                        
CREATE TABLE ligneCommande (quantité integer,
                                                        prix double,
                                                        produitID integer,
                                                        commandeID integer,
                                                        idPanier integer,
					
                    CONSTRAINT pk_idCommande_idProduit primary key(produitID, commandeID),
                    CONSTRAINT fk_idProduit foreign key(produitID) references produit (idProduit),
                    CONSTRAINT fk_idCommande foreign key(CommandeID) references commande (idCommande)
);


CREATE TABLE role (idRole integer primary key,
									roleName varchar (50),
                                    userID integer,
				CONSTRAINT fk_user foreign key(userID) references users(idUser)
);
