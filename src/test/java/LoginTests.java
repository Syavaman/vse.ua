import PageObject.BasePage;
import PageObject.HomePage.HomePage;
import BrowsersAndListeners.Browser;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.openqa.selenium.By;
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
        homepage.LogInIntoSiteWithInvalidData(homepage.emailName, "1234");
        WebElement errorTable = homepage.getErrorLoginOrPassword();
        Assert.assertEquals(errorTable.isDisplayed(), true, "Error about wrong data was not displayed");

    }

    @Test(priority = 2)
    public void loginWithEmptyPassword() {
        homepage.LogInIntoSiteWithInvalidData(homepage.emailName);
        WebElement emptyPasswordField = homepage.getEmptyPassword();
        Assert.assertEquals(emptyPasswordField.isDisplayed(), true, "Error about wrong data was not displayed");
    }

    @Test(priority = 3)
    public void loginWithEmptyCredentials() {
        homepage.LogInIntoSiteWithInvalidData();
        WebElement emptyEmailField = homepage.getEmptyEmail();
        Assert.assertEquals(emptyEmailField.isDisplayed(), true, "Error about wrong data was not displayed");
    }

    @Test(priority = 4)
    public void loginWithValidCredentials() {
        homepage.LogInIntoSite(homepage.emailName, homepage.passwordData);
        String nameOfLoggedInAccount = homepage.getNameOfLoggedInAccount().getText();
        String accountName = homepage.getAccountName();
        Assert.assertEquals(nameOfLoggedInAccount, accountName, "Name is not correct or user is not authorised");
    }


    @Test(priority = 3)
    public void verifySearchOption() {
        homepage.fillSearchFieldWithData("Samsung");
        String result = homepage.getSearchResult();
        Assert.assertTrue(result.contains("Samsung"), "Search feature does not work properly");

    }


    //@AfterClass
    public void tearDown() {
        Browser.killDriverInstance();
    }


}
