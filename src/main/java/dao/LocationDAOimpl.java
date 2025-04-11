package dao;

import java.sql.Connection;
import java.util.List;

import Exceptions.CompanyException;
import tablas.Location;

public class LocationDAOimpl implements DAO<Location>{

	@Override
	public List<Location> getAll(Connection conn) throws CompanyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location get(Connection conn, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Connection conn, Location t) throws CompanyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Connection conn, Location t, Object[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Connection conn, Location t) {
		// TODO Auto-generated method stub
		
	}

}
