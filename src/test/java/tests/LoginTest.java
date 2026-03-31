package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import utils.ConfigReader;
import utils.ExtentTestManager;
import utils.WaitUtils;

public class LoginTest extends BaseTest {

	@Test(groups= {"Sanity", "Master"})
	public void verifyLogin () {
		
		logger.info("******* Starting TC_02_LoginTest *********");
		
	
			
			//HomePage 
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			ExtentTestManager.getTest().info("Opening login Page");
			
			//Login Page 
			LoginPage login = new LoginPage(driver);
			
			String email = ConfigReader.getProperty("email");
			String password = ConfigReader.getProperty("password");

			ExtentTestManager.getTest().info("Entering credentials");
			login.login(email, password);
			
			//My Account Page
			MyAccountPage myAcc = new MyAccountPage(driver);
			boolean targetPage = myAcc.isMyAccountPageExist();
			

			Assert.assertTrue(targetPage, "Login failed - My Account page not displayed");

		
		logger.info("****** Finished TC_02_LoginTest ********");
	}
}
