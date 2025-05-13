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
    @FindBy(css = "[ng-click^=manager]")
    private WebElement managerLoginButton;
    @FindBy(id = "userSelect")
    private WebElement yourNameSelector;
    @FindBy(css = ".btn-default")
    private WebElement submitLoginButton;

    @Step("Клик по кнопке 'Customer Login'")
    public LoginPage clickCustomerLoginButton() {
        return clickElement(customerLoginButton);
    }

    @Step("Клик по кнопке 'Login'")
    public LoginPage clickSubmitLoginButton() {
        return clickElement(submitLoginButton);
    }
    @Step("Клик по кнопке 'Bank Manager Login'")
    public LoginPage clickManagerLoginButton() {
        return clickElement(managerLoginButton);
    }

    @Step("Выбор Имени: {userName} в селекторе")
    public LoginPage selectTestUser(String userName) {
        waitElement(yourNameSelector);
        new Select(yourNameSelector).selectByVisibleText(userName);
        return this;
    }
}
