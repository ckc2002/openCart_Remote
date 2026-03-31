package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class MyAccountPage {

	WebDriver driver;
	WaitUtils wait;
	
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitUtils(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement linkLogout;
	
	public boolean isMyAccountPageExist() {
		try {
			wait.waitForVisibility(msgHeading);
			return msgHeading.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clicklogout() {
		linkLogout.click();
	}
}
