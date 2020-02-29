package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

	protected WebDriver driver;
	private WebDriverWait wait;
	private FluentWait<WebDriver> fluentWait;


	public BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public WebDriverWait getWait(int timeOut) {
		wait = new WebDriverWait(getDriver(), timeOut);
		return wait;
	}

	public FluentWait<WebDriver> getFluentWait(long timeOut, long every) {
		fluentWait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(every)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return fluentWait;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public boolean isTextPresent(String TextExpected) {
		return getDriver().getPageSource().contains(TextExpected);
	}

	public void click(WebElement element) {
		waitElementClickable(element);
		element.click();
	}

	public boolean checkMessagePresent(String errorMessage) {
		return getDriver().getPageSource().contains(errorMessage);
	}

	protected void waitElementClickable(WebElement element) {
		getFluentWait(15, 2).until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void waitElementNotClickable(WebElement element) {
		getWait(7).until(ExpectedConditions.not((ExpectedConditions.elementToBeClickable(element))));
	}

	protected void waitAllElementVisible(List<WebElement> elements) {
		getWait(10).until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	protected void waitAllElementNotVisible(List<WebElement> elements) {
		getWait(7).until(ExpectedConditions.invisibilityOfAllElements(elements));
	}

	protected void waitElementVisible(WebElement element) {
		getWait(10).until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitElementNotVisible(WebElement element) {
		getWait(10).until(ExpectedConditions.invisibilityOf(element));
	}

	protected void switchToNextWindows(String titleNewPage) {
		// getWait().until(ExpectedConditions.numberOfWindowsToBe(2));
		String currentWindow = getDriver().getWindowHandle();
		for (String winHandle : getDriver().getWindowHandles()) {
			if (getDriver().switchTo().window(winHandle).getTitle().contains(titleNewPage)) {
				getDriver().switchTo().window(winHandle).getTitle();
				break;
			} else {
				driver.switchTo().window(currentWindow);
			}
		}
	}

	public void selectOptionFromDropdown(WebElement webElement, String value) {
		Select dropdown = new Select(webElement);
		dropdown.selectByValue(value);;
		boolean selectedOptionValue = dropdown.getFirstSelectedOption().getAttribute("value").equalsIgnoreCase(value);
		if (!selectedOptionValue) {
			throw new InvalidElementStateException(
					String.format("The option was not correctly selected in the dropdown", value));
		}
	}

	public boolean clickWithActionsBuilder(WebElement element) {
		waitElementClickable(element);
		try {
			Actions builder = new Actions(getDriver());
			builder.moveToElement(element, 5, 5).click(element);
			builder.perform();
			return true;
		} catch (TimeoutException toe) {
			return false;
		}
	}

	protected void mouseOver(WebElement element) {
		Actions builder = new Actions(getDriver());
		Action action = builder.moveToElement(element).build();
		action.perform();
	}

	protected void scrollDownToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	protected boolean existsElementById(String id) {
		try {
			driver.findElement(By.id(id));
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

}
