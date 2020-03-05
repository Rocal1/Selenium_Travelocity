package com.tests;

import org.testng.annotations.Test;

import com.pages.CruisePage;
import com.pages.CruisesResultsPage;
import com.pages.LandingPage;

public class Excercise5Test extends BaseTest {
	
	@Test(description = "Exercise 5")
	public void excercise5() throws InterruptedException {
		LandingPage landingPage = new LandingPage(myDriver.getDriver());
		landingPage.searchCruise("europe");	
		CruisesResultsPage cruisesResultsPage = landingPage.clickOnSearchCruise();
		softAssert().assertEquals(cruisesResultsPage.getDestination(),"Europe", "Destination displayed is not the selected");
		cruisesResultsPage.selectFilterNight10To14();
		softAssert().assertTrue(cruisesResultsPage.verifyEveryCruiseWithAndWithoutDiscount(), "Not all the cruises has discount");
		int BestCruiseIndex = cruisesResultsPage.getCuiseIndexWithMoreDiscount();
		String title=cruisesResultsPage.getCruiseTitle(BestCruiseIndex);
		CruisePage cruisePage = cruisesResultsPage.selectCruise(BestCruiseIndex);
		cruisePage.switchToCruisePage();
		softAssert().assertTrue(title.equalsIgnoreCase(cruisePage.getSmallTitle()), "Cruise Name is not the cruise selected");
	}
}
