package PageObjescts;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrations extends BasePage {

    public AccountRegistrations(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//input[@id='input-firstname']") WebElement txtfirstName;
    @FindBy(xpath="//input[@id='input-lastname']") WebElement txtlastName;
    @FindBy(xpath="//input[@id='input-email']") WebElement txtemail;
    @FindBy(xpath="//input[@id='input-telephone']") WebElement txttelephone;
    @FindBy(xpath="//input[@id='input-password']") WebElement txtpassword;
    @FindBy(xpath="//input[@id='input-confirm']") WebElement txtconfirmpassword;
    @FindBy(xpath="//input[@name='agree']") WebElement chkAgree;
    @FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;

    public void setFirstName(String fname) {
        txtfirstName.clear();
        txtfirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        txtlastName.clear();
        txtlastName.sendKeys(lname);
    }

    public void setEmail(String email) {
        txtemail.clear();
        txtemail.sendKeys(email);
    }

    public void setTelephone(String telephone) {
        txttelephone.clear();
        txttelephone.sendKeys(telephone);
    }

    public void setPassword(String pwd) {
        txtpassword.clear();
        txtpassword.sendKeys(pwd);
    }

    public void setConfirmPassword(String pwd) {
        txtconfirmpassword.clear();
        txtconfirmpassword.sendKeys(pwd);
    }

    public void checkAgree() {
        if (!chkAgree.isSelected()) {
            chkAgree.click();
        }
    }

    public void clickContinue() {
        btnContinue.click();
    }

    public void fillRegistrationForm(String fname, String lname, String email, String telephone, String pwd) {
        setFirstName(fname);
        setLastName(lname);
        setEmail(email);
        setTelephone(telephone);
        setPassword(pwd);
        setConfirmPassword(pwd);
        checkAgree();
    }

    public String getConfirmationMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(msgConfirmation));
            return msgConfirmation.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
