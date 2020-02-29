package com.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage{
	
	@FindBy(id = "firstname[0]")
	private WebElement firstName;
	
	@FindBy(id = "lastname[0]")
	private WebElement lastName;

	@FindBy(id = "phone-number[0]")
	private WebElement phoneNumber;

	
	public PaymentPage(WebDriver driver) {
		super(driver);
	}
		
	public boolean FirstNameIsDisplayed() {
		return (firstName.isDisplayed());
	}
	public boolean LastNameIsDisplayed() {
		return (lastName.isDisplayed());
	}
	public boolean PhoneNumberIsDisplayed() {
		return (phoneNumber.isDisplayed());
	}
	
	public void setNamesWhoIsTravelling(String first, String last) {
		firstName.sendKeys(first);
		lastName.sendKeys(last);
	}
	public void setPhoneWhoIsTravelling(String phone) {
		phoneNumber.sendKeys(phone);
	}

}
