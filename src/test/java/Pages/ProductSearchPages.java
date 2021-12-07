package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductSearchPages {
	WebDriver driver;
	By logo = By.id("nav-logo-sprites");
	By txt_SearchBox = By.id("twotabsearchtextbox");
	By dropDown_sort = By.id("a-autoid-0-announce");
	By highToLowOption = By.xpath("//*[@id='s-result-sort-select_2']");

	public ProductSearchPages(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement verifyLogoLoaded() {
		return driver.findElement(logo);
	}

	public void searchProduct(String product_name) {

		driver.findElement(txt_SearchBox).sendKeys(product_name);
		driver.findElement(txt_SearchBox).submit();

	}

	public void sortProduct() {

		driver.findElement(dropDown_sort).click();
		driver.findElement(highToLowOption).click();

	}

	public void selectProduct(Integer rowNumber) {
Integer i = rowNumber+1;
		driver.findElement(By.xpath("//*[@id='search']/div[1]/div[1]/div/span[3]/div[2]/div[" + i
				+ "]/div/span/div/div/div[2]/div[2]/div/div/div[1]/h2/a")).click();

	}

	public boolean verifyProduct(String productCode) {

		String productName = driver.findElement(By.id("productTitle")).getText();
		boolean status = false;
		if (productName.contains(productCode)) {
			status = true;
			

		}
		return status;
	}
}
