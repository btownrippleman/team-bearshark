package main.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.test.driver.DriverManager;
import main.test.pom.AddLoan;

public class LoanTest {
	WebDriver driver = null;
	
	@AfterSuite
	public void closeTest() {
		DriverManager.getInstance().close();
	}

	@Test
	public void CreateLoan() throws InterruptedException {
		System.out.println("Testing loan addition");
		
		AddLoan.loadPage(driver);
		AddLoan.getFirstBorrowerOption(driver).click();
		AddLoan.getFirstLenderOption(driver).click();
		AddLoan.getAPRInput(driver).sendKeys("1.25");
		AddLoan.getPrincipalInput(driver).sendKeys("1000000");
		AddLoan.getStartDateInput(driver).sendKeys("08172016");
		AddLoan.getLastUpdateInput(driver).sendKeys("08172016");
		AddLoan.getIsAdjustableInput(driver).click();
		// Thread.sleep((long) (10 * 1000));
		AddLoan.getSubmitButton(driver).click();
	}

	@BeforeClass
	public void beforeClass() {
		driver = DriverManager.getInstance().driver;
	}

	@AfterClass
	public void afterClass() {
	}
}
