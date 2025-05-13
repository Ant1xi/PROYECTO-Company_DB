package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.CompanyException;
import tablas.Customer;
import tablas.Order;

public class OrderDAOImpl {

	public List<Order> getByCustomerId(Connection conn, int customerId) throws CompanyException {
		List<Order> orderList = new ArrayList<>();

		String sqlQuery = "SELECT * FROM orders WHERE customer_id = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
			pstmt.setInt(1, customerId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int orderId = rs.getInt("order_id");
				int custId = rs.getInt("customer_id");
				String status = rs.getString("status");
				int salesmanId = rs.getInt("salesman_id");
				java.util.Date orderDate = rs.getDate("order_date");

				orderList.add(new Order(orderId, custId, status, salesmanId, orderDate));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new CompanyException("Error al obtener pedidos");
		}

		return orderList;
	}

}
