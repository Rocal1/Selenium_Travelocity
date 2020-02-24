package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.Base;

public class CruisePage extends Base{


		public WebDriver driver;

		public CruisePage(WebDriver driver) {
			this.driver = driver;
		}

		private By SmallTitle = By.cssSelector("[class=\"small-title trip-title\"]");
		
		public WebElement getSmallTitle() {
			return driver.findElement(SmallTitle);
		}
}
