package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
	public void verify_LoginDDT(String email, String pwd, String expRes) {
		logger.info("***** Starting TC003_LoginDDT *****");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLoginBtn();

			MyAccountPage mcp = new MyAccountPage(driver);
			boolean targerpage = mcp.isMyAccountPageExists();

			if (expRes.equalsIgnoreCase("Valid")) {
				if (targerpage == true) {
					mcp.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

			if (expRes.equalsIgnoreCase("Invalid")) {
				if (targerpage == true) {
					mcp.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("***** Finished TC003_LoginDDT *****");

	}

}
