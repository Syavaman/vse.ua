package PageObject.Search;

import PageObject.BasePage;
import PageObject.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static PageObject.Constants.wrongDataForSearch;

public class SearchMenu extends BasePage {
    private Constants constant;
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

    public SearchMenu  ClearingOfSearch(){
        fillSearchFieldWithData(constant.searchProduct);
        clearSearchButton.click();
        return this;
    }

    public String getWrongEnteredSearchResult() {
        return wrongEnteredSearchResult.getText();
    }

    public String getWrongDataForSearch() {
        return String.format("Ничего не найдено по запросу "+ constant.wrongDataForSearch);
    }
}
