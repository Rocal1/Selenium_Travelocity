package tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pageObject.FlightResultsGoingPage;
import pageObject.FlightResultsReturnPage;
import pageObject.HotelPage;
import pageObject.HotelResultsPage;
import pageObject.LandingPage;
import pageObject.PaymentPage;
import pageObject.TripDetailPage;
import resources.Base;

public class Excercise2Test extends Base {


	@Test(description = "Step 1 and 2", priority = 1)
	public void searching_a_flight_hotel() {
		LandingPage l = new LandingPage(driver);
		l.getFlightsAndHotel().click();
		// FROM TO
		l.getFromFH().sendKeys("LAS");
		l.getFirstOptionDisplayed().click();
		l.getToFH().sendKeys("LAX");
		l.getFirstOptionDisplayed().click();
		// DATAPICKER (not able to search from actual day)
		l.getDatePickerDepartingFH().click();
		String Today = l.getDaysEnables().get(0).getAttribute("data-day");
		l.getNextMonth().click();
		l.getNextMonth().click();
		l.getDaysEnables().get(Integer.parseInt(Today)).click();// Actual day, 2 month in future

		l.getDatePickerReturningFH().click();
		l.getDaysEnables().get(Integer.parseInt(Today) + 9).click();// 4(default) + 9 = 13 days after
		l.getSearchFH().click();
	}

	@Test(description = "Step 3", priority = 2)
	public void verify_validationsPage() {
		// Checking validations
		HotelResultsPage h = new HotelResultsPage(driver);
		assertTrue(h.getSortByPrice().isDisplayed());
		assertFalse(h.getHotel().isEmpty());
	}

	@Test(description = "Step 4 and 5", priority = 3)
	public void Sort_byPrice_and_Select3stars() throws InterruptedException {
		HotelResultsPage h = new HotelResultsPage(driver);
		h.getSortByPrice().click();
		System.out.println("Price Filter clicked");
		//new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfAllElements(h.getHotelPrices()));
		//new WebDriverWait(driver, 20).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
			
		Thread.sleep(10000); //WAIT LOADING POPUP FINISH
		
		// PRINT & CHECK SORT
		List<WebElement> HotelPriceList = h.getHotelPrices();
		double PreviousPriceNumber = 0;
		for (int i = 0; i < HotelPriceList.size(); i++) {
			String Price = HotelPriceList.get(i).getText().trim().replaceAll("[^0-9]", "");

			System.out.println(Price);
			double PriceNumber = Double.parseDouble(Price);
			if (PriceNumber >= PreviousPriceNumber) {
				PreviousPriceNumber = PriceNumber;
			} else
				assertTrue(false);
		}
		System.out.println("Verified List is sorted");
		
		List<WebElement> StarsList = h.getStarsList();
		for (int i = 0; i < StarsList.size(); i++) {
			String Star = StarsList.get(i).getAttribute("title");
			if (Star.contentEquals("3.0")) {
				System.out.println("Position " + i + " with 3 stars");
				h.getHotel().get(i).click();
				break;
			}
		}
	}
	@Test(description = "Step 6 and 7", priority = 4)
	public void Verify_hotel_Selected()  {
		HotelPage h=new HotelPage(driver);
		
		new WebDriverWait(driver, 15).until(ExpectedConditions.numberOfWindowsToBe(2));	
		String currentWindow = driver.getWindowHandle();  //will keep current window to switch back
		for(String winHandle : driver.getWindowHandles()){
		   if (driver.switchTo().window(winHandle).getTitle().contains("Book")) {
			 driver.switchTo().window(winHandle).getTitle();
			 System.out.println("TAB SWITCHED");
		     break;
		   } 
		   else {
		      driver.switchTo().window(currentWindow);
		   } 
		}
		Actions actions = new Actions(driver);
		actions.moveToElement(h.getSelectRoom().get(0));
		actions.click(h.getSelectRoom().get(0));
		actions.perform();	
	}
	
	@Test(description = "Step 8", priority = 5)
	public void select_departing_flight() throws InterruptedException  {
		FlightResultsGoingPage f = new FlightResultsGoingPage(driver);
		//WebDriverWait wait = new WebDriverWait(driver, 10); 
		//WebElement SelectButton = wait.until(ExpectedConditions.elementToBeClickable(f.getSelectButton().get(0)));
		//SelectButton.click();
		
		Thread.sleep(10000); //WAIT LOADING FINISH
		f.getSelectButton().get(0).click();
		System.out.println("Select clicked");
		Actions actions = new Actions(driver);
		actions.moveToElement(f.getSelectFareButton().get(0));
		actions.click(f.getSelectFareButton().get(0));
		actions.perform();
		System.out.println("Flight Going Selected");
	}
	
	@Test(description = "Step 9",priority = 6)
	public void select_returning_flight() throws InterruptedException {
		FlightResultsReturnPage f = new FlightResultsReturnPage(driver);
		//WebDriverWait wait = new WebDriverWait(driver, 10); 
		//WebElement SelectButton = wait.until(ExpectedConditions.elementToBeClickable(f.getSelectButton().get(0)));
		//SelectButton.click();
				
		Thread.sleep(10000); //WAIT LOADING FINISH
		f.getSelectButton().get(0).click();
		System.out.println("Select clicked");
		Actions actions = new Actions(driver);
		actions.moveToElement(f.getSelectFareButton().get(0));
		actions.click(f.getSelectFareButton().get(0));
		actions.perform();
		System.out.println("Flight Return Selected");		
	}
	
	@Test(description = "Step 12 and 13",priority = 7)
	public void TripDetails_and_WhoIsTravelling() throws InterruptedException {
		TripDetailPage t = new TripDetailPage(driver);
		t.getNext().click();
		Thread.sleep(10000); //WAIT LOADING POPUP FINISH
		PaymentPage p = new PaymentPage(driver);
		assertTrue(p.getFirstName().isDisplayed());
		p.getFirstName().sendKeys("Rodrigo");
		assertTrue(p.getLastName().isDisplayed());
		p.getLastName().sendKeys("Calabretta");
		assertTrue(p.getPhone().isDisplayed());
		p.getPhone().sendKeys("123456");;
	}
	
}
