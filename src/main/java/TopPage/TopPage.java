package TopPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TopPage {
    WebDriver driver;

    public TopPage(WebDriver driver) {
        this.driver = driver;
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
    @FindBy (className = "user-info")
    private WebElement nameOfLoggedInAccount;


    public TopPage fillSearchFieldWithData(String data) {
        searchField.click();
        searchField.sendKeys(data);
        searchButton.click();
        return new TopPage(driver);
    }

    public String getSearchResult() {
        return searchResult.getText();
    }

    public  TopPage LogInIntoSite (String email, String password ){
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
        return new TopPage(driver);
    }

    public WebElement getNameOfLoggedInAccount() {
        return nameOfLoggedInAccount;
    }
}
