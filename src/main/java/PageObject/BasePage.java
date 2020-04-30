package PageObject;

import PageObject.HomePage.HomePage; // Не нужный импорт
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;
    @FindBy(xpath = "//span[@class='user-info']//a//span") // '//a//span' можно заменить на '//span', старайся делать локаторы максимально короткими
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

    //SOLID -> S -> Single Responsibility -> 1 метод - 1 действие. Этот метод и открывает страницу, и логинится. Зачем?) Просто в начале вызываешь метод открытия страницы, а потом логин
    // А вдруг ты будешь уже давно на странице, а потом понадобится залогинится (если нужна еще причина для разделения метода)
    public BasePage openPageAndlogIn() {
        driver.get("https://vse.ua");
        logInButton.click();
        loginField.sendKeys(emailName);
        passwordField.sendKeys(passwordData);
        clickLoginButton.click();
        try {
            Thread.sleep(1500); // ужс
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
        return new BasePage(driver);
    }

    // Не храни тестовые данные в классе страницы, вынеси их отдельно (тот же енам или класс статических констант)
    public String emailName = "syava1204@gmail.com";
    public String passwordData = "Svyatik12";
    public String searchShop = "Epicentr";
    public String searchProduct = "Samsung";
}
