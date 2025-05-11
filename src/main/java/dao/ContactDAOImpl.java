package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Exceptions.CompanyException;
import Exceptions.ContactDataException;
import tablas.Contact;

public class ContactDAOImpl implements DAO<Contact> {

	@Override
	public List<Contact> getAll(Connection conn) throws CompanyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact get(Connection conn, int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
    public Contact getByCustomerId(Connection conn, int customerId) throws SQLException, ContactDataException {
        String sql = "SELECT * FROM contacts WHERE customer_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Contact(
                    rs.getInt("contact_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    customerId
                );
            } else {
                return null;
            }
        }
    }

    public void update(Connection conn, Contact contact) throws SQLException {
        String sql = "UPDATE contacts SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE contact_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contact.getFirstName());
            stmt.setString(2, contact.getLastName());
            stmt.setString(3, contact.getEmail());
            stmt.setString(4, contact.getPhone());
            stmt.setInt(5, contact.getContactId());

            stmt.executeUpdate();
        }
    }

	@Override
	public void create(Connection conn, Contact t) throws CompanyException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Connection conn, Contact t, Object[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Connection conn, Contact t) {
		// TODO Auto-generated method stub

	}
}
