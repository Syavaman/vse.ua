package HomePageTests;

import PageObject.HomePage.HomePage;
import BrowsersAndListeners.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends Browser {
    private HomePage homepage;

    @BeforeClass
    public void setUp() {
        WebDriver webDriver = Browser.getInstance();
        homepage = new HomePage(webDriver);
    }

    @BeforeMethod
    public void openBasePage() {
        homepage.openPage();
    }

    @Test
    public void verifyTitleOfPage() {
        String title = homepage.showTitle();
        Assert.assertEquals(title, "Все цены Киева и Украины: товары и услуги, магазины", "Title contains invalid info");
    }

    @Test(priority = 1)
    public void loginWithInvalidCredentials() {
        homepage.logInIntoSiteWithInvalidData(homepage.emailName, "1234");
        WebElement errorTable = homepage.getErrorLoginOrPassword();
        Assert.assertEquals(errorTable.isDisplayed(), true, "Error about wrong data was not displayed");

    }

    @Test(priority = 1)
    public void loginWithEmptyPassword() {
        homepage.logInIntoSiteWithInvalidData(homepage.emailName);
        WebElement emptyPasswordField = homepage.getEmptyPassword();
        Assert.assertEquals(emptyPasswordField.isDisplayed(), true, "Error about wrong data was not displayed");
    }

    @Test(priority = 1)
    public void loginWithEmptyCredentials() {
        homepage.logInIntoSiteWithInvalidData();
        WebElement emptyEmailField = homepage.getEmptyEmail();
        Assert.assertEquals(emptyEmailField.isDisplayed(), true, "Error about wrong data was not displayed");
    }

    @Test(priority = 1)
    public void logOutFromSite() {
        homepage.logOut();
        String logOutAcc = homepage.getNameOfLoggedInAccount().getText();
        Assert.assertEquals(logOutAcc, "Войти", "User was not logged out");
    }

    @Test(priority = 2)
    public void loginWithValidCredentials() {
        homepage.logInIntoSite(homepage.emailName, homepage.passwordData);
        String nameOfLoggedInAccount = homepage.getNameOfLoggedInAccount().getText();
        String accountName = homepage.getAccountName();
        Assert.assertEquals(nameOfLoggedInAccount, accountName, "Name is not correct or user is not authorised");
    }


    @AfterClass
    public void tearDown() {
        Browser.killDriverInstance();
    }


}
