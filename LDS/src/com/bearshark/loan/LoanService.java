package com.bearshark.loan;

import java.util.List;

import com.bearshark.jdbc.LoanDao;

public class LoanService {

	private LoanDao loanDao = new LoanDao();

	public Boolean makeLoan(Loan loan) {
		return loanDao.createLoan(loan);
	}

	public List<Loan> getLoansByBorrower(Borrower borrower) {
		return loanDao.retrieveLoansByBorrower(borrower);
	}

	public List<Loan> getLoansByLender(Lender lender) {
		return loanDao.retrieveLoansByLender(lender);
	}
	
	public List<Loan> getAllUnassociatedLoans() {
		return loanDao.getAllUnassociatedLoans();
	}

	public List<Loan> getAllLoans() {
		return loanDao.getAllLoans();
	}

	public Loan getLoanById(String s) {
		Long loanId = Long.parseLong(s);
		// System.out.println("Converting string to long " + s + " --> " + loanId);
		return getLoanById(loanId);
	}

	public Loan getLoanById(Long loanId) {
		return loanDao.getLoanById(loanId);
	}
}