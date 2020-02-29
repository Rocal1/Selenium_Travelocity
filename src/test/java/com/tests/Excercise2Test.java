package com.tests;

import org.testng.annotations.Test;

import com.pages.FlightResultsPage;
import com.pages.HotelPage;
import com.pages.HotelResultsPage;
import com.pages.LandingPage;
import com.pages.PaymentPage;
import com.pages.TripDetailPage;
import com.travelocity.BaseTest;

public class Excercise2Test extends BaseTest {

	@Test(description = "Exercise 2")
	public void excercise2() {
		LandingPage landingPage = new LandingPage(myDriver.getDriver());
		landingPage.searchFlightAndHotel("LAS", "LAX", 13);
		HotelResultsPage hotelResultsPage = landingPage.clickOnSearchFlightAndHotel();
		softAssert().assertTrue(hotelResultsPage.sortByPriceIsDisplayed(), "sortByPrice element is not displayed on page");
		softAssert().assertFalse(hotelResultsPage.hotelHasEmptyResults(), "There is no hotel displayed that matchs with your search");
		hotelResultsPage.clickOnSortByPrice();
		softAssert().assertTrue(hotelResultsPage.verifyHotelsResultsSorted(),"List of hotel is not sorted by Price");
		HotelPage hotelPage = hotelResultsPage.clickOnTheFirst3StarsHotel();
		hotelPage.switchToBookHotelTab();
		FlightResultsPage flightResultsPage = hotelPage.clickOnSelectRoom();
		flightResultsPage.selectGoingFlight();
		TripDetailPage tripDetailPage = flightResultsPage.selectReturningFlight();
		PaymentPage paymentPage = tripDetailPage.clickOnNextButton();
		softAssert().assertTrue(paymentPage.FirstNameIsDisplayed(), "First Name is not present");
		softAssert().assertTrue(paymentPage.LastNameIsDisplayed(), "Last Name is not present");
		paymentPage.setNamesWhoIsTravelling("Rodrigo", "Calabretta");		
		softAssert().assertAll();
	}
	
}
