package utils;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static void initDriver() {
		
		String browser = System.getProperty("browser", ConfigReader.getProperty("browser"));
		String env = System.getProperty("execution_env", ConfigReader.getProperty("execution_env"));
		String appUrl = System.getProperty("appUrl", ConfigReader.getProperty("appUrl"));
			
		try {
			if(env.equalsIgnoreCase("remote")) {
				initRemoteDriver(browser);
			} else {
				initLocalDriver(browser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().get(appUrl);		
	}
	
	private static void initLocalDriver(String browser) {
		
		switch(browser.toLowerCase()) {
			case "chrome": 
				driver.set(new ChromeDriver());
				break;
			case "firefox": 
				driver.set(new FirefoxDriver());
				break;
			case "edge":
				driver.set(new EdgeDriver());
				break;
			default: 
				throw new RuntimeException("Browser not supported: "+browser);
 		}
	}
	
	private static void initRemoteDriver(String browser) throws Exception {

		String gridUrl = "http://localhost:4444"; //Running Selenium Grid inside the docker container/server
		
	  //  String gridUrl = "http://localhost:4444/wd/hub"; //Running Selenium Grid in Local Machine use this URL
	    
	    System.out.println("Running Tests on Selenium Grid");

	    switch (browser.toLowerCase()) {

	        case "chrome":
	            ChromeOptions chromeOptions = new ChromeOptions();
	            driver.set(new RemoteWebDriver(new URL(gridUrl), chromeOptions));
	            break;

	        case "firefox":
	            FirefoxOptions firefoxOptions = new FirefoxOptions();
	            driver.set(new RemoteWebDriver(new URL(gridUrl), firefoxOptions));
	            break;

	        case "edge":
	            EdgeOptions edgeOptions = new EdgeOptions();
	            driver.set(new RemoteWebDriver(new URL(gridUrl), edgeOptions));
	            break;

	        default:
	            throw new RuntimeException("Browser not supported: " + browser);
	    }
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void quitDriver() {
		getDriver().quit();
		driver.remove();
	}
}
