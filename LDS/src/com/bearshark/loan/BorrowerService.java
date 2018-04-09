package com.bearshark.loan;

import java.util.List;

import com.bearshark.jdbc.BorrowerDao;

public class BorrowerService {
	private BorrowerDao borrowerDao = new BorrowerDao();

	public Boolean createBorrower(Borrower borrower) {
		return borrowerDao.createBorrower(borrower);
	}

	public Borrower getBorrowersByFirstName(String firstname) {
		return borrowerDao.getBorrowerByFirstName(firstname);
	}
	
	public Borrower getBorrowerByLastName(String lastname){
		return borrowerDao.getBorrowerByLastName(lastname);
	}
	
	public Borrower getBorrowerByFullName(String firstname, String lastname){
	return borrowerDao.getBorrowerByFirstAndLastName(firstname, lastname);
	}
	
	public Borrower getBorrowerByID(Long borrowerid){
		return borrowerDao.retrieveBorrowerById(borrowerid);
	}

	public List<Borrower> getAllBorrowers() {
		return borrowerDao.getAllBorrowers();
	}
	
	
}
