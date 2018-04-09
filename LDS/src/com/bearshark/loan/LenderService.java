package com.bearshark.loan;

import java.util.List;

import com.bearshark.jdbc.LenderDao;

public class LenderService {
	private LenderDao lenderDao = new LenderDao();

	public Boolean createLender(Lender lender) {
		return lenderDao.createLender(lender);
	}

	public Lender getLenderByName(String name) {
		return lenderDao.getLenderByName(name);
	}

	/* public List<Lender> getLendersByNameWildcard(String name) {
		return lenderDao.getLendersByNameWildcard(name);
	} */

	public List<Lender> getAllLenders() {
		return lenderDao.getAllLenders();
	}

	public Lender getLenderById(Long lenderId) {
		return lenderDao.getLenderByID(lenderId);
	}
}