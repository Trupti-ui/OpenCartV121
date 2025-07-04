package PageObjescts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage

{
	
	// Constructor
	
	public HomePage(WebDriver driver) 
	{
		super(driver);
		
	}

	// Locators
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkMyAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegister;
	@FindBy(xpath="(//a[normalize-space()='Login'])[1]") WebElement lnkLogin;
	
	
	// Action type
	
	// Actions
    public void clickMyAccount() {
        lnkMyAccount.click();
    }

    public void clickRegister() {
        lnkRegister.click();
    }

    public void clickLogin() {
        lnkLogin.click();
    }

}
