package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginDemo{
	public WebDriver driver;
	
	public LoginDemo(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By username = By.name("uid");
	By password = By.name("password");
	By loginbtn = By.name("btnLogin");
	
	public WebElement getUsername()
	{
		return driver.findElement(username);
	}
	
	public WebElement getPassword()
	{
		return driver.findElement(password);
	}
	
	public WebElement getLoginBtn() 
	{
		return driver.findElement(loginbtn);
	}
	
}
