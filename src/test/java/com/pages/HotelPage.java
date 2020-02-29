package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelPage extends BasePage{
	

	@FindBy(css = "[class=\"btn btn-secondary btn-sub-action book-button\"]")
	private WebElement selectRoom;
	
	
	public HotelPage(WebDriver driver) {
		super(driver);
	}

	public HotelPage switchToBookHotelTab() {
		switchToNextWindows("Book");
		waitElementClickable(selectRoom);
		return this;
	}
	public FlightResultsPage clickOnSelectRoom() {
		clickWithActionsBuilder(selectRoom);
		return new FlightResultsPage(getDriver());
	}

}
