import BrowsersAndListeners.Browser;
import PageObject.HomePage.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LanguageTests extends Browser {
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

    @Test (priority = 1)
    public void checkSwitchToUkrainian(){
        homepage.checkUkrainianLanguage();
        WebElement UA = homepage.getLanguageUA();
        Assert.assertEquals(UA.isEnabled(),false);
    }



   // @AfterClass
    public void tearDown() {
        Browser.killDriverInstance();
    }
}
