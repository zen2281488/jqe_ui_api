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
import static Util.TestUtils.authAccount;
import static Util.WriteUtils.writeTransactionsToCSV;


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
    @DisplayName("T-001")
    public void customerLoginTest() {
        driver.get(getProperty("loginPageUrl"));
        loginPage.clickCustomerLoginButton().selectTestUser(getProperty("userName")).clickSubmitLoginButton();
        Assertions.assertEquals("1004\n1005\n1006", accountPage.getAccountBillName());
        Assertions.assertEquals("Harry Potter", accountPage.getAccountNameFromPage());
    }

    @Feature("123")
    @Description("123")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("XYZ-UI-customer-depositWithdrawl")
    @DisplayName("T-004")
    public void customerDepositWithdrawlTest() {
        authAccount(driver, loginPage);

        accountPage.clickDepositButton().fillAmountDepositInput(100).clickSubmitDepositButton().clickWithDrawlButton().fillAmountWithDrawlInput(100).clickSubmitWithdrawlButton();
        Assertions.assertEquals("0", accountPage.getBalance());
        driver.navigate().refresh();
        Assertions.assertEquals("0", accountPage.getBalance());
        accountPage.clickTransactionsButton();
        var transactions = transactionPage.getTransactions();
        writeTransactionsToCSV(transactions);

        Assertions.assertEquals(100, transactions.get(0).amount);
        Assertions.assertEquals(100, transactions.get(1).amount);
        Assertions.assertEquals("Credit", transactions.get(0).transactionType);
        Assertions.assertEquals("Debit", transactions.get(1).transactionType);
    }

    @Feature("Переход на страницу транзакций")
    @Description("Тест работоспособности перехода на страницу транзакции.")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("XYZ-UI-customer-transactions")
    @DisplayName("T-005")
    public void testNavigationButtonOpenTransactions() {
        authAccount(driver, loginPage);
        accountPage.clickTransactionsButton();
        transactionPage.tableHeadIsVisible().clickBack();
    }

    @Feature("Открытие окна внесения депозита")
    @Description("Тест работоспособности открытия модального окна внесения депозита")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("XYZ-UI-customer-deposit")
    @DisplayName("T-002")
    public void testNavigationButtonOpenDeposit() {
        authAccount(driver, loginPage);
        accountPage.clickDepositButton().submitDepositButtonIsVisible().depositInputIsVisible();
    }

    @Feature("Открытие окна снятия депозита")
    @Description("Тест работоспособности открытия модального окна снятия депозита")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("XYZ-UI-customer-withdrawl")
    @DisplayName("T-003")
    public void testNavigationButtonOpenWithdrawl() {
        authAccount(driver, loginPage);
        accountPage.clickWithDrawlButton().submitWithdrawButtonIsVisible().withdrawInputIsVisible();
    }
}
