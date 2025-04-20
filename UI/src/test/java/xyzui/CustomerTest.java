package xyzui;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.AccountPage;
import page.LoginPage;
import page.TransactionPage;

import static Util.ConfProperties.getProperty;


@Epic("����� ����������������� ���������.")
    public class CustomerTest extends BaseTest {
        private AccountPage accountPage;
        private LoginPage loginPage;
        private TransactionPage transactionPage;

        @BeforeEach
        @Step("������������� �������")
        public void before() {
            accountPage = new AccountPage(driver);
            loginPage = new LoginPage(driver);
            transactionPage = new TransactionPage(driver);
        }

        @Feature("���� � �������")
        @Description("���� ����������������� ����� � �������.")
        @Severity(value = SeverityLevel.NORMAL)
        @Test
        @Issue("XYZ-UI-customer-login")
        @DisplayName("�-001")
        public void customerLoginTest() {
            driver.get(getProperty("loginPageUrl"));
            loginPage.clickCustomerLoginButton().selectTestUser(getProperty("userName")).clickSubmitLoginButton();
            Assertions.assertEquals("1004\n1005\n1006", accountPage.getAccountBillName());
            Assertions.assertEquals("Harry Potter", accountPage.getAccountNameFromPage());
        }
}
