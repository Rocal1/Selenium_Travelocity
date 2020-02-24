package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pageObject.LandingPage;
import resources.Base;

public class Excercise4Test extends Base {

	
	@Test(description = "Step 1", priority = 1)
	public void searching_a_flight() {
		LandingPage l = new LandingPage(driver);
		l.getFlightsAndHotel().click();
		// FROM TO
		l.getFromFH().sendKeys("LAS");
		l.getFirstOptionDisplayed().click();
		l.getToFH().sendKeys("LAX");
		l.getFirstOptionDisplayed().click();
		// DATAPICKER (not able to search from actual day)
		l.getDatePickerDepartingFH().click();
		l.getDaysEnables().get(0).click();
		l.getDatePickerReturningFH().click();
		l.getDaysEnables().get(1).click();
		// DATAPICKER CHECK
		l.getCheckbox().click();
		l.getCheckin().click();
		l.getDaysEnables().get(6).click();
		l.getCheckout().click();
		l.getDaysEnables().get(7).click();

		l.getSearchFH().click();

		String TextExpected = "Your partial check-in and check-out dates must fall within your arrival and departure dates. Please review your dates.";
		assertTrue(driver.getPageSource().contains(TextExpected));
		System.out.println("Error Message is displaying OK");
	}

	
}
