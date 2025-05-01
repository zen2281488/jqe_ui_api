package page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class LoginPage extends BasePage<LoginPage> {
    public LoginPage(WebDriver browser) {
        super(browser);
    }

    @FindBy(css = "[ng-click^=customer]")
    private WebElement customerLoginButton;
    @FindBy(id = "userSelect")
    private WebElement yourNameSelector;
    @FindBy(css = ".btn-default")
    private WebElement submitLoginButton;

    @Step("Клик по кнопке 'Customer Login'")
    public LoginPage clickCustomerLoginButton() {
        clickElement(customerLoginButton);
        return this;
    }

    @Step("Клик по кнопке 'Login'")
    public LoginPage clickSubmitLoginButton() {
        clickElement(submitLoginButton);
        return this;
    }

    @Step("Выбор Имени: {userName} в селекторе")
    public LoginPage selectTestUser(String userName) {
        wait.until(ExpectedConditions.visibilityOf(yourNameSelector));
        new Select(yourNameSelector).selectByVisibleText(userName);
        return this;
    }
}
