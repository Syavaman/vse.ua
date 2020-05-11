package HomePageTests;

import BrowsersAndListeners.Browser;
import PageObject.HomePage.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

    @Test
    public void checkSwitchBetweenLanguages() {
        SoftAssert soft = new SoftAssert();
        homepage.checkUkrainianLanguage();
        soft.assertEquals(homepage.getLanguage(), "ВСІ ЦІНИ", "Language was not switched to Ukrainian");
        homepage.checkRussianLanguage();
        soft.assertEquals(homepage.getLanguage(), "ВСЕ ЦЕНЫ", "Language was not switched to Ukrainian");
        soft.assertAll();
    }


    @AfterClass
    public void tearDown() {
        Browser.killDriverInstance();
    }
}
