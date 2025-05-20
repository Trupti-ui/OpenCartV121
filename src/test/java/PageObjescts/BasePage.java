package PageObjescts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import TestBase.BaseClass;

public class BasePage {

    protected WebDriver driver;

    public BasePage() {
        // Use thread-safe WebDriver instance from BaseClass
        this.driver = BaseClass.getDriver();
        PageFactory.initElements(driver, this);
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
