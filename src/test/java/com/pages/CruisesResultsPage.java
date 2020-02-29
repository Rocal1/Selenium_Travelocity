package com.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;


public class CruisesResultsPage extends BasePage {

	@FindBy(id = "destination-select")
	private WebElement destination;

	@FindBy(css = "[id=\"cruise-search-display\"] [name=\"length-10-14\"]")
	private WebElement filterNight10To14;

	@FindAll(@FindBy(className = "card-price"))
	private List<WebElement> priceWithDiscount;

	@FindAll(@FindBy(className = "strikeout-price-card"))
	private List<WebElement> priceWithOutDiscount;

	@FindAll(@FindBy(css = "[class=\"message-flag flex-flag\"]"))
	private List<WebElement> discountAmount;

	@FindAll(@FindBy(css = "[id*=\"selectSailingButton\"]" ))
	private List<WebElement> select;

	@FindAll(@FindBy(className = "title-on-ship-image"))
	private List<WebElement> title;

	public CruisesResultsPage(WebDriver driver) {
		super(driver);
	}

	public String getDestination() {
		return destination.getText();
	}

	public CruisesResultsPage selectFilterNight10To14() {
		clickWithActionsBuilder(filterNight10To14);
		return this;
	}
	
	public boolean verifyEveryCruiseWithAndWithoutDiscount() throws InterruptedException {
		waitElementClickable(filterNight10To14);
		boolean HasDiscounts = false;
		if (priceWithOutDiscount.size() > 0 && priceWithDiscount.size() > 0) {
			HasDiscounts=true;
		}
		return HasDiscounts;
	}
	
	public Integer getCuiseIndexWithMoreDiscount() {
		int BestCruise = 0;
		int BestCruiseIndex = 0;
		for (int i = 0; i < discountAmount.size(); i++) {
			String Discount = discountAmount.get(i).getText().trim().replaceAll("[^0-9]", "");
			Integer ActualDiscount = Integer.parseInt(Discount);		
			if (ActualDiscount > BestCruise) {
					BestCruise=ActualDiscount;
					BestCruiseIndex = i;
				}
			}
		return BestCruiseIndex;
	}
	
	public String getCruiseTitle(int BestCruiseIndex){
		String titulo = title.get(BestCruiseIndex).getText();
		return titulo;
	}
	
	public CruisePage selectCruise(int BestCruiseIndex) {
		click(select.get(BestCruiseIndex));
		return new CruisePage(getDriver());
	}

}
