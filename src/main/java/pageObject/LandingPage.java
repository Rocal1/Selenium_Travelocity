package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

	// DECLARE THE WEBDRIVER
	public WebDriver driver;

	// SET THE CONSTRUCTOR (to assign/connect the driver from the page to the driver test)
	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}

	// WebElements(Private)
	//Flight TAB
	private By Flights = By.id("tab-flight-tab-hp");
	private By From = By.id("flight-origin-hp-flight");
	private By To = By.id("flight-destination-hp-flight");
	private By FirstDisplayed=By.cssSelector("li.results-item:nth-child(1)");
	private By DatePickerDeparting = By.id("flight-departing-hp-flight");
	private By DatePickerReturning = By.id("flight-returning-hp-flight");
	private By DaysEnable= By.cssSelector("[class=\"datepicker-cal-date\"]");
	private By NextMonth = By.cssSelector("[class*=\"btn-secondary next\"]");
	private By Search = By.cssSelector("#section-flight-tab-hp .btn-primary");
	
	//Flight+Hotel TAB
	private By FlightsAndHotel = By.id("tab-package-tab-hp");
	private By FromFH = By.id("package-origin-hp-package");
	private By ToFH = By.id("package-destination-hp-package");
	private By DatePickerDepartingFH = By.id("package-departing-hp-package");
	private By DatePickerReturningFH = By.id("package-returning-hp-package");
	private By SearchFH = By.id("search-button-hp-package");
	private By Checkbox = By.id("partialHotelBooking-hp-package");
	private By Checkin = By.id("package-checkin-hp-package");
	private By Checkout = By.id("package-checkout-hp-package");

	//Hotel Only
	private By GoingTo = By.id("hotel-destination-hp-hotel");
	private By SearchH = By.id("search-button-hp-package");
	//Cruises
	private By Cruises = By.id("tab-cruise-tab-hp");
	private By GoingToCruises = By.cssSelector(".cruise-destination");
	private By DatePickerDepartingC = By.id("cruise-start-date-hp-cruise");
	private By DatePickerReturningC = By.id("cruise-end-date-hp-cruise");
	private By SearchC = By.cssSelector("#gcw-cruises-form-hp-cruise .gcw-submit");
	
	
	// Methods Getters(Public)
	//Flight TAB
	public WebElement getFlights() {
		return driver.findElement(Flights);
	}
	public WebElement getFrom() {
		return driver.findElement(From);
	}
	public WebElement getTo() {
		return driver.findElement(To);
	}
	public WebElement getFirstOptionDisplayed() {
		return driver.findElement(FirstDisplayed);
	}
	public WebElement getDatePickerDeparting() {
		return driver.findElement(DatePickerDeparting);
	}
	public WebElement getDatePickerReturning() {
		return driver.findElement(DatePickerReturning);
	}
	public List<WebElement> getDaysEnables() {
		return driver.findElements(DaysEnable);
	}
	public WebElement getNextMonth() {
		return driver.findElement(NextMonth);
	}
	public WebElement getSearch() {
		return driver.findElement(Search);
	}
	//Flight+Hotel TAB
	public WebElement getFlightsAndHotel() {
		return driver.findElement(FlightsAndHotel);
	}
	public WebElement getFromFH() {
		return driver.findElement(FromFH);
	}
	public WebElement getToFH() {
		return driver.findElement(ToFH);
	}
	public WebElement getDatePickerDepartingFH() {
		return driver.findElement(DatePickerDepartingFH);
	}
	public WebElement getDatePickerReturningFH() {
		return driver.findElement(DatePickerReturningFH);
	}
	public WebElement getSearchFH() {
		return driver.findElement(SearchFH);
	}
	public WebElement getCheckbox() {
		return driver.findElement(Checkbox);
	}
	public WebElement getCheckin() {
		return driver.findElement(Checkin);
	}
	public WebElement getCheckout() {
		return driver.findElement(Checkout);
	}
	//Hotel Only
	public WebElement getGoingTo() {
		return driver.findElement(GoingTo);
	}
	public WebElement getSearchH() {
		return driver.findElement(SearchH);
	}
	//Cruises
	public WebElement getCruises() {
		return driver.findElement(Cruises);
	}
	public WebElement getGoingToCruises() {
		return driver.findElement(GoingToCruises);
	}
	public WebElement getDatePickerDepartingC() {
		return driver.findElement(DatePickerDepartingC);
	}
	public WebElement getDatePickerReturningC() {
		return driver.findElement(DatePickerReturningC);
	}
	public WebElement getSearchC() {
		return driver.findElement(SearchC);
	}
}
