package Util;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import page.AccountPage;
import page.LoginPage;

import static Util.ConfProperties.getProperty;

public class TestUtils {
    @Step("����������� � ������ ������� �������")
    public static void authUserAccount(WebDriver driver, LoginPage loginPage) {
        driver.get(getProperty("loginPageUrl"));
        loginPage.clickCustomerLoginButton().selectTestUser(getProperty("userName")).clickSubmitLoginButton();
    }

    @Step("����������� � ������ ������� ���������")
    public static void authManagerAccount(WebDriver driver, LoginPage loginPage) {
        driver.get(getProperty("loginPageUrl"));
        loginPage.clickManagerLoginButton();
    }

    @Step("�������� ��������")
    public static void sendTestDeposit(WebDriver driver, AccountPage accountPage) {
        accountPage.clickDepositButton().fillAmountDepositInput("100").clickSubmitDepositButton();
    }
}
