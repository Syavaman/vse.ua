import PageObject.HomePage.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SampleOfTests extends Browser {
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

    @Test(priority = 1)
    public void verifyTitleOfPage() {
        String title = homepage.showTitle();
        Assert.assertEquals("Все ценіы Киева и Украины: товары и услуги, магазины", title, "Title contains invalid info");
    }

    @Test(priority = 3)
    public void loginWithValidCredentials() {
        homepage.LogInIntoSite("syava1204@gmail.com", "Svyatik12");
        String nameOfLoggedInAccount = homepage.getNameOfLoggedInAccount().getText();
        Assert.assertEquals("syava1204", nameOfLoggedInAccount, "Name is not correct or user is not authorised");
    }

    @Test(priority = 2)
    public void verifySearchOption() {
        homepage.fillSearchFieldWithData("Samsung");
        String result = homepage.getSearchResult();
        Assert.assertTrue(result.contains("Samsung"), "Search feature does not work properly");

    }

    @AfterClass
    public void tearDown() {
        Browser.killDriverInstance();
    }


}
