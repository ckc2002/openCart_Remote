package base;

import org.apache.logging.log4j.LogManager;//log4j2
import org.apache.logging.log4j.Logger;//log4j2
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import utils.DriverFactory;
import utils.RunTimeConfig;

public class BaseTest {
	
	protected WebDriver driver;
	public Logger logger=LogManager.getLogger(this.getClass()); //log4j2
	
	@Parameters("browser")
	@BeforeMethod(alwaysRun=true)
	public void setup(String browser) {
		
		System.setProperty("browser", browser);
		System.setProperty("execution_env", "remote");
		
		logger.info("Starting browser... " + browser);
		DriverFactory.initDriver();
		driver = DriverFactory.getDriver();
		logger.info("Browser Started Successfully - "+ browser);
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void teardown() {
		DriverFactory.quitDriver();
		logger.info("Browser Closed and Clean Successfully");
	}
}
