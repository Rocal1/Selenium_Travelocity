package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.FlightResultsGoingPage;
import pageObject.FlightResultsReturnPage;
import pageObject.LandingPage;
import pageObject.PaymentPage;
import pageObject.TripDetailPage;
import resources.Base;

public class Excercise1Test extends Base {


	@Test(description = "Step 1" ,priority = 1)
	public void searching_a_flight() {
		LandingPage l = new LandingPage(driver);
		l.getFlights().click();
		// FROM TO
		l.getFrom().sendKeys("LAS");
		l.getFirstOptionDisplayed().click();
		l.getTo().sendKeys("LAX");
		l.getFirstOptionDisplayed().click();
		// DATAPICKER (not able to search from actual day)
		l.getDatePickerDeparting().click();
		String Tomorrow = l.getDaysEnables().get(1).getAttribute("data-day");
		l.getDaysEnables().get(1).click();
		l.getDatePickerReturning().click();
		l.getNextMonth().click();
		l.getNextMonth().click();
		l.getDaysEnables().get(Integer.parseInt(Tomorrow) - 1).click();
		l.getSearch().click();
	}

	@Test(description = "Step 2",priority = 2)
	public void verify_results_on_the_page() throws InterruptedException {
		FlightResultsGoingPage f = new FlightResultsGoingPage(driver);
		assertTrue(f.getSortby().isDisplayed());
		int check1 = f.getFlightDuration().size();
		int check2 = f.getSelectButton().size();
		int check3 = f.getFlightDetails().size();
		if (check1 <= check2 && check1 == check3) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	@Test(description = "Step 3 and 4",priority = 3)
	public void verify_sorted_results() throws InterruptedException {
		FlightResultsGoingPage f = new FlightResultsGoingPage(driver);
		Select dropdown= new Select(f.getSortby());
		dropdown.selectByVisibleText("Duration (Shortest)");	
		Thread.sleep(5000);
		List<WebElement> durationList = f.getFlightDuration();
		//PRINT & CHECK SORT
		double PrevioustimeNumber=0;
		for (int i = 0; i < durationList.size(); i++) {
			String time= durationList.get(i).getText().trim().replaceAll("h", ".").replaceAll("[^0-9.]", "");
			
			System.out.println(time);
			double timeNumber = Double.parseDouble(time);
			if (timeNumber>=PrevioustimeNumber) {
				PrevioustimeNumber=timeNumber;
			}else 
				assertTrue(false);
		}
		System.out.println("Verified List is sorted");
		
		Thread.sleep(10000); //WAIT LOADING FINISH
		f.getSelectButton().get(0).click();
		System.out.println("Select clicked");
		Actions actions = new Actions(driver);
		actions.moveToElement(f.getSelectFareButton().get(0));
		actions.click(f.getSelectFareButton().get(0));
		actions.perform();
		System.out.println("Flight Going Selected");
	}
	
	@Test(description = "Step 5",priority = 4)
	public void select_returning_flight() throws InterruptedException {
		FlightResultsReturnPage f = new FlightResultsReturnPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10); 
		
		//WAIT DEPARTURE SELECTED
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("selected-departure")));
		
		f.getSelectButton().get(0).click();
		System.out.println("Select clicked");
		Actions actions = new Actions(driver);
		actions.moveToElement(f.getSelectFareButton().get(0));
		actions.click(f.getSelectFareButton().get(0));
		actions.perform();
		System.out.println("Flight Return Selected");			
	}
	
	@Test(description = "Step 6 and 7",priority = 5)
	public void verify_TripDetails() throws InterruptedException {
		TripDetailPage t= new TripDetailPage(driver);
		
		new WebDriverWait(driver, 15).until(ExpectedConditions.numberOfWindowsToBe(2));
		
//	    ArrayList<String> newTab  = new ArrayList<String> (driver.getWindowHandles());
//	    driver.switchTo().window(newTab.get(0));
//		System.out.println("Primer intento");
		
//		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "2");
//		System.out.println("Segundo intento");

		String currentWindow = driver.getWindowHandle();  //will keep current window to switch back
		for(String winHandle : driver.getWindowHandles()){
		   if (driver.switchTo().window(winHandle).getTitle().equals("Trip Detail | Travelocity")) {
			 driver.switchTo().window(winHandle).getTitle();
			 System.out.println("TAB SWITCHED");
		     break;
		   } 
		   else {
		      driver.switchTo().window(currentWindow);
		   } 
		}
		System.out.println("Checking Validations...");
		assertTrue(t.getTotalPrice().isDisplayed());
		System.out.println("Total Price OK");
		assertTrue(t.getSummary().isDisplayed());
		System.out.println("Summary OK");
		assertTrue(driver.getPageSource().contains("Price Guarantee"));
		System.out.println("Price Garantee text OK");
		t.getContinue().click();
	}
	
	@Test(description = "Step 8",priority = 6)
	public void verify_WhoIsTravelling() {
		PaymentPage p = new PaymentPage(driver);
		assertTrue(p.getFirstName().isDisplayed());
		p.getFirstName().sendKeys("Rodrigo");
		assertTrue(p.getLastName().isDisplayed());
		p.getLastName().sendKeys("Calabretta");
		assertTrue(p.getPhone().isDisplayed());
		p.getPhone().sendKeys("123456");;
	}

}
