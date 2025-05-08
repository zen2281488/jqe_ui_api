package Util;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import page.LoginPage;

import static Util.ConfProperties.getProperty;

public class TestUtils {
    @Step("Авторизация")
    public static void authAccount(WebDriver driver, LoginPage loginPage) {
        driver.get(getProperty("loginPageUrl"));
        loginPage.clickCustomerLoginButton().selectTestUser(getProperty("userName")).clickSubmitLoginButton();
    }
}
