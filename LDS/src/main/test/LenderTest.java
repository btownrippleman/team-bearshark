package main.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import main.test.driver.DriverManager;

public class LenderTest {
	WebDriver driver = null;

	@AfterSuite
	public void closeTest() {
		DriverManager.getInstance().close();
	}

	@Test(priority = 1)
	public void Launch() {
		System.out.println("Test Annotation ");
		String actualtitle = driver.getTitle();
		String expectedTitle = "Bank Registration";
		Assert.assertEquals(actualtitle, expectedTitle);
	}

	@Test(priority = 2)
	public void InputCheck() {
		System.out.println("Testing lender addition");
		driver.findElement(By.xpath("//*[@id=\"col-md-4\"]")).sendKeys("Quinn");
		driver.findElement(By.xpath("//*[@id=\"col-md-0\"]")).sendKeys("40912");
		driver.findElement(By.xpath("//*[@value=\"Register\"]")).click();
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class");
		driver = DriverManager.getInstance().driver;
		driver.get("http://localhost:7001/LDS/pages/addBank.jsp");
	}
}
