package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	
	static public WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups= {"Sanity", "Regression","Master"})
	@Parameters({"os","browser"})
	
	
	public void setup(String os, String browser) throws IOException
	{
		logger=LogManager.getLogger(this.getClass());
		//loading properties file
		 FileReader file=new FileReader(".//src//test//resources//config.properties");
		 p=new Properties();
		 p.load(file);
		 
		 logger.info("***********Execution is started***************");
		 
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities= new DesiredCapabilities();
			
			// OS
			
			if(os.equals("Windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equals("Linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else if(os.equals("MAC"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("Os not matching");
				return;
			}
			
			// Browsers
			
			switch(browser.toLowerCase())
			{
			case "chrome" : capabilities.setBrowserName("chrome"); break;
			case "edge"   : capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : capabilities.setBrowserName("firefox"); break;
			default : System.out.println("Invalid Browser Name"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);

		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(browser.toLowerCase()) 
			{
				case "chrome" : driver = new ChromeDriver();break;
				case "edge" : driver = new EdgeDriver(); break;
				case "firefox" : driver = new FirefoxDriver();break;
				default : System.out.println("Invalid browser name"); 
				return;
			}
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups= {"Sanity", "Regression","Master"})
	public void tearDown()
	{
		driver.close();
	}
	
	public String randomString()
	{
		String geneatedRandomeString =RandomStringUtils.randomAlphabetic(5);
		return geneatedRandomeString;
	}
	
	public String randomNumber()
	{
		String geneatedRandomeString =RandomStringUtils.randomNumeric(10);
		return geneatedRandomeString;
	}
	
	public String randomAlphaNumeric()
	{
		String str= RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		return (str+"$"+num);
		
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\Screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}

}
