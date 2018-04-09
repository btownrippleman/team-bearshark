package main.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.test.driver.DriverManager;
import main.test.pom.AddBorrower;

public class BorrowerTest {
	public WebDriver driver = null;

	@AfterSuite
	public void closeTest() {
		DriverManager.getInstance().close();
	}

	@Test(priority = 1)
	public void Launch() {
		System.out.println("Testing Title");

		String actualtitle = driver.getTitle();
		String expectedTitle = "Borrower Registration";
		Assert.assertEquals(actualtitle, expectedTitle);
		System.out.println("Title of the page is: " + actualtitle);
	}

	@Test(priority = 2)
	public void InformationInput() {
		System.out.println("Testing borrower addition");
		
		AddBorrower.firstName(driver).sendKeys("Mike");
		AddBorrower.lastName(driver).sendKeys("Antonio");
		AddBorrower.creditScore(driver).sendKeys("571");
		AddBorrower.zipCode(driver).sendKeys("48901");
		AddBorrower.submit(driver).click();
		System.out.println("Information Entered and sent to the database!");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class");
		driver = DriverManager.getInstance().driver;
		driver.get("http://localhost:7001/LDS/pages/addBorrower.jsp");
	}
}
