package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class BaseClass
{

	public static WebDriver driver;
	public Logger logger;
	public Properties prop;

	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os, String br) throws IOException
	{

		// loading properties file

		FileReader file = new FileReader("./src//test/resources//config.properties");
		prop=new Properties();
		prop.load(file);

		logger = LogManager.getLogger(this.getClass());

		if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
		{

			DesiredCapabilities cap = new DesiredCapabilities();

			// OS
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matchimg os");
				return;
			}

			// Browser
			switch(br.toLowerCase())
			{
			case "chrome": cap.setBrowserName("chrome"); break;
			case "firefox": cap.setBrowserName("firefox"); break;
			case "edge": cap.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching Browser"); return;
			}

			driver = new RemoteWebDriver(new URL("http://ANIL-PC:4444/wd/hub"), cap);
		}


		if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			default: System.out.println("Invalid browse name....."); return;
			}
		}




		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("appURL2"));
		driver.manage().window().maximize();
	}


	@AfterClass(groups = {"Sanity","Regression","Master"})
	public void tearDown()
	{
		if(driver!=null)
		{
			driver.quit();
		}
	}

	public String randomString()
	{
		String generateString =  RandomStringUtils.randomAlphabetic(5);
		return generateString;
	}

	public String randomNumber()
	{
		String generateNumber = RandomStringUtils.randomNumeric(10);
		return generateNumber;
	}

	public String randomAlphaNumeric()
	{
		String generateString =  RandomStringUtils.randomAlphabetic(3);
		String generateNumber = RandomStringUtils.randomNumeric(3);
		return (generateString+"@"+generateNumber);
	}

	public String captureScreenshot(String tname)
	{

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_"+timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;


	}
}
