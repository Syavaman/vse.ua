import TopPage.TopPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class FirstTest {
    private TopPage toppage;

    @BeforeClass
    public void setUp() {
        WebDriver webDriver = SingletonWD.getInstance(System.getProperty("browser"));
        toppage = PageFactory.initElements(webDriver, TopPage.class);
        webDriver.get("https://vse.ua");
    }

    @Test(priority = 1)
    public void verifyTitleOfPage() {
        String title = SingletonWD.driver.getTitle();
        Assert.assertEquals("Все цены Киева и Украины: товары и услуги, магазины", title, "Title contains invalid info");
    }

    @Test(priority = 2)
    public void loginWithValidCredentials() {
       toppage.LogInIntoSite("syava1204@gmail.com", "Svyatik12");
        String nameOfLoggedInAccount = toppage.getNameOfLoggedInAccount().getText();
        Assert.assertEquals("syava1204",nameOfLoggedInAccount,"Name is not correct or user is not authorised");
    }

    @Test(priority = 3)
    public void verifySearchOption() {
        toppage.fillSearchFieldWithData("Samsung");
        String result = toppage.getSearchResult();
        Assert.assertTrue(result.contains("Samsung"), "Search feature does not work properly");

    }

    @AfterClass
    public void tearDown() {
        SingletonWD.killDriverInstance();
    }


}
