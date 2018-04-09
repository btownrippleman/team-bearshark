package com.bearshark.pool;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.bearshark.loan.Loan;

public class Pool {
	private Long pool_id;
	private Long initial_total;
	private BigDecimal initial_apr;
	private Date creation_date;
	private List<Loan> loans = new ArrayList<Loan>();

	public Long getPool_id() {
		return pool_id;
	}

	public void setPool_id(Long pool_id) {
		this.pool_id = pool_id;
	}

	public Long getInitial_total() {
		return initial_total;
	}

	public void setInitial_total(Long initial_total) {
		this.initial_total = initial_total;
	}

	public BigDecimal getInitial_apr() {
		return initial_apr;
	}

	public void setInitial_apr(BigDecimal initial_apr) {
		this.initial_apr = initial_apr;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public Long getTotal() {
		Long total = 0L;
		for (Loan l : loans) {
			if (l != null && l.getLoanPrincipal() != null)
				total += l.getLoanPrincipal();
		}
		return total;
	}

	public int getNumberOfLoans() {
		return loans.size();
	}

	public boolean isValidPoolSize() {
		return (getTotal() >= 1000000);
	}

	@Override
	public String toString() {
		return "Pool [pool_id=" + pool_id + ", initial_total=" + initial_total + ", initial_apr=" + initial_apr
				+ ", creation_date=" + creation_date + ", loans=" + loans + "]";
	}

	public void initializeCreationData() {
		Double totalWeightValueOfLoans = getTotalWeightValueOfLoans();
		Double totalInitialPrincipal = getTotal().doubleValue();

		if (!(totalInitialPrincipal > 0))
			throw new RuntimeException("Cannot divide by zero.");

		Double dblAprValue = totalWeightValueOfLoans / totalInitialPrincipal;
		// System.out.println("totalInitialPrincipal: " + totalInitialPrincipal);
		// System.out.println("Double Apr Value = " + dblAprValue);

		if (dblAprValue.equals(Double.NaN))
			setInitial_apr(new BigDecimal(0));
		else
			setInitial_apr(BigDecimal.valueOf(dblAprValue));

		setInitial_total(totalInitialPrincipal.longValue());
		setCreation_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
	}

	private Double getTotalWeightValueOfLoans() {
		Double retVal = 0.0;
		for (Loan l : loans) {
			Long currPrincipal = null;
			BigDecimal currApr = null;
			if (l != null) {
				currPrincipal = l.getLoanPrincipal();
				currApr = l.getLoanAPR();
			}
			if (currPrincipal != null && currApr != null) {
				retVal += currPrincipal.doubleValue() * currApr.doubleValue();
			}
		}
		return retVal;
	}
}