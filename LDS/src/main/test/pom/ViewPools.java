package main.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ViewPools {

	private static final String url = "http://localhost:7001/LDS/ctrl/viewPools";
	
	public static void loadPage(WebDriver wd) {
		wd.get(url);
	}

	public static int getRowCount(WebDriver wd) {
		return wd.findElements(By.tagName("tr")).size();
	}
	
	public static String[] get(WebDriver wd, int row) {
		int trSize = wd.findElements(By.tagName("tr")).size() - 1;
		String s = "html/body/div[2]/table/tbody/tr[" + (row + 1) + "]";
		String[] result = new String[trSize];
		for (int i = 0; i < 4; i++) {			
				String t1 = s + "/td[" + (i+1) + "]";	
				WebElement k = wd.findElement(By.xpath(t1));
				result[i] = new String (k.getText());
		}

		return result;
	}
}
