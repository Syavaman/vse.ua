package HomePageTests;

import PageObject.Constants;
import PageObject.HomePage.HomePage;
import BrowsersAndListeners.Browser;
import PageObject.HomePage.LoginPageErrors;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTestsFlow extends Browser {
    private HomePage homepage;
    private Constants constant;
    private LoginPageErrors loginError;

    @BeforeClass
    public void setUp() {
       WebDriver webDriver = Browser.getInstance();
        homepage = new HomePage(webDriver);
        loginError = new LoginPageErrors(webDriver);
        constant = new Constants();
    }

    @BeforeMethod
    public void openBasePage() {
        homepage.openPage();
    }

    @Test
    public void verifyTitleOfPage() {
        Assert.assertEquals(homepage.getTitleName(), "Все цены Киева и Украины: товары и услуги, магазины", "Title contains invalid info");
    }

    @Test(priority = 1)
    public void loginWithInvalidCredentials() {
        homepage.logInIntoSiteWithInvalidData(constant.emailName, constant.wrongPassw0rd);
        Assert.assertEquals(loginError.isErrorLoginOrPasswordDisplayed(), true, "Error about incorrect was not displayed");
    }

    @Test(priority = 1)
    public void loginWithEmptyPassword() {
        homepage.logInIntoSiteWithInvalidData(constant.emailName,constant.emptyField);
        Assert.assertEquals(loginError.isErrorEmptyPasswordDisplayed(), true, "Error about wrong data was not displayed");
    }

    @Test(priority = 1)
    public void loginWithEmptyCredentials() {
        homepage.logInIntoSiteWithInvalidData(constant.emptyField,constant.emptyField);
        Assert.assertEquals(loginError.isErrorEmptyEmailDisplayed(), true, "Error about wrong data was not displayed");
    }

    @Test(priority = 3)
    public void logOutFromSite() {
        homepage.logOut();
        Assert.assertEquals(homepage.getNameOfLoggedInAccount(), "Войти", "User was not logged out");
    }

    @Test(priority = 2)
    public void loginWithValidCredentials() {
        homepage.logInIntoSite(constant.emailName, constant.passwordData);
        String nameOfLoggedInAccount = homepage.getNameOfLoggedInAccount();
        String accountName = homepage.getAccountName();
        Assert.assertEquals(nameOfLoggedInAccount, accountName, "Name is not correct or user is not authorised");
    }


    @AfterClass
    public void tearDown() {
        Browser.killDriverInstance();
    }


}
