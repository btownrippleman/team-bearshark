package com.bearshark.loan;

import java.math.BigDecimal;
import java.sql.Date;

public class Loan {
	private Long loanID;
	private BigDecimal loanAPR;
	private Long loanPrincipal;
	private Date LoanStartDate;
	private Date LoanLastUpdate;
	private Boolean LoanIsAdjustable;
	private Borrower borrower;
	private Lender lender;

	@Override
	public String toString() {
		return "Loan [loanID=" + loanID + ", loanAPR=" + loanAPR + ", loanPrincipal=" + loanPrincipal
				+ ", LoanStartDate=" + LoanStartDate + ", LoanLastUpdate=" + LoanLastUpdate + ", LoanIsAdjustable="
				+ LoanIsAdjustable + "]";
	}

	public Loan(Long loanID, BigDecimal loanAPR, Long loanPrincipal, Date loanStartDate, Date loanLastUpdate,
			Boolean loanIsAdjustable) {
		this.loanID = loanID;
		this.loanAPR = loanAPR;
		this.loanPrincipal = loanPrincipal;
		LoanStartDate = loanStartDate;
		LoanLastUpdate = loanLastUpdate;
		LoanIsAdjustable = loanIsAdjustable;
	}

	public Loan() {
	}

	public Long getLoanID() {
		return loanID;
	}

	public void setLoanID(Long loanID) {
		this.loanID = loanID;
	}

	public BigDecimal getLoanAPR() {
		return loanAPR;
	}

	public void setLoanAPR(BigDecimal loanAPR) {
		this.loanAPR = loanAPR;
	}

	public Long getLoanPrincipal() {
		return loanPrincipal;
	}

	public void setLoanPrincipal(Long loanPrincipal) {
		this.loanPrincipal = loanPrincipal;
	}

	public Date getLoanStartDate() {
		return LoanStartDate;
	}

	public void setLoanStartDate(Date loanStartDate) {
		LoanStartDate = loanStartDate;
	}

	public Date getLoanLastUpdate() {
		return LoanLastUpdate;
	}

	public void setLoanLastUpdate(Date loanLastUpdate) {
		LoanLastUpdate = loanLastUpdate;
	}

	public Boolean getLoanIsAdjustable() {
		return LoanIsAdjustable;
	}

	public void setLoanIsAdjustable(Boolean loanIsAdjustable) {
		LoanIsAdjustable = loanIsAdjustable;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public Lender getLender() {
		return lender;
	}

	public void setLender(Lender lender) {
		this.lender = lender;
	}

}
