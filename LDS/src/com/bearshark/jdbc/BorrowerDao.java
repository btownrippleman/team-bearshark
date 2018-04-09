package com.bearshark.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bearshark.loan.Borrower;

public class BorrowerDao {
	private ConnectionFactory connFactory = ConnectionFactory.getInstance();
	private List<Borrower> allBorrowers = null;
	private Long lastUpdate = null;
	private Long cacheTimeout = (long) 5 * 1000;
	
	private final String CREATE_BORROWER_UPDATE = "INSERT INTO BORROWER(FIRST_NAME,	LAST_NAME,	CREDIT_SCORE, ZIP_CODE) VALUES(?, ?, ?, ?)";
	private final String RETRIEVE_ALL_BORROWERS = "SELECT * FROM BORROWER";
	private final String DELETE_BORROWER = "DELETE ";
	
	/* BORROWER_ID NUMBER // TABLE FROM BRADY
	 FIRST_NAME VARCHAR2(100 BYTE)
	 LAST_NAME VARCHAR2(100 BYTE)
	 CREDIT_SCORE NUMBER
	 ZIP_CODE VARCHAR2(5 BYTE)
	*/

	private boolean cacheIsTimedOut() {
		if (allBorrowers == null || lastUpdate == null) {
			return true;
		}
		else if (System.currentTimeMillis() > (lastUpdate + cacheTimeout)) {
			return true;
		}
		return false;
	}
	private void updateCache() {
		if (cacheIsTimedOut()) {
			allBorrowers = getAllBorowersFromDB();
			lastUpdate = System.currentTimeMillis();
		}
	}
	private List<Borrower> getAllBorowersFromDB() {
		try (Connection conn = connFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(RETRIEVE_ALL_BORROWERS);) {
			ResultSet rs = stmt.executeQuery();

			List<Borrower> borrowers = new ArrayList<Borrower>();

			while (rs.next() && rs != null) {
				Long id = rs.getLong(1);
				String firstname = rs.getString(2);
				String lastname = rs.getString(3);
				Integer creditscore = rs.getInt(4);
				String zip_code = rs.getString(5);

				Borrower borrower = new Borrower();
				borrower.setBorrowerID(id);
				borrower.setFirstName(firstname);
				borrower.setLastName(lastname);
				borrower.setCreditScore(creditscore);
				borrower.setZipCode(zip_code);
				borrowers.add(borrower);
			}
			rs.close();

			return borrowers;

		} catch (SQLException e) {
			System.out.println("Error retrieving all borrowers");
			System.out.println("Stack tract to follow...");
			e.printStackTrace();
			return null;
		}
	}
	
	public Borrower getBorrowerByFirstName(String entry) {
		for (Borrower borrower : getAllBorrowers()) {
			if (borrower.getFirstName().equals(entry))
				return borrower;
		}
		return null;
	}

	/* public List<Borrower> getBorrowersByFirstNameWildcard(String entry) {
		try (Connection conn = connFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(RETRIEVE_BORROWERS_BY_FIRST_NAME_WILDCARD_QUERY);) {
			stmt.setString(1, entry);
			ResultSet rs = stmt.executeQuery();
			List<Borrower> borrowers = new ArrayList<Borrower>();

			while (rs.next() && rs != null) {
				Long id = rs.getLong(1);
				String firstname = rs.getString(2);
				String lastname = rs.getString(3);
				Integer creditscore = rs.getInt(4);
				String zip_code = rs.getString(5);

				Borrower borrower = new Borrower();
				borrower.setBorrowerID(id);
				borrower.setFirstName(firstname);
				borrower.setLastName(lastname);
				borrower.setCreditScore(creditscore);
				borrower.setZipCode(zip_code);
				borrowers.add(borrower);
			}
			return borrowers;
		} catch (SQLException e) {
			System.out.println("Error retrieving borrowers by first name: " + entry);
			System.out.println("Stack tract to follow...");
			e.printStackTrace();
			return null;
		}
	}
	*/

