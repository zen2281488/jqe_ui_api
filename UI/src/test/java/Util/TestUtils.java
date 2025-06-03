package Util;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import page.AccountPage;
import page.LoginPage;

import static Util.ConfProperties.getProperty;

public class TestUtils {

    @Step("Авторизация под обычным пользователем")
    public static void authUserAccount(WebDriver driver, LoginPage loginPage) {
        driver.get(getProperty("loginPageUrl"));
        loginPage
                .clickCustomerLoginButton()
                .selectTestUser(getProperty("userName"))
                .clickSubmitLoginButton();
    }

    @Step("Авторизация под менеджером")
    public static void authManagerAccount(WebDriver driver, LoginPage loginPage) {
        driver.get(getProperty("loginPageUrl"));
        loginPage.clickManagerLoginButton();
    }

    @Step("Внесение тестового депозита на сумму 100")
    public static void sendTestDeposit(WebDriver driver, AccountPage accountPage) {
        accountPage
                .clickDepositButton()
                .fillAmountDepositInput("100")
                .clickSubmitDepositButton();
    }
}
