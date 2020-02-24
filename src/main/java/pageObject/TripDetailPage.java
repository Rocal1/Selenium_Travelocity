package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TripDetailPage {
	// DECLARE THE WEBDRIVER
	public WebDriver driver;

	// SET THE CONSTRUCTOR (to assign/connect the driver from the page to the driver test)
	public TripDetailPage(WebDriver driver) {
		this.driver = driver;
	}

	// WebElements(Private)
	private By TotalPrice = By.xpath("//div[2]/section[1]/div/div[2]/div/div[1]/span[2]");
	private By Summary = By.cssSelector("[class=\"flightSummaryContainer\"]");
	private By Continue = By.id("bookButton");
	private By Next = By.cssSelector("[class=\"section-footer\"] [class=\"btn-primary btn-action\"]");

	
	
	// Methods (public)
	public WebElement getTotalPrice() {
		return driver.findElement(TotalPrice);
	}
	public WebElement getSummary() {
		return driver.findElement(Summary);
	}
	public WebElement getContinue() {
		return driver.findElement(Continue);
	}
	public WebElement getNext() {
		return driver.findElement(Next);
	}
}
