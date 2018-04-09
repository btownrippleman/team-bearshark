package main.test.driver;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
	private static DriverManager driverManager = null;
	public static DriverManager getInstance() {
		if (driverManager == null)
			driverManager = new DriverManager();
		return driverManager;
	}
	
	private DriverManager() {
		File chromeDriverFile = new File("C:/Git_Repos/team-bearshark-loan-delivery-service/seleniumdrivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chromeDriverFile.getAbsolutePath());
		driver = new ChromeDriver();
	}
	public WebDriver driver = null;
	public void close() {
		driver.quit();
	}
}