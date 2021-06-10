package resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	private WebDriver driver;
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException
	{
		prop= new Properties();
		String path=System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(path+"\\src\\main\\java\\resource\\data.properties");
		prop.load(fis);
		String selectedBrowser=prop.getProperty("browser");
		if(selectedBrowser.equals("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver", path+"\\src\\main\\java\\resource\\drivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(selectedBrowser.equals("firefox"))
		{
			//System.setProperty("webdriver.gecko.driver", path+"\\src\\main\\java\\resource\\drivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			//DesiredCapabilities dc = new DesiredCapabilities();
			//dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			driver= new FirefoxDriver();
		}
		else if(selectedBrowser.equals("IE"))
		{
			//System.setProperty("webdriver.chrome.driver", path+"\\src\\main\\java\\resource\\drivers\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

}
