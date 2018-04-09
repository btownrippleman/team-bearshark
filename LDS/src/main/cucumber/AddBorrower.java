package main.cucumber;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import main.test.driver.DriverManager;
import main.test.pom.Index;

@SuppressWarnings("deprecation")
public class AddBorrower {
	private WebDriver driver = null;
	private main.test.pom.AddBorrower page;
	
	@SuppressWarnings("static-access")
	@Given("^I go to the create borrower form$")
	public void i_go_to_the_create_borrower_form() throws Throwable {
	    driver = DriverManager.getInstance().driver;
	    page = new main.test.pom.AddBorrower();
	    driver.get(page.url);
	}

	@SuppressWarnings("static-access")
	@Given("^My first name is \"([^\"]*)\"$")
	public void my_first_name_is(String arg1) throws Throwable {
	    page.firstName(driver).sendKeys(arg1);
	}

	@SuppressWarnings("static-access")
	@Given("^My last name is \"([^\"]*)\"$")
	public void my_last_name_is(String arg1) throws Throwable {
	    page.lastName(driver).sendKeys(arg1);
	}

	@SuppressWarnings("static-access")
	@Given("^My zipcode is \"([^\"]*)\"$")
	public void my_zipcode_is(String arg1) throws Throwable {
	    page.zipCode(driver).sendKeys(arg1);
	}

	@SuppressWarnings("static-access")
	@Given("^My credit score is \"([^\"]*)\"$")
	public void my_credit_score_is(String arg1) throws Throwable {
	    page.creditScore(driver).sendKeys(arg1);
	}

	@SuppressWarnings("static-access")
	@When("^I click on the add button$")
	public void i_click_on_the_add_button() throws Throwable {
		page.submit(driver).click();
	}

	@SuppressWarnings("deprecation")
	@Then("^The borrower is created$")
	public void the_borrower_is_created() throws Throwable {
	    String message = Index.alert(driver).getText();
	    DriverManager.getInstance().close();
	    Assert.assertTrue(message.equals("Success! Created Borrower"));
	}
}
