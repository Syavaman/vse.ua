package PersonalAccountTests;

import BrowsersAndListeners.Browser;
import PageObject.Constants;
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
    private Constants constant;

    @BeforeClass
    public void setUp() {
        WebDriver webDriver = Browser.Browsers.FIREFOX.create();
        favoriteShop = new FavoriteShops(webDriver);
        favoriteShop.openPageAndlogIn();
    }

    // Вижу такие шаги перед тестами:
    // Открыть страницу -> Залогинится -> Открыть страницу опять
    // Это точно нужно?
    @BeforeMethod
    public void openBasePage() {
        favoriteShop.openPage();
    }

    // Тест добавляет в фавориты магаз, но не удаляет сам.
    // Если тесты вносят изменения в базу, юзера, предмет, что угодно - после теста мы удаляем наши следы -два окремих теста, перший добавляє в магазин, а наступний удаляє його
    @Test(priority = 1)
    public void addFavoriteShop() {
        // А потом в этом методе ты снова открываешь страницу. Поиск на сколько я помню есть везде, поэтому страницу 50 раз одну и ту же
        // можно не открывать)
        favoriteShop.addToFavorites(constant.searchShop);
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
