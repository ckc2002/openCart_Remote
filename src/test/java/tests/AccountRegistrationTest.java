package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AccountRegistrationPage;
import pages.HomePage;
import utils.RandomUtils;

public class AccountRegistrationTest extends BaseTest {

	RandomUtils rn = new RandomUtils();
	
	@Test(groups= {"Master"})
	public void verifyAccount_Registration() {
		
		logger.info("*********** Starting TC01 Account Registration Test *************");
		
		
			//Home Page to navigate register page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickRegister();
			
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			
			logger.info("Providing Customer Details...");
			String password = rn.randomAlphaNumeric();
			
			regpage.register(
					rn.randomString().toUpperCase(),
					rn.randomString().toUpperCase(),
					rn.randomString()+"@gmail.com",
					rn.randomnumeric(),
					password,
					password);
			
			logger.info("Validate expected message");
			String confmsg = regpage.getConfirmationMsg();
			
//			New and Different way
			Assert.assertEquals(
					confmsg, 
					"Your Account Has Been Created!",
					"Registration failed - Confirmation message mismatch"
					);
			
			//Old way
//			if(confmsg.equals("Your Account Has Been Created!")) {
//				Assert.assertTrue(true);
//			} else {
//				logger.info("Test Failed");
//				logger.info("Debug logs");
//				Assert.assertFalse(false);
//			}
		
		logger.info("**** Finished TC001_AccountRegisterationTest !****");
	}

}
