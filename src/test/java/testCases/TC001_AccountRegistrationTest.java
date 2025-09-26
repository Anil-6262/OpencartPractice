package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{




	@Test(groups = {"Regression","Master"})
	public void verify_account_registration()
	{

		logger.info("***** Starting TC001_AccountRegistrationTest *****");

		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("***** Click on My Account info *****");
		hp.clickRegister();
		logger.info("***** Click on My Account info *****");

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

		logger.info("***** Provide customer details *****");

		regpage.setFirstName(randomString());
		regpage.setLastName(randomString());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());

		String randomPassword = randomAlphaNumeric();

		regpage.setPassword(randomPassword);
		regpage.setConfirmPassword(randomPassword);

		regpage.clickPrivacyPolicy();
		regpage.clickContinue();

		logger.info("***** Validating expected message... *****");
		String confirmMsg = regpage.getConfirmationMsg();

		if(confirmMsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}

		}catch (Exception e) {
			Assert.fail();
		}

		logger.info("***** Finished TC001_AccountRegistrationTest *****");

	}


	

}
