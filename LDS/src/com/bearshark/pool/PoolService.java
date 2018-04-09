package com.bearshark.pool;

import java.util.List;

import com.bearshark.jdbc.PoolDao;
import com.bearshark.jdbc.PoolLoanDao;

public class PoolService {
	private PoolDao poolDao = new PoolDao();
	private PoolLoanDao poolLoanDao = new PoolLoanDao();

	public List<Pool> retrieveAllPools() {
		return poolDao.getAllPools();
	}

	public Long createPool(Pool pool) throws IllegalArgumentException {
		if (pool.isValidPoolSize()) {
			return poolDao.createPool(pool);
		}
		else {
			throw new IllegalArgumentException(
				"Pool must be at least 1 Million dollars of principle on creation. "
				+ "Attempted to create pool with size " + pool.getTotal()
			);
		}
	}

	public List<Pool> getAllPools() {
		return poolDao.getAllPools();
	}
	
	public Long getLastAddedIdofPools(){
		return poolDao.retrieveLastAddedIdFromPools();
	}
	

}
