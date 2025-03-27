package dao;

import java.sql.*;
import java.util.*;

import Exceptions.EmployeeDataException;

public interface DAO<T> {
	public List<T> getAll(Connection conn) throws EmployeeDataException;
	
	public T get(Connection conn, int id);
	
	public void create(Connection conn, T t);
	
	public void update(Connection conn, T t, Object[] params);
	
	public void delete(Connection conn, T t);
}
