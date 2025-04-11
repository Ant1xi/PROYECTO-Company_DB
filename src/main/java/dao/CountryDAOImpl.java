package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.CompanyException;
import dto.OpcionComboDTO;
import tablas.Country;

public class CountryDAOImpl implements DAO<Country>{

	@Override
	public List<Country> getAll(Connection conn) throws CompanyException {
		// TODO Auto-generated method stub
		return null;
	}
	

	public List<Country> getCountriesByRegion(Connection conn, int regionId) throws SQLException {
	    String sql = "SELECT * FROM countries WHERE region_id = ?";
	    List<Country> countryList = new ArrayList<>();

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	
	        pstmt.setInt(1, regionId);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	            	String countryId = rs.getString("country_id");
	                String countryName = rs.getString("country_name");
	                Integer regionIdFk = rs.getInt("region_id");
	                countryList.add(new Country(countryId, countryName, regionIdFk));
	            }
	        }
	    }
	    return countryList;
	}
	
	
	@Override
	public Country get(Connection conn, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Connection conn, Country t) throws CompanyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Connection conn, Country t, Object[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Connection conn, Country t) {
		// TODO Auto-generated method stub
		
	}

}