	public Borrower getBorrowerByLastName(String entry) {
		for (Borrower borrower : getAllBorrowers()) {
			if (borrower.getLastName().equals(entry))
				return borrower;
		}
		return null;
	}

	/* public List<Borrower> getBorrowersByLastNameWildcard(String entry) {
		try (Connection conn = connFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(RETRIEVE_BORROWERS_BY_LAST_NAME_WILDCARD_QUERY);) {
			stmt.setString(1, entry);
			ResultSet rs = stmt.executeQuery();

			List<Borrower> borrowers = new ArrayList<Borrower>();

			while (rs.next() && rs != null) {
				Long id = rs.getLong(1);
				String firstname = rs.getString(2);
				String lastname = rs.getString(3);
				Integer creditscore = rs.getInt(4);
				String zip_code = rs.getString(5);

				Borrower borrower = new Borrower();
				borrower.setBorrowerID(id);
				borrower.setFirstName(firstname);
				borrower.setLastName(lastname);
				borrower.setCreditScore(creditscore);
				borrower.setZipCode(zip_code);
				borrowers.add(borrower);
			}

			return borrowers;

		} catch (SQLException e) {
			System.out.println("Error retrieving user by last name: " + entry);
			System.out.println("Stack tract to follow...");
			e.printStackTrace();
			return null;
		}
	}
	*/

	/* public List<Borrower> getBorrowerByFirstAndLastNameWildcard(String firstName, String LastName) {
		List<Borrower> borrowers = new ArrayList<>();
		try (Connection conn = connFactory.getConnection();
				PreparedStatement stmt = conn
						.prepareStatement(RETRIEVE_BORROWERS_BY_FIRST_AND_LAST_NAME_BY_WILDCARD_QUERY);) {
			stmt.setString(1, firstName);
			stmt.setString(2, LastName);
			ResultSet rs = stmt.executeQuery();

			while (rs.next() && rs != null) {
				Long id = rs.getLong(1);
				String firstname = rs.getString(2);
				String lastname = rs.getString(3);
				Integer creditscore = rs.getInt(4);
				String zip_code = rs.getString(5);

				Borrower borrower = new Borrower();
				borrower.setBorrowerID(id);
				borrower.setFirstName(firstname);
				borrower.setLastName(lastname);
				borrower.setCreditScore(creditscore);
				borrower.setZipCode(zip_code);
				borrowers.add(borrower);
			}
			return borrowers;
		} catch (SQLException e) {
			System.out.println("Error retrieving user by first name: " + firstName + " and last name: " + LastName);
			System.out.println("Stack tract to follow...");
			e.printStackTrace();
			return null;
		}
	}
	*/

	public Borrower getBorrowerByFirstAndLastName(String firstName, String LastName) {
		for (Borrower borrower : getAllBorrowers()) {
			if (borrower.getFirstName().equals(firstName) && borrower.getLastName().equals(LastName))
				return borrower;
		}
		return null;
	}

	public List<Borrower> getAllBorrowers() {
		updateCache();
		return allBorrowers;
	}

	public Boolean createBorrower(Borrower borrower) {
		try (Connection conn = connFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(CREATE_BORROWER_UPDATE);) {
			stmt.setString(1, borrower.getFirstName());
			stmt.setString(2, borrower.getLastName());
			stmt.setInt(3, borrower.getCreditScore());
			stmt.setString(4, borrower.getZipCode());
			stmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			System.out.println("Error adding borrower:");
			System.out.println(borrower);
			System.out.println("Stack trace to follow...");
			e.printStackTrace();

			return false;
		}
	}

	public Borrower retrieveBorrowerById(Long id) {
		for (Borrower borrower : getAllBorrowers()) {
			if (borrower.getBorrowerID().equals(id))
				return borrower;
		}
		return null;
	}

	public Borrower getBorrowerById(Long borrowerId) {
		return retrieveBorrowerById(borrowerId);
	}
}
