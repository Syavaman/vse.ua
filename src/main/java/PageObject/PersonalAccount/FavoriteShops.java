package PageObject.PersonalAccount;

import PageObject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FavoriteShops extends BasePage {
    public FavoriteShops(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "user-info")
    private WebElement clickUserInfo;
    @FindBy(xpath = "//*[local-name()='svg']//following::span")
    private WebElement shopsMenu;
    @FindBy(xpath = "//ul[@class='magazine__list']//li//a")
    private WebElement findedShop;
    @FindBy(xpath = "//a[contains(@class,'favorite-toggle')]")
    private WebElement addTofavorite;
    @FindBy(xpath = "//div[@class='links dropdown']//li[1]//a[1]//span[1]")
    private WebElement favoriteShops;
    @FindBy(xpath = "//div[@class='po-magazine-logo']//a//img")
    private WebElement favoriteShopLogo;
    @FindBy (xpath = "//a[@class='favorite-delete']")
    private WebElement deleteFromFavorite;
    @FindBy (xpath = "//div[@class='about-site-head']")
    private WebElement emptyFavoriteShopList;
    @FindBy(className = "search-text-input")
    private WebElement searchField;
    @FindBy(xpath = "//div[@class='search-group-submit']//input")
    private WebElement searchButton;



    public FavoriteShops addToFavorites(String searchData){
        shopsMenu.click();
        searchField.click();
        searchField.sendKeys(searchData);
        searchButton.click();
        findedShop.click();
        addTofavorite.click();
        clickUserInfo.click();
        favoriteShops.click();
        return new FavoriteShops(driver);
    }

    public WebElement getFavoriteShopLogo() {
        return favoriteShopLogo;
    }

    public  FavoriteShops removeFromFavorites(){
        clickUserInfo.click();
        favoriteShops.click();
        System.out.println(favoriteShopLogo.getText() + "store is going to be removed from Favorites");
        deleteFromFavorite.click();
        driver.navigate().refresh();
        return new FavoriteShops(driver);
    }

    public WebElement getEmptyFavoriteShopList() {
        return emptyFavoriteShopList;
    }
}
