package PageObject.Search;

import PageObject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchMenu extends BasePage {
    public SearchMenu(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "search-text-input")
    private WebElement searchField;
    @FindBy(xpath = "//div[@class='search-group-submit']//input")
    private WebElement searchButton;
    @FindBy(xpath = "//li/h1/span")
    private WebElement searchResult;
    @FindBy (xpath = "//a[@class='search-delete']")
    private WebElement clearSearchButton;
    @FindBy (xpath = "//div[@class='search-group-input']")
    private WebElement  clearedSearchField;
    @FindBy (xpath = "//div[@class='search-info-head']")
    private WebElement wrongEnteredSearchResult;
    // А вот я и нашла дату не в тестах) Не храни такое внутри страниц
    private String wrongDataForSearch = "dasdasdasdasd";



    public SearchMenu fillSearchFieldWithData(String data) {
        searchField.click();
        searchField.sendKeys(data);
        searchButton.click();
        return new SearchMenu(driver);
    }

    public String getSearchResult() {
        return searchResult.getText();
    }

    public String getClearedSearchField() {
        return clearedSearchField.getText();
    }

    public SearchMenu verifyClearingOfSearch(){
        // А как ты тут проверяешь что поле очистилось? тут ты его просто
        // очищаешь на сколько я вижу. к тому же делаешь это двумя способами, хотя по идее кнопки бы хватило, без обновления страницы
        fillSearchFieldWithData("Philips");
        clearSearchButton.click();
        driver.navigate().refresh();
        return new SearchMenu(driver);
    }

    public WebElement getWrongEnteredSearchResult() {
        return wrongEnteredSearchResult;
    }

    public String getWrongDataForSearch() {
        return wrongDataForSearch;
    }
}
