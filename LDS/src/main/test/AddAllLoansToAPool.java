package main.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.test.driver.DriverManager;
import main.test.pom.AddPool;
import main.test.pom.Index;

public class AddAllLoansToAPool {
	WebDriver wd;
	
	@AfterSuite
	public void closeTest() {
		DriverManager.getInstance().close();
	}

	@Test
	public void AddPool() throws InterruptedException {
		System.out.println("Adding all loans to pool");
		
		String rowTag = "tr";
		Index.loadPage(wd);
		Index.selectLoans(wd).click();
		List<WebElement> rows = wd.findElements(By.tagName(rowTag));
		int numberOfRows = rows.size();
		for (int i = 2; i <= numberOfRows ; i++) {

			AddPool.checkbox(wd, i).click();
		}
		AddPool.addSelectedLoansToPool(wd).click();

		String s = Index.alert(wd).getText();
		if (s.compareTo("Success! Created Pool and added to Pool table") != 0) {
			Assert.fail("Failed to add loans to pool");
			System.out.println("Failed");
		}
		else
			System.out.println("AddAllLOanstoAPool was a success.");
	}

	@BeforeClass
	public void beforeClass() {
		wd = DriverManager.getInstance().driver;
	}
}
