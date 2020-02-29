package com.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;


public class HotelResultsPage extends BasePage {

	@FindBy(xpath = "//span[text()='Price']/../..")
	private WebElement sortByPrice;

	@FindAll(@FindBy(css = "[class=\"actualPrice price fakeLink \"]"))
	private List<WebElement> hotelPrice;

	@FindAll(@FindBy(css = "[title=\"3.0\"]"))
	private List<WebElement> treeStars;

	@FindAll(@FindBy(css = "[class=\"starRating secondary\"] [class*=\"icon-stars\"]"))
	private List<WebElement> starsList;

	@FindAll(@FindBy(css = "#resultsContainer [data-hotelid]"))
	private List<WebElement> hotelName;

	@FindBy(css = "[class=\"playback-pill-grid-item-label\"]")
	private WebElement pillButton;

	@FindAll(@FindBy(css = "[class=\"listing-photo-gallery title__media-container\"]"))
	private List<WebElement> hotelResult;

	public HotelResultsPage(WebDriver driver) {
		super(driver);
	}

	public boolean sortByPriceIsDisplayed() {
		waitElementClickable(sortByPrice);
		return (sortByPrice.isDisplayed());
	}

	public boolean hotelHasEmptyResults() {
		return hotelName.isEmpty();
	}

	public void clickOnSortByPrice() {
		click(sortByPrice);
	}

	public boolean verifySponsorOnFirstResult() {
		boolean sponsor = false;
		if (hotelResult.stream().findFirst().get().getText().contains("Sponsored")) {
			sponsor = true;
		}
		return sponsor;
	}

	public boolean verifyHotelsResultsSorted() {
		waitElementNotVisible(hotelName.get(1));
		waitElementClickable(hotelName.get(1));
		double PreviousPriceNumber = 0;
		boolean sorted = true;
		for (int i = 0; i < hotelPrice.size(); i++) {
			String Price = hotelPrice.get(i).getText().trim().replaceAll("[^0-9]", "");
			double PriceNumber = Double.parseDouble(Price);
			if (PriceNumber >= PreviousPriceNumber) {
				PreviousPriceNumber = PriceNumber;
			} else
				sorted = false;
		}
		return sorted;
	}

	public HotelPage clickOnTheFirst3StarsHotel() {
		for (int i = 0; i < starsList.size(); i++) {
			String Star = starsList.get(i).getAttribute("title");
			if (Star.contentEquals("3.0")) {
				click(hotelName.get(i));
				break;
			}
		}
		return new HotelPage(getDriver());
	}

}
