package com.bearshark.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bearshark.loan.Borrower;
import com.bearshark.loan.Lender;
import com.bearshark.loan.Loan;
import com.bearshark.pool.Pool;

public class LoanDao {
	private ConnectionFactory connFactory = ConnectionFactory.getInstance();
	private BorrowerDao borrowerDao = new BorrowerDao();
	private LenderDao lenderDao = new LenderDao();

	private List<Loan> allLoans = null;
	private Long lastUpdate = null;
	private Long cacheTimeout = (long) 5 * 1000;

	private static final String GET_ALL_LOANS_QUERY = "SELECT * FROM LOANS";
	private final String CREATE_LOAN_UPDATE = "INSERT INTO LOANS(LOAN_APR, LOAN_PRINCIPAL, LOAN_START_DATE, LOAN_LAST_UPDATE, LOAN_IS_ADJUSTABLE, BORROWER_ID, LENDER_ID  ) VALUES(?, ?, ?, ?, ?, ?, ?)";

	private boolean cacheIsTimedOut() {
		if (allLoans == null || lastUpdate == null) {
			return true;
		}
		else if (System.currentTimeMillis() > (lastUpdate + cacheTimeout)) {
			return true;
		}
		return false;
	}
	
	private void updateCache() {
		if (cacheIsTimedOut()) {
			allLoans = getAllLoansFromDB();
			lastUpdate = System.currentTimeMillis();
		}
	}
	
	public List<Loan> getAllLoans() {
		updateCache();
		// System.out.println("All Loans");
		// System.out.println(allLoans);
		return allLoans;
	}
	
	private List<Loan> getAllLoansFromDB() {
		List<Loan> loans = new ArrayList<Loan>();

		try (Connection conn = connFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(GET_ALL_LOANS_QUERY);) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Long loanId = rs.getLong(1);
				BigDecimal loanApr = rs.getBigDecimal(2);
				Long principal = rs.getLong(3);
				Date start = rs.getDate(4);
				Date lastUpdate = rs.getDate(5);
				int intIsAdjustable = rs.getInt(6);
				boolean isAdjustable = intIsAdjustable == 1;
				Borrower borrower = borrowerDao.getBorrowerById(rs.getLong(7));
				Lender lender = lenderDao.getLenderByID(rs.getLong(8));

				Loan loan = new Loan();
				loan.setBorrower(borrower);
				loan.setLender(lender);
				loan.setLoanID(loanId);
				loan.setLoanAPR(loanApr);
				loan.setLoanPrincipal(principal);
				loan.setLoanStartDate(start);
				loan.setLoanLastUpdate(lastUpdate);
				loan.setLoanIsAdjustable(isAdjustable);

				loans.add(loan);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Error retrieving all loans.");
			System.out.println("Stacktrace to follow...");
			e.printStackTrace();

			loans = null;
		}

		return loans;
	}

	public Boolean createLoan(Loan loan) {
		try (Connection conn = connFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(CREATE_LOAN_UPDATE);) {
			stmt.setBigDecimal(1, loan.getLoanAPR());
			stmt.setLong(2, loan.getLoanPrincipal());
			stmt.setDate(3, loan.getLoanStartDate());
			stmt.setDate(4, loan.getLoanLastUpdate());
			stmt.setBoolean(5, loan.getLoanIsAdjustable());
			stmt.setLong(6, loan.getBorrower().getBorrowerID());
			stmt.setLong(7, loan.getLender().getLenderID());
			stmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			System.out.println("Error adding loan:");
			System.out.println("Stack trace to follow...");
			e.printStackTrace();

			return false;
		}
	}

	// LOAN_APR NUMBER(5,4)
	// LOAN_PRINCIPAL NUMBER
	// LOAN_START_DATE DATE
	// LOAN_LAST_UPDATE DATE
	// LOAN_IS_ADJUSTABLE NUMBER(1,0)
	// BORROWER_ID NUMBER
	// LENDER_ID NUMBER

	public List<Loan> retrieveLoansByBorrower(Borrower borrower) {
		List<Loan> loans = new ArrayList<Loan>();
		for (Loan l : getAllLoans()) {
			if (l.getBorrower().getBorrowerID().equals(borrower.getBorrowerID()))
				loans.add(l);
		}
		if (loans.size() > 0)
			return loans;
		else
			return null;
	}

	public List<Loan> retrieveLoansByLender(Lender lender) {
		List<Loan> loans = new ArrayList<Loan>();
		for (Loan l : getAllLoans()) {
			if (l.getLender().getLenderID().equals(lender.getLenderID()))
				loans.add(l);
		}
		if (loans.size() > 0)
			return loans;
		else
			return null;
	}

	public Loan getLoanById(Long loanId) {
		for (Loan l : getAllLoans()) {
			if (l.getLoanID().equals(loanId))
				return l;
		}
		System.out.println("Could not find loan with id " + loanId + " in allLoans: " + allLoans);
		return null;
	}

	public List<Loan> getAllUnassociatedLoans() {
		List<Loan> allLoans = getAllLoans();
		List<Loan> unassociatedLoans = new ArrayList<Loan>();

		PoolLoanDao pld = new PoolLoanDao();

		for (Loan loan : allLoans) {
			Pool p = pld.retrievePoolByLoanId(loan.getLoanID());

			if (p == null) {
				unassociatedLoans.add(loan);
			}
		}

		return unassociatedLoans;
	}
}
