package dao;

import java.sql.*;

import java.util.*;

import Exceptions.CompanyException;

public interface DAO<T> {
	public List<T> getAll(Connection conn) throws CompanyException;
	
	public T get(Connection conn, int id);
	
	public void create(Connection conn, T t);
	
	public void update(Connection conn, T t, Object[] params);
	
	public void delete(Connection conn, T t);
}
