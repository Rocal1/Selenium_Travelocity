package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CruisePage extends BasePage {

	@FindBy(css = "[class=\"small-title trip-title\"]")
	private WebElement smallTitle;

	public CruisePage(WebDriver driver) {
		super(driver);
	}

	public String getSmallTitle() {
		return smallTitle.getText();
	}
	

	public CruisePage switchToCruisePage() {
		switchToNextWindows("Cruise | Travelocity");
		return this;
	}

}
