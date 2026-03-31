package utils;

import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	
	WebDriver driver;
	WebDriverWait wait;
	//FluentWait<WebDriver> wait;

	public WaitUtils(WebDriver driver) {
		this.driver = driver;
		
		int timeout = Integer.parseInt(ConfigReader.getProperty("timeout"));
		int polling = Integer.parseInt(ConfigReader.getProperty("polling"));
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
//        wait = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(timeout))
//                .pollingEvery(Duration.ofSeconds(polling))
//                .ignoring(NoSuchElementException.class);

	}
	
	
	// ---------- FOR By Locators ----------------
	public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
	
	 public WebElement waitForClick(By locator) {
	        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
	 
	 //------------- For By WebElements -----------------
	 public WebElement waitForVisibility(WebElement element) {
		 return wait.until(ExpectedConditions.visibilityOf(element));
	 }
	 
	 public WebElement waitForClick(WebElement element) {
		 return wait.until(ExpectedConditions.elementToBeClickable(element));
	 }
	
}
