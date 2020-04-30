package HomePageTests;

import PageObject.HomePage.HomePage;
import BrowsersAndListeners.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends Browser {
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
    public void verifyTitleOfPage() {
        String title = homepage.showTitle(); // та стринга очень короткая и больше нигде не используется. Можно не выносить отдельно, а вызывать метод внутри проверки
        Assert.assertEquals(title, "Все цены Киева и Украины: товары и услуги, магазины", "Title contains invalid info");
    }

    @Test(priority = 1)
    public void loginWithInvalidCredentials() {
        homepage.logInIntoSiteWithInvalidData(homepage.emailName, "1234");
        // Получение элементов внутри тестов конечно работает, но это плохая практика. Мы прячем жлементы внутри страницы не просто так.
        // Решением могло бы быть получение true / false ри вызове метода homepage.isErrorLoginOrPasswordDisplayed()
        WebElement errorTable = homepage.getErrorLoginOrPassword();
        Assert.assertEquals(errorTable.isDisplayed(), true, "Error about wrong data was not displayed");
        // Все ошибки тоже лучше хранить отвельно, раз они имеют постоянное значение
    }

    @Test(priority = 1)
    public void loginWithEmptyPassword() {
        homepage.logInIntoSiteWithInvalidData(homepage.emailName);
        WebElement emptyPasswordField = homepage.getEmptyPassword();
        Assert.assertEquals(emptyPasswordField.isDisplayed(), true, "Error about wrong data was not displayed");
    }

    @Test(priority = 1)
    public void loginWithEmptyCredentials() {
        homepage.logInIntoSiteWithInvalidData();
        WebElement emptyEmailField = homepage.getEmptyEmail();
        Assert.assertEquals(emptyEmailField.isDisplayed(), true, "Error about wrong data was not displayed");
    }

    // Этот тест явно зависит от другого теста. Логику я понимаю, но стоит все же стараться делать тесты не зависимыми друг от друга
    // Например скорее всего я не смогу просто запустить этот тест, потому что пользователь не залогинен и тест упадет
    @Test(priority = 1)
    public void logOutFromSite() {
        homepage.logOut();
        // Если это метод для получения именно имени, то метод getText() можно вызвать внутри метода getNameOfLoggedInAccount()
        String logOutAcc = homepage.getNameOfLoggedInAccount().getText();
        Assert.assertEquals(logOutAcc, "Войти", "User was not logged out");
    }

    @Test(priority = 2)
    public void loginWithValidCredentials() {
        homepage.logInIntoSite(homepage.emailName, homepage.passwordData);
        String nameOfLoggedInAccount = homepage.getNameOfLoggedInAccount().getText();
        String accountName = homepage.getAccountName();
        Assert.assertEquals(nameOfLoggedInAccount, accountName, "Name is not correct or user is not authorised");
    }


    @AfterClass
    public void tearDown() {
        Browser.killDriverInstance();
    }


}
