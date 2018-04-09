/**
*	Test "Add Selected Loans To Pool"
*/

package main.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddPool {

	private static final String url = "http://localhost:7001/LDS/ctrl/addLoan";

	private static final String _addSelectedLoansToPool = "//*[@id=\"submit\"]";
	private static final String _cancel                 = "//*[@id=\"cancel\"]";

	public static void loadPage(WebDriver wd) {
		wd.get(url);
	}

	public static WebElement addSelectedLoansToPool(WebDriver wd) {
		return wd.findElement(By.xpath(_addSelectedLoansToPool));
	}

	public static WebElement cancel(WebDriver wd) {
		return wd.findElement(By.xpath(_cancel));
	}
	
	public static WebElement checkbox(WebDriver wd, int index) {
		String s = "/html/body/div[2]/form/table/tbody/tr[" + index + "]/td[1]/input";		
		return wd.findElement(By.xpath(s));
	}
}
