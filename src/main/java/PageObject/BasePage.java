package PageObject;
import PageObject.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    private Constants constant;
    protected WebDriver driver;
    @FindBy(xpath = "//span[@class='user-info']//a//span") // '//a//span' можно заменить на '//span', старайся делать локаторы максимально короткими
    private WebElement logInButton;
    @FindBy(xpath = "//input[@id='login-form-login']")
    private WebElement loginField;
    @FindBy(xpath = "//input[@id='login-form-password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@id='loginButton']")
    private WebElement clickLoginButton;


    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void openPage() {
        driver.get("https://vse.ua");
    }

    //SOLID -> S -> Single Responsibility -> 1 метод - 1 действие. Этот метод и открывает страницу, и логинится. Зачем?) Просто в начале вызываешь метод открытия страницы, а потом логин
    // А вдруг ты будешь уже давно на странице, а потом понадобится залогинится (если нужна еще причина для разделения метода)
    public BasePage openPageAndlogIn() {
        driver.get("https://vse.ua");
        logInButton.click();
        loginField.sendKeys(constant.emailName);
        passwordField.sendKeys(constant.passwordData);
        clickLoginButton.click();
        try {
            Thread.sleep(1500); // ужс
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
        return new BasePage(driver);
    }

    // Не храни тестовые данные в классе страницы, вынеси их отдельно (тот же енам или класс статических констант) - Done


}
