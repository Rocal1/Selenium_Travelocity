package tests;

import org.testng.annotations.Test;

import pageObject.LandingPage;
import resources.Base;

public class Excercise3Test extends Base {

	@Test(description = "Step 1 and 2", priority = 1)
	public void searching_a_hotel() {
		LandingPage l = new LandingPage(driver);
		l.getGoingTo().sendKeys("Montevideo,Uruguay");
		l.getFirstOptionDisplayed().click();
		l.getSearchH();
	}

	@Test(description = "Step 3 and 4", priority = 2)
	public void Verify_sponsor() {

		// TODO Request more information HERE 
		// Option of receive a discount was not found in the page
	}

}
