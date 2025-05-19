package PageObjescts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrations extends BasePage

{
	// Constructor
	
	public AccountRegistrations(WebDriver driver)
	{
		super(driver);
	}
	
	// Locators of elements
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtfirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtlastName;
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txtemail;
	
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txttelephone;
	
	@FindBy(xpath="//input[@id='input-password']") WebElement txtpassword;
	
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtconfirmpassword;
	
	@FindBy(xpath="//input[@name='agree']") WebElement chkAgree;
	
	@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	
	// Action type
	
	public void setFirstName(String fname)
	{
		txtfirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtlastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtemail.sendKeys(email);
	}
	
	public void setTelephone(String telphone)
	{
		txttelephone.sendKeys(telphone);
	}
	
	public void setPassword(String pwd)
	{
		txtpassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)
	{
		txtconfirmpassword.sendKeys(pwd);
	}
	
	public void checkAgree()
	{
		chkAgree.click();
	}
	
	public void btnContinue()
	{
		//sol1 
		btnContinue.click();
		
		//sol2 
		//btnContinue.submit();
		
		//sol3
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
					
		//sol4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", btnContinue);
		
		//Sol 5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//Sol6  
		//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

	}
	
	public String getconfirmationmsg()
	{
		try {
			return (msgConfirmation.getText());
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	
	}
	
}
