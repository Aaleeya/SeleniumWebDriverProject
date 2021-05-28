package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManagerHomePage {
	
	public WebDriver driver;
	
	public ManagerHomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By managerID = By.xpath("//td[contains(text(),'Manger Id : mngr328588')]");
	
	public WebElement getManagerIDText()
	{
		return driver.findElement(managerID);
	}

}
