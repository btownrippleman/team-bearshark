package main.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bearshark.jdbc.PoolDao;
import com.bearshark.pool.Pool;

import main.test.driver.DriverManager;
import main.test.pom.ViewPools;

public class ViewPoolsTest {
	
	WebDriver driver = null;
	
	@AfterSuite
	public void closeTest() {
		DriverManager.getInstance().close();
	}

	@Test
	public void ViewPools() throws ClassNotFoundException {
		
		PoolDao dao = new PoolDao();

		ViewPools.loadPage(driver);

		int count = ViewPools.getRowCount(driver);
		for (int i = 1; i < count; i++) {

			String[] s = ViewPools.get(driver, i);	
			for (String k : s)
				System.out.println("*** String: " + k);
			
			Long id = Long.parseLong(s[0]);
			Pool p = dao.getPoolById(id);
			if (p == null) {
				Assert.fail("Pool with ID (" + s[0] + ") does not exist!");
				System.out.println("Failed");
			}			
		}
	}

	@BeforeClass
	public void beforeClass() {
		driver = DriverManager.getInstance().driver;
	}
}
