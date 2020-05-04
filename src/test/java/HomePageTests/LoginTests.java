package HomePageTests;

import PageObject.Constants;
import PageObject.HomePage.HomePage;
import BrowsersAndListeners.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends Browser {
    private HomePage homepage;
    private Constants constant;

    @BeforeClass
    public void setUp() {
        WebDriver webDriver = Browser.Browsers.FIREFOX.create();
        homepage = new HomePage(webDriver);
        constant = new Constants();
    }

    @BeforeMethod
    public void openBasePage() {
        homepage.openPage();
    }

    @Test
    public void verifyTitleOfPage() {
        // та стринга очень короткая и больше нигде не используется. Можно не выносить отдельно, а вызывать метод внутри проверки - Done!
        Assert.assertEquals(homepage.getTitleName(), "Все цены Киева и Украины: товары и услуги, магазины", "Title contains invalid info");
    }

    @Test(priority = 1)
    public void loginWithInvalidCredentials() {
        homepage.logInIntoSiteWithInvalidData(constant.emailName, constant.wrongPassw0rd);
        // Получение элементов внутри тестов конечно работает, но это плохая практика. Мы прячем жлементы внутри страницы не просто так.
        // Решением могло бы быть получение true / false ри вызове метода homepage.isErrorLoginOrPasswordDisplayed() -  Done
        Assert.assertEquals(homepage.isErrorLoginOrPasswordDisplayed(), true, "Error about incorrect was not displayed");
        // Все ошибки тоже лучше хранить отвельно, раз они имеют постоянное значение - для трьох помилок окремий клас зробити?
    }

    @Test(priority = 1)
    public void loginWithEmptyPassword() {
        homepage.logInIntoSiteWithInvalidData(constant.emailName);
        Assert.assertEquals(homepage.isErrorLoginOrPasswordDisplayed(), true, "Error about wrong data was not displayed");
    }

    @Test(priority = 1)
    public void loginWithEmptyCredentials() {
        homepage.logInIntoSiteWithInvalidData();
        Assert.assertEquals(homepage.isErrorEmptyEmailDisplayed(), true, "Error about wrong data was not displayed");
    }

    // Этот тест явно зависит от другого теста. Логику я понимаю, но стоит все же стараться делать тесты не зависимыми друг от друга
    // Например скорее всего я не смогу просто запустить этот тест, потому что пользователь не залогинен и тест упадет - переписа метод, але логіка для логаута як на мене то ОК
    @Test(priority = 3)
    public void logOutFromSite() {
        homepage.logOut();
        // Если это метод для получения именно имени, то метод getText() можно вызвать внутри метода getNameOfLoggedInAccount() - Done!
        Assert.assertEquals(homepage.getNameOfLoggedInAccount(), "Войти", "User was not logged out");
    }

    @Test(priority = 2)
    public void loginWithValidCredentials() {
        homepage.logInIntoSite(constant.emailName, constant.passwordData);
        String nameOfLoggedInAccount = homepage.getNameOfLoggedInAccount();
        String accountName = homepage.getAccountName();
        Assert.assertEquals(nameOfLoggedInAccount, accountName, "Name is not correct or user is not authorised");
    }


    @AfterClass
    public void tearDown() {
        Browser.killDriverInstance();
    }


}
