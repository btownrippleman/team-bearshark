package com.bearshark.loan;

import com.bearshark.jdbc.PoolLoanDao;
import com.bearshark.pool.Pool;

public class PoolLoanService {
	private PoolLoanDao poolLoanDao = new PoolLoanDao();

	public Boolean addLoanToPool(Loan loan, Pool pool) {
		return poolLoanDao.addLoanToPool(pool, loan);
	}

	public Boolean addLoansToPoolOnCreation(Pool pool) {
		return poolLoanDao.addLoansToPoolOnCreation(pool);
	}
	
	public void deletePool(Pool pool){
		poolLoanDao.deletePool(pool);
	}
}
