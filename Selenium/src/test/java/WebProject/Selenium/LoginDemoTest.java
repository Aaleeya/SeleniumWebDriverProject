package WebProject.Selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.LoginDemo;
import pageObjects.ManagerHomePage;
import resource.Base;

public class LoginDemoTest extends Base{
	
public WebDriver driver;
	
	@BeforeTest
	public void initializePage() throws IOException
	{
		driver = initializeDriver();
	}
	
	@Test(dataProvider="getData")
	public void login(String username, String password) throws Exception
	{
		driver.get(prop.getProperty("newToursUrl"));
		SoftAssert sa = new SoftAssert();
		
		LoginDemo login = new LoginDemo(driver);
		login.getUsername().sendKeys(username);
		login.getPassword().sendKeys(password);
		login.getLoginBtn().click();
		//Thread.sleep(2000);
		try {
			WebDriverWait wait = new WebDriverWait(driver,15);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert simplePrompt = driver.switchTo().alert();
			if(simplePrompt != null) {
			System.out.println(simplePrompt.getText());
			simplePrompt.accept();
			}
			else {
				System.out.println("No alert present");
			}
			System.out.println("Login Failed");
		}
		catch(Exception alertException)
		{
		String title = driver.getTitle();
		String homePageTitle = title.trim();
		ManagerHomePage hp = new ManagerHomePage(driver);
		if(homePageTitle.equals("Guru99 Bank Manager HomePage"))
		{
			System.out.println("Login Successful");
			
			//2.	Post login , a message "Welcome <manager id> " is shown. The client wants you to validate this
			sa.assertTrue(hp.getManagerIDText().isDisplayed());
			System.out.println(hp.getManagerIDText().getText());
			//Code to take screenshot
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//Code to save screenshot at desired location
			FileUtils.copyFile(srcFile, new File(""));
			
		}
		else
		{
			System.out.println("Login Failed");
		}
		}
		sa.assertAll();
	}
		
	/*The client now wants you to make 2 changes
	1.	 Instead of Excel he wants you to use Data Provider annotation of Testing
	2.	Post login , a message "Welcome <manager id> " is shown. The client wants you to validate this
	*/
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = new Object[4][2];
		data[0][0] = "mngr328588";
		data[0][1] = "rEvUmyz";
		data[1][0] = "dr.tim56";
		data[1][1] = "rEvUmyz";
		data[2][0] = "mngr328588";
		data[2][1] = "dr tim";
		data[3][0] = "12345";
		data[3][1] = "12345";
		return data;
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
