package com.pages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;



public class FlightResultsPage extends BasePage{
	

	
	@FindBy(id = "sortDropdown")
	private WebElement sortBy;
	
	@FindAll(@FindBy(xpath = "//span[text()='Select']/../.."))
	private List<WebElement> selectButton;
	
	@FindAll(@FindBy(xpath = "//span[text()='Select this fare']/../.."))
	private List<WebElement> selectFareButton;
	
	@FindAll(@FindBy(css = "[class=\"duration-emphasis\"]"))
	private List<WebElement> flightDuration;
	
	@FindAll(@FindBy(css = "[class=\"show-flight-details\"]"))
	private List<WebElement> flightDetails;
	
	@FindAll(@FindBy(css = "[data-test-id=\"offer-listing\"]"))
	private List<WebElement> results;
	
	@FindBy(css = "[class=\"toggle-pane fade open\"]")
	private WebElement selectFareContainer;
	

	public FlightResultsPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean SortByIsDisplayed() {
		return (sortBy.isDisplayed());
	}
	public boolean verifyEveryResult() {
		waitElementClickable(sortBy);
		Integer check1 = flightDuration.size();
		Integer check2 = selectButton.size();
		Integer check3 = flightDetails.size();
		return (check1 <= check2 && check1 == check3);
	}
	public boolean verifyFlightResultsSorted() {
		waitElementClickable(sortBy);
		selectOptionFromDropdown(sortBy, "duration:asc");
		waitElementClickable(sortBy);
		double PrevioustimeNumber=0;
		boolean sorted = true; 
		for (int i = 0; i < flightDuration.size(); i++) {
			String time= flightDuration.get(i).getText().trim().replaceAll("h", ".").replaceAll("[^0-9.]", "");
			double timeNumber = Double.parseDouble(time);
			if (timeNumber>=PrevioustimeNumber) {
				PrevioustimeNumber=timeNumber;
			}else { 
				sorted = false;
			}
		}
		return sorted;
	}

	public FlightResultsPage selectGoingFlight() {
		waitElementClickable(sortBy);
		click(selectButton.get(0));
		waitElementVisible(selectFareContainer);
		click(selectFareButton.get(0));
		return this;
	}
	
	public TripDetailPage selectReturningFlight() {
		waitElementClickable(sortBy);
		click(selectButton.get(0));
		waitElementVisible(selectFareContainer);
		click(selectFareButton.get(0));
		return new TripDetailPage(getDriver());		
	}
	
}
