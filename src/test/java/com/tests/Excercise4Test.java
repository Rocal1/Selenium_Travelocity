package com.tests;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import com.pages.LandingPage;
import com.travelocity.BaseTest;

public class Excercise4Test extends BaseTest {
	
	@Test(description = "Exercise 4")
	public void excercise4() {
		LandingPage landingPage = new LandingPage(myDriver.getDriver());
		landingPage.searchFlightAndHotel("LAS", "LAX", 13, 14);
		landingPage.clickOnSearchFlightAndHotel();
		assertTrue(landingPage.checkMessagePresent("Your partial check-in and check-out dates must fall within your arrival and departure dates. Please review your dates."), "Error Message is not present in page");
	}
	
}
