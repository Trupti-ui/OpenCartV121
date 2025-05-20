package PageObjescts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{

	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	// Locatore
	
	@FindBy (xpath="//input[@id='input-email']") WebElement txtuserName;
	
	@FindBy (xpath="//input[@id='input-password']") WebElement txtpassword;
	
	@FindBy (xpath="//input[@value='Login']") WebElement btnLogin;
	
	
	// Actions
    public void setEmail(String email) {
    	txtuserName.clear();
    	txtuserName.sendKeys(email);
    }

    public void setPassword(String pwd) {
    	txtpassword.clear();
    	txtpassword.sendKeys(pwd);
    }

    public void clickLogin() {
        btnLogin.click();
    }


	
}
