package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage {
	// DECLARE THE WEBDRIVER
	public WebDriver driver;

	// SET THE CONSTRUCTOR (to assign/connect the driver from the page to the driver test)
	public PaymentPage(WebDriver driver) {
		this.driver = driver;
	}

	// WebElements(Private)
	private By FirstName = By.id("firstname[0]");
	private By LastName = By.id("lastname[0]");
	private By Phone = By.id("phone-number[0]");
	
	
	// Methods (public)
	public WebElement getPhone() {
		return driver.findElement(Phone);
	}
	public WebElement getLastName() {
		return driver.findElement(LastName);
	}
	public WebElement getFirstName() {
		return driver.findElement(FirstName);
	}
}
