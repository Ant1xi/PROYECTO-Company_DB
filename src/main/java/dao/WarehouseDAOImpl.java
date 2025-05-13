package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Exceptions.CompanyException;
import Exceptions.WarehouseDataException;
import tablas.Warehouse;

public class WarehouseDAOImpl {

	public void create(Connection conn, Warehouse warehouse) throws CompanyException {
		String sqlQuery = "INSERT INTO warehouses (warehouse_name, location_id) VALUES (?,?)";

		try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
			pstmt.setString(1, warehouse.getWarehouseName());
			pstmt.setInt(2, warehouse.getLocationId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new WarehouseDataException();
		}

	}

}
