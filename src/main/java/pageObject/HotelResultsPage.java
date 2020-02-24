package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HotelResultsPage {
	// DECLARE THE WEBDRIVER
	public WebDriver driver;

	// SET THE CONSTRUCTOR
	public HotelResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	// WebElements(Private)
	private By SortByPrice = By.xpath("//span[text()='Price']/../..");
	private By HotelPrice = By.cssSelector("[class=\"actualPrice price fakeLink \"]");
	private By TreeStars = By.cssSelector("[title=\"3.0\"]");
	private By StarsList = By.cssSelector("[class=\"starRating secondary\"] [class*=\"icon-stars\"]");
	private By Hotel = By.cssSelector("#resultsContainer [data-hotelid]");
	
	
	public WebElement getSortByPrice() {
		return driver.findElement(SortByPrice);
	}
	public List<WebElement> getHotelPrices() {
		return driver.findElements(HotelPrice);
	}
	public List<WebElement> getTreeStars() {
		return driver.findElements(TreeStars);
	}
	public List<WebElement> getStarsList() {
		return driver.findElements(StarsList);
	}
	public List<WebElement> getHotel() {
		return driver.findElements(Hotel);
	}
}
