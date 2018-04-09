package com.bearshark.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bearshark.loan.Loan;
import com.bearshark.pool.Pool;

public class PoolLoanDao {
	private ConnectionFactory connFactory = ConnectionFactory.getInstance();
	// this method is to add a loan to a pool
	private final String INSERT_INTO_POOL_UPDATE = "insert into pool_loans(Loan_ID, pool_ID) values(?,?)";
	private final String SELECT_ENTRY_BY_LOAN_ID_QUERY = "SELECT LOAN_ID, POOL_ID FROM POOL_LOANS WHERE LOAN_ID = ?";
	private final String SELECT_ENTRIES_BY_POOL_ID_QUERY = "SELECT LOAN_ID, POOL_ID FROM POOL_LOANS WHERE POOL_ID = ?";

	public Boolean addLoanToPool(Pool pool, Loan loan) {
		if (loan == null || loan.getLoanID() == null) {
			throw new IllegalArgumentException("Loan id must not be null.  Attempted to add loan " + loan + "to a pool via PoolLoanDao.");
		}
		System.out.println("public Boolean addLoanToPool(Pool pool, Loan loan) {");
		try (Connection conn = connFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(INSERT_INTO_POOL_UPDATE);) {
			pstmt.setLong(1, loan.getLoanID());
			pstmt.setLong(2, pool.getPool_id());
			pstmt.executeUpdate();
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() < start +15L){ /*empty loop*/}

			return true;
		} catch (SQLException e) {
			System.out.println("Error in inserting loan: ");
			System.out.println(loan);
			System.out.println("Here is the stack trace: ");
			e.printStackTrace();

			return false;
		}
	}

	public Boolean addLoansToPoolOnCreation(Pool pool) {
		String tValuesToInsert = generateValuesToInsert(pool);
		
		String query = "INSERT ALL\n" + tValuesToInsert + "SELECT * FROM DUAL";
		// System.out.println(query);
		try (
			Connection conn = connFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		) {
			pstmt.executeUpdate();
			
			return true;
		}
		catch (SQLException e) {
			System.out.println("Unable to add loans for pool with id " + pool.getPool_id());
			System.out.println("Stack trace to follow...");
			e.printStackTrace();
			return false;
		}
	}
	
	private String generateValuesToInsert(Pool pool) {
		int numLoans = pool.getNumberOfLoans();
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < numLoans; i++) {
			sb.append("INTO POOL_LOANS(LOAN_ID, POOL_ID) VALUES");
			sb.append("(");
			sb.append(pool.getLoans().get(i).getLoanID());
			sb.append(", ");
			sb.append(pool.getPool_id());
			sb.append(")");
			sb.append("\n");
		}
		
		return sb.toString();
	}

	public Pool retrievePoolByLoanId(Long loanId) {
		try (
			Connection conn = connFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ENTRY_BY_LOAN_ID_QUERY);
		) {
			pstmt.setLong(1, loanId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Long poolId = rs.getLong(2);
				rs.close();
				return new PoolDao().getPoolById(poolId);
			} else{
				rs.close();
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Could not find pool for loan with id: " + loanId);
			// common expected behavior. only uncomment for debugging
			// System.out.println("Stack trace to follow...");
			// e.printStackTrace();
			return null;
		}
	}

	public List<Loan> getLoansInPool(Long poolId) {
		List<Loan> loans = new ArrayList<Loan>();

		try (Connection conn = connFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ENTRIES_BY_POOL_ID_QUERY);) {
			List<Long> loanIds = new ArrayList<Long>();
			pstmt.setLong(1, poolId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				loanIds.add(rs.getLong(1));
			}
			rs.close();

			LoanDao loanDao = new LoanDao();
			for (Long l : loanIds) {
				loans.add(loanDao.getLoanById(l));
			}

			return loans;
		} catch (SQLException e) {
			System.out.println("Error retrieving loans for pool with ID: " + poolId);
			System.out.println("Stacktrace to follow...");
			e.printStackTrace();
			return null;
		}
	}
	
	public void deletePool(Pool pool){
		final String DELETE_POOL = "delete from pool_loans where pool_id = ?; delete from pools where pool_id = ?;";
		try(Connection conn = connFactory.getConnection(); 
			PreparedStatement pstmt= conn.prepareStatement(DELETE_POOL);){
			pstmt.setLong(1, pool.getPool_id());
			pstmt.setLong(2, pool.getPool_id());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
