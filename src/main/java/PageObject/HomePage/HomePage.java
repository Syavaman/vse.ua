package PageObject.HomePage;

import PageObject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.SQLOutput;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @FindBy(className = "search-text-input")
    private WebElement searchField;
    @FindBy(xpath = "//div[@class='search-group-submit']//input")
    private WebElement searchButton;
    @FindBy(xpath = "//li/h1/span")
    private WebElement searchResult;
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
    @FindBy (xpath = "//a[contains(text(),'RU')]")
    private WebElement languageRU;
    @FindBy (xpath = "//a[contains(text(),'UA')]")
    private WebElement languageUA;
    @FindBy (xpath = "//div[@class='logo']//span[1]")
    private WebElement allPrices;


    public HomePage fillSearchFieldWithData(String data) {
        searchField.click();
        searchField.sendKeys(data);
        searchButton.click();
        return new HomePage(driver);
    }

    public String getAccountName() {
        return emailName.substring(0, emailName.indexOf("@"));
    }

    public String getSearchResult() {
        return searchResult.getText();
    }

    public String showTitle() {
        return driver.getTitle();
    }

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
        return new HomePage(driver);
    }

    public HomePage logInIntoSiteWithInvalidData(String email, String password) {
        logInButton.click();
        loginField.sendKeys(email);
        passwordField.sendKeys(password);
        clickLoginButton.click();
        System.out.println("You are trying to Login with Invalid Credentials");
        System.out.println(getErrorLoginOrPassword().getText() + " Was displayed");
        return new HomePage(driver);
    }

    public HomePage logInIntoSiteWithInvalidData(String email) {
        logInButton.click();
        loginField.sendKeys(email);
        clickLoginButton.click();
        System.out.println("You are trying to Login with empty password");
        System.out.println(emptyPassword.getText() + " Was displayed");
        return new HomePage(driver);
    }

    public HomePage logInIntoSiteWithInvalidData() {
        logInButton.click();
        clickLoginButton.click();
        System.out.println("You are trying to Login with empty email and password");
        System.out.println("Email field shows : " + emptyEmail.getText());
        System.out.println("Password field field shows : " + emptyPassword.getText());
        return new HomePage(driver);
    }

    public HomePage logOut(){
        logInIntoSite(emailName,passwordData);
        logInButton.click();
        clickLogOutButton.click();
        return new HomePage(driver);
    }

    public WebElement getNameOfLoggedInAccount() {
        return nameOfLoggedInAccount;
    }

    public WebElement getEmptyEmail() {
        return emptyEmail;
    }

    public WebElement getEmptyPassword() {
        return emptyPassword;
    }

    public WebElement getErrorLoginOrPassword() {
        return errorLoginOrPassword;
    }

    public HomePage checkRussianLanguage (){
        languageRU.click();
        System.out.println("Language is changed to Russian and title is : " + driver.getTitle());
        return new HomePage(driver);
    }
    public HomePage checkUkrainianLanguage (){
        languageUA.click();
        System.out.println("Language is changed to Ukrainian and title is : " + driver.getTitle());
        return new HomePage(driver);
    }

    public WebElement getLanguageUA() {
        return languageUA;
    }

    public WebElement getAllPrices() {
        return allPrices;
    }
}
