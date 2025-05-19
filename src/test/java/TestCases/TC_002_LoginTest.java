package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjescts.HomePage;
import PageObjescts.LoginPage;
import PageObjescts.MyAccountPage;
import TestBase.BaseClass;

public class TC_002_LoginTest extends BaseClass
{
	@Test(groups={"Sanity","Master"})
	public void Verify_Login_Test() throws InterruptedException
	{
		logger.info("**** Started execution of TC_002_LoginTest **** ");
		try {
		//  Home page
		HomePage hp= new HomePage(driver);
		
		hp.MyAccount();
		Thread.sleep(2000);
		hp.Login();
		
		LoginPage lp= new LoginPage(driver);
		
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.login();
		
		
		MyAccountPage mp= new MyAccountPage(driver);
		
		boolean targetpage=mp.msgdisplayed();
		
		Assert.assertEquals(targetpage, true);
		
		mp.Logout();
		}
		catch(Exception e)
		{
				Assert.fail();
		}
	}
}
