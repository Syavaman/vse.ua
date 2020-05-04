package SearchTests;
import BrowsersAndListeners.Browser;
import PageObject.Constants;
import PageObject.Search.SearchMenu;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchFieldTests {
    private SearchMenu searchField;
    private Constants constant;
    @BeforeClass
    public void setUp() {
        WebDriver webDriver = Browser.Browsers.FIREFOX.create();
        searchField = new SearchMenu(webDriver);
    }

    @BeforeMethod
    public void openBasePage() {
        searchField.openPage();
    }

    @Test
    public void verifySearchOption() {
        searchField.fillSearchFieldWithData(constant.searchProduct);
        String result = searchField.getSearchResult();
        // Магические строки -??
        Assert.assertTrue(result.contains("Samsung"), "Search feature does not work properly");
    }
    @Test
    public void verifySearchOptionWithInvalidData() {
        searchField.fillSearchFieldWithData(constant.wrongDataForSearch);
        // Можно вынести в отдельный метод создание ошибки. Например есть отдельный класс, который хранит
        // инфу либо о всех ошибках, либо только по поиску например. И из него все брать для тестов - хз, щось переробив
        String expectedResult = searchField.getWrongDataForSearch();
        String actualResult = searchField.getWrongEnteredSearchResult();
        Assert.assertEquals(actualResult,expectedResult,"Some results were shown for incorrect search");
    }

    @Test
    public void verifyShortSearchQuery(){
        // Магические строки - shortSearchQuery - done!)
        searchField.fillSearchFieldWithData(constant.shortQuery);
        String actualResult =searchField.getWrongEnteredSearchResult();;
        Assert.assertTrue(actualResult.contains("Слишком короткий запрос"),"Search with one character works");
    }

    @Test
    public void verifyClearOfSearchMenu(){
        searchField.ClearingOfSearch();
        String result = searchField.getClearedSearchField();
       Assert.assertFalse(result.contains(constant.searchProduct), "Clear of search does not work");
    }

   // @AfterClass
    public void tearDown() {
        Browser.killDriverInstance();
    }
}
