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
	
	
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Logger logger;
    public Properties p;

    public static WebDriver getDriver() {
        return driver.get();
    }
	
	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
	public void setup(String os, String browser) throws IOException
	{
		logger=LogManager.getLogger(this.getClass());
		//loading properties file
		 FileReader file=new FileReader(".//src//test//resources//config.properties");
		 p=new Properties();
		 p.load(file);
		 
		 logger.info("***********Execution is started***************");
		 
        WebDriver localDriver = null;
		
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            switch (os.toLowerCase()) {
                case "windows":
                    capabilities.setPlatform(Platform.WIN11);
                    break;
                case "linux":
                    capabilities.setPlatform(Platform.LINUX);
                    break;
                case "mac":
                    capabilities.setPlatform(Platform.MAC);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid OS: " + os);
            }

            switch (browser.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Browser: " + browser);
            }

            localDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        } else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    localDriver = new ChromeDriver();
                    break;
                case "edge":
                    localDriver = new EdgeDriver();
                    break;
                case "firefox":
                    localDriver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Browser: " + browser);
            }
        }
		
		driver.set(localDriver);
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get(p.getProperty("appURL"));
        getDriver().manage().window().maximize();
	}

	@AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();  // Clean up ThreadLocal
        }
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
