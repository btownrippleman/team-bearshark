package com.bearshark.loan;

public class Borrower 
{
  private Long borrowerID;
  private String firstName;
  private String lastName;
  private Integer creditScore;
  private String zipCode;
@Override
public String toString() 
{
	return "Borrower [borrowerID=" + borrowerID + ", firstName=" + firstName + ", lastName=" + lastName
			+ ", creditScore=" + creditScore + ", zipCode=" + zipCode + "]";
}
public Borrower(Long borrowerID, String firstName, String lastName, Integer creditScore, String zipCode) 
{
	this.borrowerID = borrowerID;
	this.firstName = firstName;
	this.lastName = lastName;
	this.creditScore = creditScore;
	this.zipCode = zipCode;
}
public Borrower() {}
public Long getBorrowerID() {
	return borrowerID;
}
public void setBorrowerID(Long borrowerID) {
	this.borrowerID = borrowerID;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getFullName() {
	return firstName + " " + lastName;
}
public Integer getCreditScore() {
	return creditScore;
}
public void setCreditScore(Integer creditScore) {
	this.creditScore = creditScore;
}
public String getZipCode() {
	return zipCode;
}
public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}




  

  
}
