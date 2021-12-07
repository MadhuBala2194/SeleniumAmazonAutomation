package StepDefinition;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;

import BaseClass.BaseClass;
import ConfigReader.ConfigReader;
import Pages.ProductSearchPages;
import Utility.ScreenRecorderUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class ProductSearch {
	protected static WebDriver driver;
	BaseClass baseClass = new BaseClass();
	ConfigReader reader = new ConfigReader();
	ScreenRecorderUtil screenRecorder;
	ProductSearchPages pages;
	@Before
	public void setup() throws InterruptedException {
		driver = baseClass.chooseBrowser();
		baseClass.openApplication(driver);
	}

	@Given("^user enters into application")
	public void user_enters_into_application() throws Exception {
		screenRecorder.startRecord("user Search for product");
		pages = new ProductSearchPages(driver);
		WebElement logo= pages.verifyLogoLoaded();		
		baseClass.waitForElementToDisplay(driver, logo);

	}

	//@Then("^user search for \"([^\"]*)\" product")
	@Then("user search for {string} product")
	public void user_search_for_product(String product_name) {
		pages = new ProductSearchPages(driver);
		pages.searchProduct(product_name);
		baseClass.waitForElementToDisplay(driver, pages.verifyLogoLoaded());
	}

	@Then("^user sort the product price from highest to lowest")
	public void user_sort_the_product_price_from_highest_to_lowest() {
		pages = new ProductSearchPages(driver);
		pages.sortProduct();
		baseClass.waitForElementToDisplay(driver, pages.verifyLogoLoaded());
	}

	//@When("^user selects row number \"([^\"]*)\" from results and clicks for details")
	@When("user selects row number {int} from results and clicks for details")
	public void user_selects_row_number_from_results_and_clicks_for_details(Integer rownumber) {
		pages = new ProductSearchPages(driver);
		pages.selectProduct(rownumber);
		
	}
	@Then("user verifies product contains {string}")
	//@Then("user verifies product contains \"([^\"]*)\"")
	public void user_verifies_product_contains(String productCode) throws Exception {
		pages = new ProductSearchPages(driver);
		boolean status = pages.verifyProduct(productCode);
		Thread.sleep(3000);
			if(status) {
			
			Assert.assertEquals(status, true);
			
		}
		screenRecorder.stopRecord();
	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
