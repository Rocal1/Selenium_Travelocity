package com.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class TripDetailPage extends BasePage{
	
	
	@FindBy(xpath = "//div[2]/section[1]/div/div[2]/div/div[1]/span[2]")
	private WebElement totalPrice;
	
	@FindBy(css = "[class=\"flightSummaryContainer\"]")
	private WebElement summary;
	
	@FindBy(id = "bookButton")
	private WebElement continueButton;
	
	@FindBy(css = "[class=\"section-footer\"] [class=\"btn-primary btn-action\"]")
	private WebElement nextButton;
	
	public TripDetailPage(WebDriver driver) {
		super(driver);
	}
	
	
	public void switchToTripDetailsTab() {
		switchToNextWindows("Trip Detail | Travelocity");
		waitElementVisible(totalPrice);
	}
	public boolean TotalPriceIsDisplayed() {
		return (totalPrice.isDisplayed());
	}
	public boolean SummaryIsDisplayed() {
		return (summary.isDisplayed());
	}
	public PaymentPage clickOnContinue() {
		click(continueButton);
		return new PaymentPage(getDriver());
	}

	public PaymentPage clickOnNextButton() {
		clickWithActionsBuilder(nextButton);
		return new PaymentPage(getDriver());
	}
	
}
