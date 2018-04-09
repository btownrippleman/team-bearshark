package com.bearshark.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bearshark.pool.Pool;

public class PoolDao {
	private ConnectionFactory connFactory = ConnectionFactory.getInstance();

	private final String CREATE_POOL_UPDATE = "INSERT INTO POOLS(INITIAL_TOTAL, INITIAL_APR, CREATION_DATE) VALUES(?, ?, ?)";
	private final String RETRIEVE_ALL_POOLS = "SELECT POOLS.POOL_ID, POOLS.INITIAL_TOTAL, POOLS.INITIAL_APR,  POOLS.CREATION_DATE FROM POOLS";

	private List<Pool> allPools = null;
	private Long lastUpdate = null;
	private Long cacheTimeout = (long) 5 * 1000;
	
	private boolean cacheIsTimedOut() {
		if (allPools == null || lastUpdate == null) {
			return true;
		}
		else if (System.currentTimeMillis() > (lastUpdate + cacheTimeout)) {
			return true;
		}
		return false;
	}
	
	private void forceCacheUpdate() {
		allPools = getAllPoolsFromDB();
		lastUpdate = System.currentTimeMillis();
	}
	
	private void updateCache() {
		if (cacheIsTimedOut()) {
			forceCacheUpdate();
		}
	}
	
	private List<Pool> getAllPoolsFromDB() {
		List<Pool> pools = new ArrayList<>();

		try (Connection conn = connFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(RETRIEVE_ALL_POOLS);) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next() && rs != null) {
				Long id = rs.getLong(1);
				Long initial_total = rs.getLong(2);
				BigDecimal initial_apr = rs.getBigDecimal(3);
				Date creation_date = rs.getDate(4);

				Pool pool = new Pool();
				pool.setPool_id(id);
				pool.setInitial_total(initial_total);
				pool.setInitial_apr(initial_apr);
				pool.setCreation_date(creation_date);
				pools.add(pool);
			}
			rs.close();
			return pools;
		} catch (SQLException e) {
			System.out.println("Error retrieving all pools" + pools);
			System.out.println("Stack tract to follow...");
			e.printStackTrace();
			return null;
		}
	}
	
	public Long createPool(Pool pool) { // TODO add loans to pool
		try (Connection conn = connFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(CREATE_POOL_UPDATE);) {
			stmt.setLong(1, pool.getInitial_total());
			stmt.setBigDecimal(2, pool.getInitial_apr());
			stmt.setDate(3, pool.getCreation_date());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error adding pool:");
			System.out.println(pool);
			System.out.println("Stack trace to follow...");
			e.printStackTrace();

			return null;
		}
		
		return retrieveLastAddedIdFromPools();
	}

	public Long retrieveLastAddedIdFromPools() { // TODO add loans to pool
		forceCacheUpdate();
		Long largestId = 0L;
		// System.out.println("All pools...");
		// System.out.println(allPools);
		for (Pool p : allPools) {
			if (p.getPool_id() > largestId)
				largestId = p.getPool_id();
		}
		return largestId;
	}

	public List<Pool> getAllPools() { // TODO add retrieval of loans in pool
		updateCache();
		return allPools;
	}

	public Pool getPoolById(Long poolId) {
		for (Pool p : getAllPools()) {
			if (p.getPool_id().equals(poolId))
				return p;
		}
		return null;
	}
}