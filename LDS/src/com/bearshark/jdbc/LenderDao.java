package com.bearshark.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bearshark.loan.Lender;

public class LenderDao {
	private ConnectionFactory connFactory = ConnectionFactory.getInstance();
	private List<Lender> allLenders = null;
	private Long lastUpdate = null;
	private Long cacheTimeout = (long) 5 * 1000;

	private final String RETRIEVE_ALL_LENDERS = "SELECT * FROM LENDERS";
	private final String CREATE_LENDER_UPDATE = "INSERT INTO LENDERS(LENDER_NAME, LENDER_ZIPCODE) VALUES(?, ?)";

	/* public List<Lender> getLendersByNameWildcard(String name) {
		try (Connection conn = connFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(RETRIEVE_LENDERS_BY_NAME_QUERY_WILDCARD);) {
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();

			List<Lender> lenders = new ArrayList<>();

			while (rs.next() && rs != null) {
				Long id = rs.getLong(1);
				String lendername = rs.getString(2);
				String zip = rs.getString(3);

				Lender lend = new Lender();
				lend.setLenderID(id);
				lend.setLenderName(lendername);
				lend.setZipCode(zip);
				lenders.add(lend);
			}
			return lenders;
		} catch (SQLException e) {
			System.out.println("Error retrieving lender by name: " + name);
			System.out.println("Stack tract to follow...");
			e.printStackTrace();
			return null;
		}
	}
	*/

	private List<Lender> getAllLendersFromDB() {
		List<Lender> lenders = new ArrayList<>();

		try (Connection conn = connFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(RETRIEVE_ALL_LENDERS);) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next() && rs != null) {
				Long id = rs.getLong(1);
				String lendername = rs.getString(2);
				String zip = rs.getString(3);

				Lender lend = new Lender();
				lend.setLenderID(id);
				lend.setLenderName(lendername);
				lend.setZipCode(zip);
				lenders.add(lend);
			}
			rs.close();
			return lenders;
		} catch (SQLException e) {
			System.out.println("Error retrieving all lenders" + lenders);
			System.out.println("Stack tract to follow...");
			e.printStackTrace();
			return null;
		}
	}

	private boolean cacheIsTimedOut() {
		if (allLenders == null || lastUpdate == null) {
			return true;
		}
		else if (System.currentTimeMillis() > (lastUpdate + cacheTimeout)) {
			return true;
		}
		return false;
	}
	
	private void updateCache() {
		if (cacheIsTimedOut()) {
			allLenders = getAllLendersFromDB();
			lastUpdate = System.currentTimeMillis();
		}
	}
	
	public List<Lender> retrieveAllLenders() {
		updateCache();
		return allLenders;
	}

	public Lender getLenderByID(Long id) {
		for (Lender l : retrieveAllLenders()) {
			if (l.getLenderID().equals(id))
				return l;
		}
		return null;
	}

	public Lender getLenderByName(String name) {
		for (Lender l : retrieveAllLenders()) {
			if (l.getLenderName().equals(name))
				return l;
		}
		return null;
	}

	public Boolean createLender(Lender lender) {
		try (Connection conn = connFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(CREATE_LENDER_UPDATE);) {
			stmt.setString(1, lender.getLenderName());
			stmt.setString(2, lender.getZipCode());
			stmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			System.out.println("Error adding lender:");
			System.out.println(lender);
			System.out.println("Stack trace to follow...");
			e.printStackTrace();

			return false;
		}
	}

	public List<Lender> getAllLenders() {
		return retrieveAllLenders();
	}
}
