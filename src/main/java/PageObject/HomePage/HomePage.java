package PageObject.HomePage;

import PageObject.BasePage;
import PageObject.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.SQLOutput; // не нужный импорт

public class HomePage extends BasePage {
    private Constants constant;
    public HomePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//span[@class='user-info']//a//span")
    private WebElement logInButton;
    @FindBy(xpath = "//input[@id='login-form-login']")
    private WebElement loginField;
    @FindBy(xpath = "//input[@id='login-form-password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@id='loginButton']")
    private WebElement clickLoginButton;
    @FindBy(className = "user-info")
    private WebElement nameOfLoggedInAccount;
    @FindBy(xpath = "//div[@class='form-group field-login-form-password required has-error']//div[@class='dropdown-hint afterLeft']")
    private WebElement errorLoginOrPassword;
    @FindBy(xpath = "//div[@class='form-group field-login-form-login required has-error']//div[@class='dropdown-hint afterLeft']")
    private WebElement emptyEmail;
    @FindBy(xpath = "//div[@class='form-group field-login-form-password required has-error']//div[@class='dropdown-hint afterLeft']")
    private WebElement emptyPassword;
    @FindBy(xpath = "//li[7]/a")
    private WebElement clickLogOutButton;
    // Повторяющийся элемент + От другого языка отличается лишь двумя буквами, соответственно данный элемент можно
    // унифицировать и брать RU или UA в зависимости от того, что сейчас тестим - не зрозумів. Хз як зробити
    @FindBy(xpath = "//a[contains(text(),'RU')]")
    private WebElement languageRU;
    @FindBy(xpath = "//a[contains(text(),'UA')]")
    private WebElement languageUA;
    @FindBy(xpath = "//div[@class='logo']//span[1]")
    private WebElement allPrices;

    public String getAccountName() {
        return constant.emailName.substring(0, constant.emailName.indexOf("@"));
    }

    // Ты не показываешь тайтл, а берешь текст, тогда название getTitleName. Так же лучше держать подобные
    // методы в базовой странице, ибо на каждой странице будет тайтл - тайтл поміняв, по логіці я тільки раз чекаю сайт
    public String getTitleName() {
        return driver.getTitle();
    }

    // Очень много методов для логина. Зачем? Просто передавай либо корректные, либо не корректные значения
    // После можно рефреш страницы перенести в отдельный метод в базовую страницу
    // Проверка же что залогинились будет отдельно
    public HomePage logInIntoSite(String email, String password) {
        logInButton.click();
        loginField.sendKeys(email);
        passwordField.sendKeys(password);
        clickLoginButton.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
        return this;
        // Не вижу смысла создавать каждый раз новый объект. Просто возвращай this (сам объект, на
        // котором был вызван метод - DONE!)
    }

    public HomePage logInIntoSiteWithInvalidData(String email, String password) {
        logInButton.click();
        loginField.sendKeys(email);
        passwordField.sendKeys(password);
        clickLoginButton.click();
        // SOUT не стоит использовать. Если нужно - логирование - Done!
        // Это уже похоже на то, что нужно проверить. Зачем это делать здесь? Вынеси в отдельный метод,
        // возвращай булеан и будет тебе счастье - Done!
        return this;
    }

    public HomePage logInIntoSiteWithInvalidData(String email) {
        logInButton.click();
        loginField.sendKeys(email);
        clickLoginButton.click();
        System.out.println("You are trying to Login with empty password");
        System.out.println(emptyPassword.getText() + " Was displayed");
        return this;
    }

    public HomePage logInIntoSiteWithInvalidData() {
        logInButton.click();
        clickLoginButton.click();
        System.out.println("You are trying to Login with empty email and password");
        System.out.println("Email field shows : " + emptyEmail.getText());
        System.out.println("Password field field shows : " + emptyPassword.getText());
        return this;
    }

    public HomePage logOut() {
        //logInIntoSite(emailName,passwordData);
        logInButton.click();
        clickLogOutButton.click();
        return this;
    }


    public String getNameOfLoggedInAccount() {
        return nameOfLoggedInAccount.getText();
    }

    public boolean isErrorEmptyEmailDisplayed() {
        return emptyEmail.isDisplayed();
    }

    public boolean isErrorEmptyPasswordDisplayed() {
        return emptyPassword.isDisplayed();
    }

    public boolean isErrorLoginOrPasswordDisplayed() {
        return errorLoginOrPassword.isDisplayed();
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
        return getAllPrices().getText();
    }

    public WebElement getAllPrices() {
        return allPrices;
    }
}
