package test;

import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvException;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

//import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;

public class AddNewEntry{
	WebDriver driver;

	@BeforeTest
	public void initializePage() throws IOException {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test(enabled = false,dataProvider = "getTestData", priority=1 )
	public void addNewEntry(String entryType, String fname, String lname, String addline1, String city, String province,
			String country, String postCode, String pType, String phone) throws IOException, InterruptedException {
		//driver.get(prop.getProperty("baseUrl"));
		driver.get("http://localhost/index.php");
		driver.findElement(By.linkText("Add New Entry")).click();
		Select addressType = new Select(driver.findElement(By.id("addr_type")));
		addressType.selectByValue(entryType);
		driver.findElement(By.id("addr_first_name")).sendKeys(fname);
		driver.findElement(By.id("addr_last_name")).sendKeys(lname);
		driver.findElement(By.id("addr_addr_line_1")).sendKeys(addline1);
		driver.findElement(By.id("addr_city")).sendKeys(city);
		driver.findElement(By.id("addr_region")).sendKeys(province);
		driver.findElement(By.id("addr_country")).sendKeys(country);
		driver.findElement(By.id("addr_post_code")).sendKeys(postCode);
		Select phoneType = new Select(driver.findElement(By.id("addr_phone_1_type")));
		phoneType.selectByValue(pType);
		driver.findElement(By.id("addr_phone_1")).sendKeys(phone);
		driver.findElement(By.id("submit_button")).click();
		String expectedText = "The new address book entry was added successfully";
		String actualText = driver
				.findElement(By.xpath("//h2[contains(text(),'The new address book entry was added successfully')]"))
				.getText();
		assertEquals(expectedText, actualText);
		Reporter.log("Executed Add New Entry method");
	}

	@DataProvider
	public Iterator<Object[]> getTestData() throws IOException, CsvException {
		ArrayList<Object[]> testData = TestUtils.getDataReadCSV();
		return testData.iterator();
	}

	//Verify submitting add new entry form with no data
	@Test(enabled=true)
	void testAddNoData() {
		driver.findElement(By.linkText("Add New Entry")).click();
		driver.findElement(By.id("submit_button")).click();
		String expectedText = "An person's name or business name must be specified." + "\n"
				+ "At least one of the following must be entered: street/mailing address, email address, phone number or web site url.";
		String actualText = driver.findElement(By.tagName("p")).getText();
		assertEquals(expectedText, actualText);
		Reporter.log("Executed No data on Add new Entry form method");
	}

	// Verify clear form on add new entry
	@Test(enabled=true)
	void testClearFormAddNewEntry() {
		driver.findElement(By.linkText("Add New Entry")).click();
		Select entryType = new Select(driver.findElement(By.id("addr_type")));
		entryType.selectByValue("Other");
		driver.findElement(By.id("addr_first_name")).sendKeys("Enem");
		driver.findElement(By.id("addr_last_name")).sendKeys("Test");
		Select phoneType = new Select(driver.findElement(By.id("addr_phone_1_type")));
		phoneType.selectByValue("Work");
		driver.findElement(By.id("addr_phone_1")).sendKeys("5196295161");
		driver.findElement(By.id("reset_button")).click();

		// test
		String firstName = driver.findElement(By.id("addr_first_name")).getText();
		String lastName = driver.findElement(By.id("addr_last_name")).getText();
		String phone = driver.findElement(By.id("addr_phone_1")).getText();
		assertTrue(firstName.isEmpty());
		assertTrue(lastName.isEmpty());
		assertTrue(phone.isEmpty());
		Reporter.log("Executed Clear Form on Add New Entry method");
	}

	//Verify return to landing page from add new entry page
	@Test (enabled=true)
	void testReturnOnAddNewEntry() {
		driver.findElement(By.linkText("Add New Entry")).click();
		driver.findElement(By.partialLinkText("Return to Menu")).click();

		// test
		String expectedTitle = "Address Book";
		String actualTitle = driver.getTitle();
		assertEquals(expectedTitle, actualTitle);
		Reporter.log("Executed Return to main menu on Add New Entry method");
	}

	/*public void takescreenshots(String name) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(".//Screenshots//" + name + ".png"));
	}*/
	
	@AfterTest
	public void tearDown() throws IOException {
		driver.quit();
	}
}
