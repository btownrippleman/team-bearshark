package main.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Index {

	private static final String url = "http://localhost:7001/LDS/";

//	"Success! Created Pool and added to Pool table"
	private static final String _alert        = "/html/body/div[2]/div";	
	private static final String _addBorrow    = "/html/body/div[2]/ul/li[1]/a";
	private static final String _addLender    = "/html/body/div[2]/ul/li[2]/a";
	private static final String _addLoan      = "/html/body/div[2]/ul/li[3]/a";
	private static final String _selectLoans  = "/html/body/div[2]/ul/li[4]/a";
	private static final String _viewAllLoans = "/html/body/div[2]/ul/li[5]/a";
	private static final String _viewAllPools = "/html/body/div[2]/ul/li[6]/a";

	public static void loadPage(WebDriver wd) {
		wd.get(url);
	}
	
	public static WebElement alert(WebDriver wd) {
		return wd.findElement(By.xpath(_alert));
	}

	public static WebElement addBorrow(WebDriver wd) {
		return wd.findElement(By.xpath(_addBorrow));
	}

	public static WebElement addLender(WebDriver wd) {
		return wd.findElement(By.xpath(_addLender));
	}

	public static WebElement addLoan(WebDriver wd) {
		return wd.findElement(By.xpath(_addLoan));
	}

	public static WebElement selectLoans(WebDriver wd) {
		return wd.findElement(By.xpath(_selectLoans));
	}

	public static WebElement viewAllLoans(WebDriver wd) {
		return wd.findElement(By.xpath(_viewAllLoans));
	}

	public static WebElement viewAllPools(WebDriver wd) {
		return wd.findElement(By.xpath(_viewAllPools));
	}		
}
