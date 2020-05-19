package PageObject.HomePage;

import PageObject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageErrors extends BasePage {
    public LoginPageErrors(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='form-group field-login-form-password required has-error']//div[@class='dropdown-hint afterLeft']")
    private WebElement errorIncorrectData;
    @FindBy(xpath = "//div[contains(text(),'Необходимо заполнить «Электронная почта».')]")
    private WebElement emptyEmail;

    public boolean isErrorEmptyEmailDisplayed() {
        String emptyEmailError = "Необходимо заполнить «Электронная почта».";
        return emptyEmailError.equals(emptyEmail.getText());
    }

    public boolean isErrorEmptyPasswordDisplayed() {
        String emptyPassword = "Необходимо заполнить «Пароль».";
        return emptyPassword.equals(errorIncorrectData.getText());
    }

    public boolean isErrorLoginOrPasswordDisplayed() {
        String incorrectLoginOrPassword = "Неверное имя пользователя или пароль";
        return incorrectLoginOrPassword.equals(errorIncorrectData.getText());
    }


}
