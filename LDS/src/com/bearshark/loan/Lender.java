package com.bearshark.loan;

public class Lender 
{
	private Long lenderID;
	private String lenderName;
	private String zipCode;
	
	@Override
	public String toString() 
	{
		return "Lender [lenderID=" + lenderID + ", lenderName=" + lenderName + ", zipCode=" + zipCode + "]";
	}

	public Lender(Long lenderID, String lenderName, String zipCode) 
	{
		this.lenderID = lenderID;
		this.lenderName = lenderName;
		this.zipCode = zipCode;
	}

	public Lender() {}

	public Long getLenderID() {
		return lenderID;
	}

	public void setLenderID(Long lenderID) {
		this.lenderID = lenderID;
	}

	public String getLenderName() {
		return lenderName;
	}

	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	
	

}
