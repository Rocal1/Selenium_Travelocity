package com.pages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {



	@FindBy(id = "tab-flight-tab-hp")
	private WebElement flights;

	@FindBy(id = "flight-origin-hp-flight")
	private WebElement from;

	@FindBy(id = "flight-destination-hp-flight")
	private WebElement to;

	@FindAll(@FindBy(css = "[class=\"results-item\"]"))
	private List<WebElement> firstDisplayed;

	@FindBy(id = "flight-departing-hp-flight")
	private WebElement datePickerDeparting;

	@FindBy(id = "flight-returning-hp-flight")
	private WebElement datePickerReturning;

	@FindAll(@FindBy(css = "[class=\"datepicker-cal-date\"]"))
	private List<WebElement> datepickerEnableDays;

	@FindBy(css = "[class=\"datepicker-cal-date start\"]")
	private WebElement datepickerStartDay;

	@FindBy(css = "[class=\"datepicker-cal-date end\"]")
	private WebElement datepickerEndDay;

	@FindAll(@FindBy(css = "[class=\"datepicker-cal-date highlight\"]"))
	private List<WebElement> datepickerhighlightDays;

	@FindBy(css = "[class*=\"btn-secondary next\"]")
	private WebElement nextMonth;

	@FindBy(css = "#section-flight-tab-hp .btn-primary")
	private WebElement search;

	@FindBy(id = "tab-package-tab-hp")
	private WebElement flightsAndHotel;

	@FindBy(id = "package-origin-hp-package")
	private WebElement fromFH;

	@FindBy(id = "package-destination-hp-package")
	private WebElement toFH;

	@FindBy(id = "package-departing-hp-package")
	private WebElement datePickerDepartingFH;

	@FindBy(id = "package-returning-hp-package")
	private WebElement datePickerReturningFH;

	@FindBy(id = "search-button-hp-package")
	private WebElement searchFH;

	@FindBy(id = "partialHotelBooking-hp-package")
	private WebElement checkbox;

	@FindBy(id = "package-checkin-hp-package")
	private WebElement checkIn;

	@FindBy(id = "package-checkout-hp-package")
	private WebElement checkOut;

	@FindBy(id = "hotel-destination-hp-hotel")
	private WebElement goingTo;

	@FindBy(css = "[id=\"gcw-hotel-form-hp-hotel\"] [class=\"btn-primary btn-action gcw-submit \"]")
	private WebElement searchH;

	@FindBy(id = "tab-cruise-tab-hp")
	private WebElement cruises;

	@FindBy(css = ".cruise-destination")
	private WebElement goingToCruises;

	@FindBy(id = "cruise-start-date-hp-cruise")
	private WebElement datePickerDepartingC;

	@FindBy(id = "cruise-end-date-hp-cruise")
	private WebElement datePickerReturningC;

	@FindBy(css = "#gcw-cruises-form-hp-cruise .gcw-submit")
	private WebElement searchC;

	public LandingPage(WebDriver driver) {
		super(driver);
	}


	public LandingPage searchFlight(String goingFrom, String goingTo) {
		click(flights);
		from.sendKeys(goingFrom);
		click(firstDisplayed.get(0));
		to.sendKeys(goingTo);
		click(firstDisplayed.get(0));
		click(datePickerDeparting);
		Integer tomorrow = Integer.parseInt(datepickerEnableDays.get(1).getAttribute("data-day"));
		click(datepickerEnableDays.get(1));
		click(datePickerReturning);
		click(nextMonth);
		click(nextMonth);
		click(datepickerEnableDays.get(tomorrow - 1));
		return this;
	}

	public FlightResultsPage clickOnSearchFlight() {
		click(search);
		return new FlightResultsPage(getDriver());
	}

	/**
	 * 
	 * Selects actual day but 2 month in future as departure date
	 * @return 
	 * 
	 */
	public LandingPage searchFlightAndHotel(String goingFrom, String goingTo, Integer flightDays) {
		click(flightsAndHotel);
		fromFH.sendKeys(goingFrom);
		click(firstDisplayed.get(0));
		toFH.sendKeys(goingTo);
		click(firstDisplayed.get(0));
		click(datePickerDepartingFH);
		Integer tomorrow = Integer.parseInt(datepickerEnableDays.get(0).getAttribute("data-day"));
		click(nextMonth);
		click(nextMonth);
		click(datepickerEnableDays.get(tomorrow - 1));
		click(datePickerReturningFH);
		if (flightDays > 4) {
			click(datepickerEnableDays.get(flightDays - 5));
		}
		return this;
	}

	public LandingPage searchFlightAndHotel(String goingFrom, String goingTo, Integer flightDays, Integer hotelDays) {
		searchFlightAndHotel(goingFrom, goingTo, flightDays);
		click(checkbox);
		click(checkIn);
		click(datepickerStartDay);
		checkOut.click();
		if (flightDays < hotelDays) {
			click(datepickerEnableDays.get(hotelDays - flightDays - 1));
		} else if (flightDays > hotelDays) {
			click(datepickerhighlightDays.get(hotelDays - 2));
		} else {
			click(datepickerEndDay);
		}
		return this;
	}

	public HotelResultsPage clickOnSearchFlightAndHotel() {
		click(searchFH);
		return new HotelResultsPage(getDriver());
	}


	public HotelResultsPage searchHotel(String destination) {
		goingTo.sendKeys(destination);
		click(firstDisplayed.get(0));
		click(searchH);
		return new HotelResultsPage(getDriver());
	}

	public LandingPage searchCruise(String goingTo) {
		click(cruises);
		waitElementClickable(goingToCruises);
		selectOptionFromDropdown(goingToCruises, goingTo);
		click(datePickerDepartingC);
		click(datepickerEnableDays.get(0));
		return this;
	}

	public CruisesResultsPage clickOnSearchCruise() {
		click(searchC);
		return new CruisesResultsPage(getDriver());
	}

}
