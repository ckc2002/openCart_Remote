package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class AccountRegistrationPage {
	
	WebDriver driver;
	WaitUtils wait;

	public AccountRegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitUtils(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtphone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtconfirmPwd;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement btnAgree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void register(String fname, String lname, String email, String phone, String pwd, String conPwd) {
		txtFirstName.sendKeys(fname);
		txtLastname.sendKeys(lname);
		txtEmail.sendKeys(email);
		txtphone.sendKeys(phone);
		txtPassword.sendKeys(pwd);
		txtconfirmPwd.sendKeys(pwd);
		btnAgree.click();
		btnContinue.click();
	}
	
	public String getConfirmationMsg() {
		try {
			wait.waitForVisibility(msgConfirmation);
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}
}
