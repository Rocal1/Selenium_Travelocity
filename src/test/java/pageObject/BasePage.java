package pageObject;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



public class BasePage {

	protected WebDriver driver;
	private WebDriverWait wait;
	
	public BasePage(WebDriver Pdriver) {
		PageFactory.initElements(Pdriver, this);
		wait = new WebDriverWait(Pdriver, 20);
		driver = Pdriver;	}
	
	public WebDriverWait getWait() {
		return wait;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	protected void waitClickable(WebElement element) {
		getWait().until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void WaitElementVisibleByID(String id) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				//.withTimeout(30)
				//.pollingEvery(5)
				.ignoring(NoSuchElementException.class);
		
		wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver ) {
				return driver.findElement(By.id(id));
			}
		});
	}
	
	public void selectOptionFromDropdown(WebElement webElement, String text) {
		Select dropdown = new Select(webElement);
		dropdown.selectByVisibleText(text);
		boolean selectedOptionText = dropdown.getFirstSelectedOption().getAttribute("label").equalsIgnoreCase(text);
		if (!selectedOptionText) {
			throw new InvalidElementStateException(
					String.format("The option with text <{0}> was not correctly selected in the dropdown", text));
		}
	}
	
	protected boolean existsElementById(String id) {
	    try {
	        driver.findElement(By.id(id));
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}
	
	public boolean clickWithActionsBuilder(WebDriver webDriver, WebElement element) {
		try {
			Actions builder = new Actions(webDriver);
			builder.moveToElement(element, 5, 5).click(element);
			builder.perform();
			return true;
		} catch (TimeoutException toe) {
			return false;
		}
	}

	public void mouseOver(WebDriver driver, WebElement element) {
		Actions builder = new Actions(driver);
		Action action = builder.moveToElement(element).build();
		action.perform();
	}
	
	protected void scrollDownToElement(WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", webElement);
	}

	public void scrollIntoView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

}

