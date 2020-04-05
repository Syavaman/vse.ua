package PersonalAccountTests;

import BrowsersAndListeners.Browser;
import PageObject.PersonalAccount.FavoriteShops;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FavoriteShopsTests extends Browser {
    private FavoriteShops favoriteShop;

    @BeforeClass
    public void setUp() {
        WebDriver webDriver = Browser.getInstance();
        favoriteShop = new FavoriteShops(webDriver);
        favoriteShop.openPageAndlogIn();
    }

    @BeforeMethod
    public void openBasePage() {
        favoriteShop.openPage();
    }

    @Test(priority = 1)
    public void addFavoriteShop() {
        favoriteShop.addToFavorites(favoriteShop.searchShop);
        WebElement addedFavoriteShop = favoriteShop.getFavoriteShopLogo();
        Assert.assertTrue(addedFavoriteShop.isDisplayed(), "Shop was not added to favorites");
    }

    @Test(priority = 1)
    public void removeFavoriteShop() {
        favoriteShop.removeFromFavorites();
        WebElement emptyFavoriteShop = favoriteShop.getEmptyFavoriteShopList();
        Assert.assertTrue(emptyFavoriteShop.isDisplayed(), "Shop was not removed from favorites");
    }


    @AfterClass
    public void tearDown() {
        Browser.killDriverInstance();
    }
}
