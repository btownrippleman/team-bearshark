package main.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddBorrower {
	public static String url = "http://localhost:7001/LDS/pages/addBorrower.jsp";
	
	public static WebElement firstName(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"col-md-4\"]"));
	}
	
	public static WebElement lastName(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"col-md-3\"]"));
	}
	
	public static WebElement creditScore(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"col-md-2\"]"));
	}
	
	public static WebElement zipCode(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"col-md-1\"]"));
	}
	
	public static WebElement submit(WebDriver driver) {
		return driver.findElement(By.xpath("/html/body/div/div[2]/form/div[5]/input[1]"));
	}
}
