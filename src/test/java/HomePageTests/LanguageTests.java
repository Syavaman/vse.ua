package HomePageTests;

import BrowsersAndListeners.Browser;
import PageObject.HomePage.HomePage;
import org.openqa.selenium.WebDriver;
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

    @Test(priority = 1)
    public void checkSwitchToUkrainian() {
        homepage.checkUkrainianLanguage();
        // Магические значение и строки выносим в константы / конфиги / енамы. Чтобы если что-то изменилось, то пришлось бы исправлять только в одном месте, а не в куче тестов - Done!
        Assert.assertEquals(homepage.getLanguage(), "ВСІ ЦІНИ", "Language was not switched to Ukrainian");
    }

    @Test(priority = 2)
    public void checkSwitchToRussian() {
        homepage.checkRussianLanguage();
        Assert.assertEquals(homepage.getLanguage(), "ВСЕ ЦЕНЫ", "Language was not switched to Ukrainian");
    }

    @AfterClass
    public void tearDown() {
        Browser.killDriverInstance();
    }
}
