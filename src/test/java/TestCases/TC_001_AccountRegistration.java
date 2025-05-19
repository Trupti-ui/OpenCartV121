package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjescts.AccountRegistrations;
import PageObjescts.HomePage;
import TestBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass{
	
	@Test(groups= {"Regression","Master"})
	public void Verify_Account_Registration()
	{
		try 
		{
			HomePage hp = new HomePage(driver);
			logger.info("**** Started Executions of TC_001_AccountRegistration ...! ****");
			hp.MyAccount();
			logger.info("**** Clicked on My Account ..! ***");
			hp.Register();
			logger.info("**** Clicked on My Register link ..1 ***");
			
			AccountRegistrations regpage= new AccountRegistrations(driver);
			logger.info("**** Providing account creation details...!***");
			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomString()+"@gmail.com");
			regpage.setTelephone(randomNumber());
			
			String password= randomAlphaNumeric();
			
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			regpage.checkAgree();
			regpage.btnContinue();
			logger.info("**** Validating the Account registrations ...!***");
			String cnfmsg=regpage.getconfirmationmsg();
//			Assert.assertEquals(cnfmsg, "Your Account Has Been Created!!!", "Confirmation message mismatch");
//			logger.info("Test Passed");
			
			if (cnfmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
				
				logger.info("Test Passed");
			}
			else
			{
				logger.error("Test Failed");
				logger.error("Debug logs");
				Assert.assertTrue(false);
			}
			
		
		}
		
		catch(Exception e)
		{
				Assert.fail();
		}
		
	/*	catch(Exception e) {
			logger.error("Test Failed" +e.getMessage());
			logger.error("Debug logs" +e.getMessage());
			logger.info("Test Failed");
			Assert.fail();
		}
		*/
		finally {
			logger.info("*** Finished of TC_001_AccountRegistration ");
		}
			
	}
}
