package PageObject;

import PageObject.HomePage.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;
    @FindBy(xpath = "//span[@class='user-info']//a//span")
    private WebElement logInButton;
    @FindBy(xpath = "//input[@id='login-form-login']")
    private WebElement loginField;
    @FindBy(xpath = "//input[@id='login-form-password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@id='loginButton']")
    private WebElement clickLoginButton;
    @FindBy (xpath = "//a[contains(text(),'RU')]")
    private WebElement languageRU;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void openPage() {
        driver.get("https://vse.ua");
    }

    public HomePage openPageAndlogIn() {
        driver.get("https://vse.ua");
        languageRU.click();
        logInButton.click();
        loginField.sendKeys(emailName);
        passwordField.sendKeys(passwordData);
        clickLoginButton.click();
        return new HomePage(driver);
    }

    public String emailName = "syava1204@gmail.com";
    public String passwordData = "Svyatik12";
    public String searchShop = "Epicentr";
}
