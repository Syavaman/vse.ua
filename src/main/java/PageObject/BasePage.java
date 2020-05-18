package PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private Constants constant;
    protected WebDriver driver;
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


    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void openPage() {
        driver.get("https://vse.ua");
    }

    public BasePage logIn() {
        openPage();
        logInButton.click();
        loginField.sendKeys(constant.emailName);
        passwordField.sendKeys(constant.passwordData);
        clickLoginButton.click();
        String NickName = constant.emailName.substring(0,constant.emailName.indexOf("@"));
        Wait<WebDriver> wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.textToBePresentInElement(nameOfLoggedInAccount, NickName));
        return this;
    }

    public String getTitleName() {
        return driver.getTitle();
    }


}
