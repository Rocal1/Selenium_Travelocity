package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HotelPage {
	// DECLARE THE WEBDRIVER
	public WebDriver driver;

	// SET THE CONSTRUCTOR
	public HotelPage(WebDriver driver) {
			this.driver = driver;
		}
	
	private By SelectRoom = By.cssSelector("[class=\"btn btn-secondary btn-sub-action book-button\"]");
	
	
	public List<WebElement> getSelectRoom() {
		return driver.findElements(SelectRoom);
	}
}
