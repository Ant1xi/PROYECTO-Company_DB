package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.CompanyException;
import dto.OpcionComboDTO;
import tablas.Region;

public class RegionDAOImpl implements DAO<Region> {

	@Override
	public List<Region> getAll(Connection conn) throws CompanyException {
		String sqlQuery = "SELECT * FROM regions";

		List<Region> regionList = new ArrayList<Region>();

		try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery); ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				Integer regionId = rs.getInt("region_id");
				String regionName = rs.getString("region_name");

				regionList.add(new Region(regionId, regionName));
			}
			return regionList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CompanyException();
		}

	}


	@Override
	public Region get(Connection conn, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Connection conn, Region t) throws CompanyException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Connection conn, Region t, Object[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Connection conn, Region t) {
		// TODO Auto-generated method stub

	}

}
