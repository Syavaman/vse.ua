package PageObject.HomePage;

import PageObject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageErrors extends BasePage {
    public LoginPageErrors(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='form-group field-login-form-password required has-error']//div[@class='dropdown-hint afterLeft']")
    private WebElement errorLoginOrPassword;
    @FindBy(xpath = "//div[@class='form-group field-login-form-login required has-error']//div[@class='dropdown-hint afterLeft']")
    private WebElement emptyEmail;
    @FindBy(xpath = "//div[@class='form-group field-login-form-password required has-error']//div[@class='dropdown-hint afterLeft']")
    private WebElement emptyPassword;

    public boolean isErrorEmptyEmailDisplayed() {
        return emptyEmail.isDisplayed();
    }

    public boolean isErrorEmptyPasswordDisplayed() {
        return emptyPassword.isDisplayed();
    }

    public boolean isErrorLoginOrPasswordDisplayed() {
        return errorLoginOrPassword.isDisplayed();
    }


}
