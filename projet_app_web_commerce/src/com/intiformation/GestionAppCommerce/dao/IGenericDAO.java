package com.intiformation.GestionAppCommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.intiformation.GestionAppCommerce.tools.ConnexionBdd;

public interface IGenericDAO <T> {

	   public Connection connection = ConnexionBdd.getInstance();

	    public boolean add(T t);

	    public boolean update(T t);

	    public boolean delete(Integer id);

	    public List<T> getAll();

	    public T getById(Integer id);


}//END INTERFACE


