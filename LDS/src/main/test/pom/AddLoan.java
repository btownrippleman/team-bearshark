package main.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddLoan {
	private final static String url = "http://localhost:7001/LDS/ctrl/addLoan";
	public static void loadPage(WebDriver driver) {
		driver.get(url);
	}
	
	private static String firstBorrowerXPath = "//*[@id=\"borrower\"]/option[2]";
	public static WebElement getFirstBorrowerOption(WebDriver driver) {
		return driver.findElement(By.xpath(firstBorrowerXPath));
	}
	
	private static String firstLenderXPath = "//*[@id=\"lender\"]/option[2]";
	public static WebElement getFirstLenderOption(WebDriver driver) {
		return driver.findElement(By.xpath(firstLenderXPath));
	}
	
	private static String aprXPath = "//*[@id=\"apr\"]";
	public static WebElement getAPRInput(WebDriver driver) {
		return driver.findElement(By.xpath(aprXPath));
	}
	
	private static String principalXPath = "//*[@id=\"principal\"]";
	public static WebElement getPrincipalInput(WebDriver driver) {
		return driver.findElement(By.xpath(principalXPath));
	}
	
	private static String startDateXPath = "//*[@id=\"loanstart\"]";
	public static WebElement getStartDateInput(WebDriver driver) {
		return driver.findElement(By.xpath(startDateXPath));
	}
	
	private static String lastUpdateXPath = "//*[@id=\"lastupdate\"]";
	public static WebElement getLastUpdateInput(WebDriver driver) {
		return driver.findElement(By.xpath(lastUpdateXPath));
	}
	
	private static String isAdjustableXPath = "//*[@id=\"isadjustable\"]";
	public static WebElement getIsAdjustableInput(WebDriver driver) {
		return driver.findElement(By.xpath(isAdjustableXPath));
	}
	
	private static String submitBtnXPath = "//*[@value=\"create_loan\"]";
	public static WebElement getSubmitButton(WebDriver driver) {
		return driver.findElement(By.xpath(submitBtnXPath));
	}
	
	private static String cancelBtnXPath = "//*[@value=\"cancel_loan\"]";
	public static WebElement getCancelButton(WebDriver driver) {
		return driver.findElement(By.xpath(cancelBtnXPath));
	}
}