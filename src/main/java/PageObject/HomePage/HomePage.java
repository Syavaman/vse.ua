package PageObject.HomePage;

import PageObject.BasePage;
import PageObject.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput; // не нужный импорт

import static com.sun.org.apache.xml.internal.serialize.LineSeparator.Web;

public class HomePage extends BasePage {
    private Constants constant;

    public HomePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//span[@class='user-info']")
    private WebElement logInButton;
    @FindBy(xpath = "//input[@id='login-form-login']")
    private WebElement loginField;
    @FindBy(xpath = "//input[@id='login-form-password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@id='loginButton']")
    private WebElement clickLoginButton;
    @FindBy(className = "user-info")
    private WebElement nameOfLoggedInAccount;
    @FindBy(xpath = "//li[7]/a")
    private WebElement clickLogOutButton;
    @FindBy(xpath = "//a[contains(text(),'RU')]")
    private WebElement languageRU;
    @FindBy(xpath = "//a[contains(text(),'UA')]")
    private WebElement languageUA;
    @FindBy(xpath = "//div[@class='logo']//span[1]")
    private WebElement languageHeading;

    public String getAccountName() {
        return constant.emailName.substring(0, constant.emailName.indexOf("@"));
    }


    public HomePage logInIntoSite(String email, String password) {
        logInButton.click();
        loginField.sendKeys(email);
        passwordField.sendKeys(password);
        clickLoginButton.click();
        String NickName = email.substring(0, email.indexOf("@"));
        Wait<WebDriver> wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.textToBePresentInElement(nameOfLoggedInAccount, NickName));
        return this;
    }

    public HomePage logInIntoSiteWithInvalidData(String email, String password) {
        logInButton.click();
        loginField.sendKeys(email);
        passwordField.sendKeys(password);
        clickLoginButton.click();
        return this;
    }

    public HomePage logOut() {
        logInButton.click();
        clickLogOutButton.click();
        return this;
    }

    public String getNameOfLoggedInAccount() {
        return nameOfLoggedInAccount.getText();
    }

    public HomePage checkRussianLanguage() {
        languageRU.click();
        System.out.println("Language is changed to Russian and title is : " + driver.getTitle());
        return this;
    }

    public HomePage checkUkrainianLanguage() {
        languageUA.click();
        System.out.println("Language is changed to Ukrainian and title is : " + driver.getTitle());
        return this;
    }

    public String getLanguage() {
        return languageHeading.getText();
    }

}