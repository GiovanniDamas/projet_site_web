

CREATE DATABASE db_gestion_site;

USE db_gestion_site;

CREATE TABLE users ( idUser integer auto_increment primary key,
										identifiant varchar (50),
                                        password varchar (50),
                                        actived boolean
);
ALTER TABLE users auto_increment = 0;
                                        
                                        
CREATE TABLE categorie ( idCategorie integer auto_increment primary key,
											 nomCategorie varchar(50),
                                             photo varchar(50),
                                             description varchar (500)
);


                                        
CREATE TABLE produit ( idProduit integer auto_increment primary key,
										  nomProduit varchar(150),
                                          description varchar (1000),
                                          prix double,
                                          quantite integer,
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
                                           
                                        
CREATE TABLE ligneCommande (quantite integer,
                                                        prix double,
                                                        produitID integer,
                                                        commandeID integer,
                                                        idPanier integer,
					
                    CONSTRAINT pk_idCommande_idProduit primary key(produitID, commandeID),
                    CONSTRAINT fk_idProduit foreign key(produitID) references produit (idProduit),
                    CONSTRAINT fk_idCommande foreign key(CommandeID) references commande (idCommande)
);


CREATE TABLE role (idRole integer primary key auto_increment,
									roleName varchar (50),
                                    userID integer,
				CONSTRAINT fk_user foreign key(userID) references users(idUser)
);
ALTER TABLE role auto_increment = 0;

INSERT INTO users (identifiant,password,actived) VALUES ('test', 'test', true);
insert into role (roleName,userID) values ('AdminCat', last_insert_id());


SELECT * FROM categorie;

DELETE FROM categorie WHERE idCategorie = 6;

SELECT idUser, identifiant, password, actived, roleName
FROM users AS u
INNER JOIN role AS r
ON u.idUser = r.userID;

SELECT * FROM users;
SELECT * FROM role;
Select * from produit;

DELETE FROM role;
DELETE FROM ligneCommande;

SELECT idUser, identifiant, password, actived, roleName
FROM users AS u
INNER JOIN role AS r
ON u.idUser = r.userID;


SELECT idUser, identifiant, password, actived, roleName
FROM users u, role  r
WHERE idUser=3 AND userID=3;

SELECT idUser, identifiant, password, actived, roleName
					FROM users AS u
                    INNER JOIN role AS r
                    ON u.idUser = r.userID
					WHERE idUser= 3;

INSERT INTO clients (nomClient,adresse,email,telephone) VALUES ('tartufe', '20 rue du soleil', 'tartufe@tartufe.com', '0646576633');
INSERT INTO commande (dateCommande,clientID) VALUES ('2020-07-13', 1);


INSERT INTO ligneCommande(quantite,prix,produitID,commandeID,idPanier) VALUES (1,100.00,3, 1,1);


SELECT nomProduit, lc.quantite, p.prix
FROM produit as p, ligneCommande as lc
WHERE p.idProduit = lc.produitID;


SELECT * FROM ligneCommande WHERE produitID = 3 AND commandeID = 1;




