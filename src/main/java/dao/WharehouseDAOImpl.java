package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Exceptions.CompanyException;
import Exceptions.WarehouseDataException;
import tablas.Warehouse;

public class WharehouseDAOImpl implements DAO<Warehouse>{

	@Override
	public List<Warehouse> getAll(Connection conn) throws CompanyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Warehouse get(Connection conn, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Connection conn, Warehouse warehouse) throws CompanyException {
		String sqlQuery = "INSERT INTO warehouses (warehouse_name, location_id) VALUES (?,?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery))
		{
			pstmt.setString(1, warehouse.getWarehouseName());
			pstmt.setInt(2, warehouse.getLocationId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WarehouseDataException();
		}
		
	}

	@Override
	public void update(Connection conn, Warehouse t, Object[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Connection conn, Warehouse t) {
		// TODO Auto-generated method stub
		
	}

}
