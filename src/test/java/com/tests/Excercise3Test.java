package com.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.pages.HotelResultsPage;
import com.pages.LandingPage;
import com.travelocity.BaseTest;

public class Excercise3Test extends BaseTest {

	@Test(description = "Exercise 3")
	public void excercise3() {
		LandingPage landingPage = new LandingPage(myDriver.getDriver());
		HotelResultsPage hotelResultsPage = landingPage.searchHotel("Montevideo,Uruguay");
		assertTrue(hotelResultsPage.verifySponsorOnFirstResult(), "First Hotel displayed is not sponsorized");
	}
}
