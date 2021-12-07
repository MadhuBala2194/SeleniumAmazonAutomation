package BaseClass;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;

import ConfigReader.ConfigReader;

public class BaseClass {

	WebDriver driver = null;
	ConfigReader reader = null;

	/**
	 * Method to wait for the element to load
	 */
	public WebElement waitForElementToDisplay(WebDriver driver, WebElement element) {

		FluentWait wait = new FluentWait(driver);
		wait.withTimeout(Duration.ofSeconds(30));
		wait.pollingEvery(Duration.ofSeconds(5));
		wait.ignoring(NoSuchElementException.class);

		return element;
	}

	/**
	 * Method to choose browser and return driver instance
	 */
	public WebDriver chooseBrowser() {
		reader = new ConfigReader();
		String browser = reader.getBrowser();
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", reader.getDriverPathChrome());
			driver = new ChromeDriver();
		} else {

			System.setProperty("webdriver.gecko.driver", reader.getDriverPathFireFox());
			driver = new FirefoxDriver();
		}
		return driver;
	}

	/**
	 * Method to open application
	 */
	public void openApplication(WebDriver driver) throws InterruptedException {
		driver.navigate().to(reader.getApplicationUrl());
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
}
