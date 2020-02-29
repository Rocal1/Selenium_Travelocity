package com.tests;

import org.testng.annotations.Test;

import com.pages.FlightResultsPage;
import com.pages.LandingPage;
import com.pages.PaymentPage;
import com.pages.TripDetailPage;
import com.travelocity.BaseTest;

public class Excercise1Test extends BaseTest {

	@Test(description = "Exercise 1")
	public void excercise1() {
		LandingPage landingPage = new LandingPage(myDriver.getDriver());
		landingPage.searchFlight("LAS", "LAX");
		FlightResultsPage flightResultsPage = landingPage.clickOnSearchFlight();
		softAssert().assertTrue(flightResultsPage.SortByIsDisplayed(), "sortBy element is not displayed on page");
		softAssert().assertTrue(flightResultsPage.verifyEveryResult(), "Flight Duration, Select Button and Flight Details are not present on every result");
		softAssert().assertTrue(flightResultsPage.verifyFlightResultsSorted(),"Verified List and is not sorted");
		flightResultsPage.selectGoingFlight();
		TripDetailPage tripDetailPage = flightResultsPage.selectReturningFlight();
		tripDetailPage.switchToTripDetailsTab();
		softAssert().assertTrue(tripDetailPage.TotalPriceIsDisplayed(), "Total Price is not present");
		softAssert().assertTrue(tripDetailPage.SummaryIsDisplayed(), "Summary is not present");
		tripDetailPage.isTextPresent("Price Guarantee");
		PaymentPage paymentPage = tripDetailPage.clickOnContinue();
		softAssert().assertTrue(paymentPage.FirstNameIsDisplayed(), "First Name is not present");
		softAssert().assertTrue(paymentPage.LastNameIsDisplayed(), "Last Name is not present");
		softAssert().assertTrue(paymentPage.PhoneNumberIsDisplayed(), "Phone Number is not present");
		paymentPage.setNamesWhoIsTravelling("Rodrigo", "Calabretta");
		paymentPage.setPhoneWhoIsTravelling("123456789");
		softAssert().assertAll();
	}

}
