package PageObject.PersonalAccount;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;
import java.util.List;

public class FavoriteShops extends BasePage {
    public FavoriteShops(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "user-info")
    private WebElement clickUserInfo;
    @FindBy(xpath = "//div[@class='header-menu']//li[2]")
    private WebElement shopsMenu;
    @FindBy(xpath = "//ul[@class='magazine__list']")
    private WebElement listOfShops;
    @FindBy(xpath = "//a[contains(@class,'favorite-toggle')]")
    private WebElement addTofavorite;
    @FindBy(xpath = "//div[@class='links dropdown']//li[1]//a[1]//span[1]") // О_О - привязався як мін
    private WebElement favoriteShops;
    @FindBy(xpath = "//div[@class='po-magazine-logo']//a//img")
    private WebElement favoriteShopLogo;
    @FindBy(xpath = "//a[@class='favorite-delete']")
    private WebElement deleteFromFavorite;
    @FindBy(xpath = "//div[@class='about-site-head']")
    private WebElement emptyFavoriteShopList;
    @FindBy(className = "search-text-input")
    private WebElement searchField;
    @FindBy(xpath = "//div[@class='search-group-submit']//input")
    private WebElement searchButton;



    public FavoriteShops addShopToFavorites(String searchData) { // addShopToFavorites - done
        shopsMenu.click();
        searchField.click();
        searchField.sendKeys(searchData);
        searchButton.click();
        List<WebElement> shopList=listOfShops.findElements(By.tagName("li"));
        for (WebElement li : shopList) {
            if (li.getText().contains(searchData)) {
                li.click();
                break;
            }
        }
        addTofavorite.click();
        clickUserInfo.click();
        favoriteShops.click();
        return this;
    }

    public WebElement getFavoriteShopLogo() {
        return favoriteShopLogo;
    }

    public FavoriteShops removeFromFavorites() {
        openPage();
        clickUserInfo.click();
        favoriteShops.click();
        deleteFromFavorite.click();
        driver.navigate().refresh();
        return this;
    }

    public WebElement getEmptyFavoriteShopList() {
        return emptyFavoriteShopList;
    }
}
