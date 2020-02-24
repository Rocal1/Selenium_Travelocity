package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlightResultsReturnPage {

	// DECLARE THE WEBDRIVER
	public WebDriver driver;

	// SET THE CONSTRUCTOR (to assign/connect the driver from the page to the driver test)
	public FlightResultsReturnPage(WebDriver driver) {
		this.driver = driver;
	}


	// WebElements(Private)
		private By SelectButton = By.xpath("//span[text()='Select']/../..");
		private By SelectFareButton = By.xpath("//span[text()='Select this fare']/../..");
		private By Results = By.cssSelector("[data-test-id=\"offer-listing\"]");
		
		// Methods Getters(Public)
		public List<WebElement> getSelectButton() {
			return driver.findElements(SelectButton);
		}
		public List<WebElement> getSelectFareButton() {
			return driver.findElements(SelectFareButton);
		}
		public List<WebElement> getResults() {
			return driver.findElements(Results);
		}

}
