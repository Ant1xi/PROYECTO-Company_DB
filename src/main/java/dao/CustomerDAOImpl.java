package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.CompanyException;
import dto.ClienteModificarDTO;
import tablas.Customer;

public class CustomerDAOImpl implements DAO<Customer> {

	@Override
	public List<Customer> getAll(Connection conn) throws CompanyException {
		List<Customer> customerList = new ArrayList<Customer>();

		String sqlQuery = "SELECT * FROM Customers";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Integer customerId = rs.getInt("customer_id");
				String name = rs.getString("name");
				String addres = rs.getString("address");
				String website = rs.getString("website");
				Double creditLimit = rs.getDouble("credit_limit");

				customerList.add(new Customer(customerId, name, addres, website, creditLimit));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customerList;
	}
	
	

	@Override
	public Customer get(Connection conn, int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<Customer> getByIdOrNombre(Connection conn, int id, String nombre) throws CompanyException{
		List<Customer> customerList = new ArrayList<Customer>();

		String sqlQuery = "SELECT * FROM Customers WHERE customer_id = ? OR name = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, nombre);
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Integer customerId = rs.getInt("customer_id");
				String name = rs.getString("name");
				String addres = rs.getString("address");
				String website = rs.getString("website");
				Double creditLimit = rs.getDouble("credit_limit");

				customerList.add(new Customer(customerId, name, addres, website, creditLimit));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customerList;
	}

	@Override
	public void create(Connection conn, Customer t) throws CompanyException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Connection conn, Customer t, Object[] params) {
		// TODO Auto-generated method stub

	}
	
	public void update(Connection conn, Customer customer) throws SQLException {
	    String sql = "UPDATE customers SET name = ?, address = ?, website = ?, credit_limit = ? WHERE customer_id = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, customer.getName());
	        stmt.setString(2, customer.getAddres());
	        stmt.setString(3, customer.getWebsite());
	        stmt.setDouble(4, customer.getCreditLimit());
	        stmt.setInt(5, customer.getCustomerId());

	        stmt.executeUpdate();
	    }
	}


	@Override
	public void delete(Connection conn, Customer t) {
		// TODO Auto-generated method stub

	}

}
