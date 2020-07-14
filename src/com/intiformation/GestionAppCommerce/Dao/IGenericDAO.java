package com.intiformation.GestionAppCommerce.Dao;

import java.sql.Connection;
import java.util.List;

import com.intiformation.GestionAppCommerce.Tools.ConnexionBdd;

public interface IGenericDAO <T> {

	   public Connection connection = ConnexionBdd.getInstance();

	    public boolean add(T t);

	    public boolean update(T t);

	    public boolean delete(Integer id);

	    public List<T> getAll();

	    public T getById(Integer id);


}//END INTERFACE


