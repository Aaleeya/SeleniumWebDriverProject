package test;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

public class LandingPage {
	WebDriver driver;
	
	// Verify the title of the application
	@Test
	void testTitle() {
	String expectedTitle = "Address Book";
	String actualTitle = driver.getTitle();
	assertEquals(expectedTitle, actualTitle);
	}

	@BeforeTest
	public void beforeTest() throws IOException {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
