package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pageObject.CruisePage;
import pageObject.CruisesResultsPage;
import pageObject.LandingPage;
import resources.Base;

public class Excercise5Test extends Base {
	

	@Test(description = "Step 1, 2 adn 3", priority = 1)
	public void searching_a_cruise() throws InterruptedException {
		LandingPage l = new LandingPage(driver);
		l.getCruises().click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(l.getGoingToCruises()));

		Select dropdown = new Select(l.getGoingToCruises());
		dropdown.selectByVisibleText("Europe");

		l.getDatePickerDepartingC().click();
		l.getDaysEnables().get(0).click();

		l.getSearchC().click();
	}

	@Test(description = "Step 4, 5 and 6", priority = 2)
	public void verifyDestinations_and_SetFilter() throws InterruptedException {
		CruisesResultsPage c = new CruisesResultsPage(driver);
		assertEquals(c.getDestination().getText(), "Europe");
		System.out.println("Results in Europe");
		Actions actions = new Actions(driver);
		actions.moveToElement(c.getTenToFourteenNights().get(1));
		actions.click(c.getTenToFourteenNights().get(1));		
		actions.perform();
		System.out.println("Filtering By 10 to 14 Days");
		Thread.sleep(8000); //WAIT LOADING POPUP

		if (c.getPriceWithOutDiscount().size() == c.getPriceWithDiscount().size()) {
			List<WebElement> NoDiscountList = c.getPriceWithOutDiscount();
			List<WebElement> DiscountList = c.getPriceWithDiscount();
			// Check Discounts and no Discounts on every Cruise
			for (int i = 0; i < NoDiscountList.size(); i++) {
				String Price1 = DiscountList.get(i).getText().trim().replaceAll("[^0-9]", "");
				Integer PriceWithDiscount = Integer.parseInt(Price1);
				String Price2 = NoDiscountList.get(i).getText().trim().replaceAll("[^0-9]", "");
				Integer PriceWithOutDiscount = Integer.parseInt(Price2);

				System.out.println("Cruise number " + (i + 1) + " has a price of $" + PriceWithOutDiscount
						+ " but with discount is $" + PriceWithDiscount);
			}
		} else {
			System.out.println("Not all the cruises has discount");
		}
	}

	@Test(description = "Step 7 and 8", priority = 3)
	public void selectCuiseWithMoreDiscount() throws InterruptedException {
		CruisesResultsPage c = new CruisesResultsPage(driver);

		List<WebElement> DiscountAmount = c.getDiscountAmount();
		// Check Discounts and no Discounts on every Cruise
		int BestCruise = 0;
		int BestCruiseIndex = 0;
		System.out.println("Start checking discount on every cruise");
		for (int i = 0; i < DiscountAmount.size(); i++) {
			String Discount = DiscountAmount.get(i).getText().trim().replaceAll("[^0-9]", "");
			Integer ActualDiscount = Integer.parseInt(Discount);
			System.out.println("Discount amount is " + ActualDiscount+ "%");
			if (ActualDiscount > BestCruise) {
					BestCruise=ActualDiscount;
					BestCruiseIndex = i;
				}
			}

		System.out.println("The cruise with the best discount was the number:" + (BestCruiseIndex + 1));
		String titulo = c.getTitle().get(BestCruiseIndex).getText();
		c.getSelect().get(BestCruiseIndex).click();

		CruisePage cp = new CruisePage(driver);

		new WebDriverWait(driver, 15).until(ExpectedConditions.numberOfWindowsToBe(2));
		String currentWindow = driver.getWindowHandle(); // will keep current window to switch back
		for (String winHandle : driver.getWindowHandles()) {
			if (driver.switchTo().window(winHandle).getTitle().contentEquals("Cruise | Travelocity")) {
				driver.switchTo().window(winHandle).getTitle();
				System.out.println("TAB SWITCHED");
				break;
			} else {
				driver.switchTo().window(currentWindow);
			}
		}
		assertTrue(titulo.equalsIgnoreCase(cp.getSmallTitle().getText()));
		System.out.println("Cruise correct selected");
	}

}
