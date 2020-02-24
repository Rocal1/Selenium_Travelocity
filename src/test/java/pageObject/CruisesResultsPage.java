package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.Base;

public class CruisesResultsPage extends Base{


		public WebDriver driver;

		public CruisesResultsPage(WebDriver driver) {
			this.driver = driver;
		}

		private By Destination = By.id("destination-select");
		private By TenToFourteenNights = By.cssSelector("[name=\"length-10-14\"]");
		private By PriceWithDiscount = By.className("card-price");
		private By PriceWithOutDiscount = By.className("strikeout-price-card");
		private By DiscountAmount = By.cssSelector("[class=\"message-flag flex-flag\"]");
		private By Select = By.id("selectSailingButton-AQoCbXMSAm9wGIDgrdqELiALKgNjaXYyAml0OgNjaXZCAml0-OP");
		private By Title = By.className("title-on-ship-image");
		
		public WebElement getDestination() {
			return driver.findElement(Destination);
		}
		public List<WebElement> getTenToFourteenNights() {
			return driver.findElements(TenToFourteenNights);
		}
		public List<WebElement> getPriceWithDiscount() {
			return driver.findElements(PriceWithDiscount);
		}
		public List<WebElement> getPriceWithOutDiscount() {
			return driver.findElements(PriceWithOutDiscount);
		}
		public List<WebElement> getDiscountAmount() {
			return driver.findElements(DiscountAmount);
		}
		public List<WebElement> getSelect() {
			return driver.findElements(Select);
		}
		public List<WebElement> getTitle() {
			return driver.findElements(Title);
		}
}
