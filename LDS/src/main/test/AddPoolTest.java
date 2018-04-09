package main.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.test.driver.DriverManager;
import main.test.pom.AddPool;
import main.test.pom.Index;
import main.test.wait.WaitManager;

public class AddPoolTest {
	WebDriver wd;

	@AfterSuite
	public void closeTest() {
		DriverManager.getInstance().close();
	}

	@Test
	public void AddPool() throws InterruptedException {
		System.out.println("Testing pool addition");
		
		Index.loadPage(wd);
		Index.selectLoans(wd).click();
		AddPool.checkbox(wd, 2).click();
		AddPool.addSelectedLoansToPool(wd).click();
		WaitManager.WaitMillis(3000L);
		String s = Index.alert(wd).getText();
		if (s.compareTo("Success! Created Pool and added to Pool table") != 0) {
			Assert.fail("Failed to add loans to pool");
			System.out.println("Failed.");
		}
		else
			System.out.println("AddPoolTest was a success.");
	}

	@BeforeClass
	public void beforeClass() {
		wd = DriverManager.getInstance().driver;
	}
}
