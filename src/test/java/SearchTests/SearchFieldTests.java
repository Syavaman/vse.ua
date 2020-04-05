package SearchTests;
import BrowsersAndListeners.Browser;
import PageObject.Search.SearchMenu;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchFieldTests {
    private SearchMenu searchField;

    @BeforeClass
    public void setUp() {
        WebDriver webDriver = Browser.getInstance();
        searchField = new SearchMenu(webDriver);
    }

    @BeforeMethod
    public void openBasePage() {
        searchField.openPage();
    }

    @Test
    public void verifySearchOption() {
        searchField.fillSearchFieldWithData(searchField.searchProduct);
        String result = searchField.getSearchResult();
        Assert.assertTrue(result.contains("Samsung"), "Search feature does not work properly");
    }
    @Test
    public void verifySearchOptionWithInvalidData() {
        searchField.fillSearchFieldWithData(searchField.getWrongDataForSearch());
        String expectedResult = String.format("Ничего не найдено по запросу "+searchField.getWrongDataForSearch());
        String actualResult = searchField.getWrongEnteredSearchResult().getText();
        Assert.assertEquals(actualResult,expectedResult,"Some results were shown for incorrect search");
    }

    @Test
    public void verifyClearOfSearchMenu(){
        searchField.verifyClearingOfSearch();
        String result = searchField.getClearedSearchField();
       Assert.assertFalse(result.contains("Philips"), "Clear of search does not work");
    }

    @AfterClass
    public void tearDown() {
        Browser.killDriverInstance();
    }
}
