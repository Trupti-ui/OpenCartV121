package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjescts.HomePage;
import PageObjescts.LoginPage;
import PageObjescts.MyAccountPage;
import TestBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups = {"Sanity", "Master"})
	public void verifyLoginTest() {
	    logger.info("**** Started execution of TC_002_LoginTest ****");

	    try {
	        HomePage hp = new HomePage(getDriver());
	        hp.clickMyAccount();
	        logger.info("Clicked on My Account");

	        hp.clickLogin();
	        logger.info("Clicked on Login link");

	        LoginPage lp = new LoginPage(getDriver());
	        String email = p.getProperty("email");
	        String password = p.getProperty("password");
	        lp.setEmail(email);
	        lp.setPassword(password);
	        lp.clickLogin();
	        logger.info("Submitted login form");

	        MyAccountPage mp = new MyAccountPage(getDriver());
	        Assert.assertTrue(mp.isMyAccountHeadingDisplayed(), "Login failed: My Account heading not displayed");
	        logger.info("Login successful, My Account heading is visible");

	        mp.clickLogout();
	        logger.info("Clicked Logout");

	    } catch(Exception e)
		{
	    	 Assert.fail("Test failed due to an unexpected exception.");
		}

	       
	    
	}

    
}
