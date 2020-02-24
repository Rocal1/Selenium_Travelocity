package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 *
 */
public class Base {
	public WebDriver driver;
	public Properties prop;

	public WebDriver InitializerDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");

		prop.load(fis);

		if (prop.getProperty("browser").equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (prop.getProperty("browser").equalsIgnoreCase("explorer")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		
		return driver;
	}
	

	@BeforeTest
	public void SetUp() throws IOException {
		driver = InitializerDriver();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

	}

	@AfterTest
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
