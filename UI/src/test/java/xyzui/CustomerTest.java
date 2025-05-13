package xyzui;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import page.AccountPage;
import page.BankManagerPage;
import page.LoginPage;
import page.TransactionPage;

import static Util.ConfProperties.getProperty;
import static Util.TestUtils.authManagerAccount;
import static Util.TestUtils.authUserAccount;
import static Util.WriteUtils.*;


@Epic("����� ����������������� ���������.")
public class CustomerTest extends BaseTest {
    private AccountPage accountPage;
    private LoginPage loginPage;
    private TransactionPage transactionPage;
    private BankManagerPage bankManagerPage;

    @BeforeEach
    @Step("������������� �������")
    public void before() {
        accountPage = new AccountPage(driver);
        loginPage = new LoginPage(driver);
        transactionPage = new TransactionPage(driver);
        bankManagerPage = new BankManagerPage(driver);

    }

    @Feature("Авторизация")
    @Description("Тестирование авторизации в личный кабинет клиента")
    @Severity(value = SeverityLevel.CRITICAL)
    @Test
    @Issue("XYZ-UI-customer-login")
    @DisplayName("T-001")
    public void customerLoginTest() {
        driver.get(getProperty("loginPageUrl"));
        loginPage.clickCustomerLoginButton().selectTestUser(getProperty("userName")).clickSubmitLoginButton();
        Assertions.assertEquals("1004\n1005\n1006", accountPage.getAccountBillName());
        Assertions.assertEquals("Harry Potter", accountPage.getAccountNameFromPage());
    }

    @Feature("Снятие и внесение депозита")
    @Description("Тестирование снятия и внесения депозита")
    @Severity(value = SeverityLevel.CRITICAL)
    @Test
    @Issue("XYZ-UI-customer-depositWithdrawl")
    @DisplayName("T-004")
    public void customerDepositWithdrawlTest() {
        authUserAccount(driver, loginPage);

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

    @Feature("Транзакции")
    @Description("Тест работоспособности перехода на страницу транзакции.")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("XYZ-UI-customer-transactions")
    @DisplayName("T-005")
    public void testNavigationButtonOpenTransactions() {
        authUserAccount(driver, loginPage);
        accountPage.clickTransactionsButton();
        transactionPage.tableHeadIsVisible().clickBack();
    }

    @Feature("Снятие и внесение депозита")
    @Description("Тест работоспособности открытия модального окна внесения депозита")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("XYZ-UI-customer-deposit")
    @DisplayName("T-002")
    public void testNavigationButtonOpenDeposit() {
        authUserAccount(driver, loginPage);
        accountPage.clickDepositButton().submitDepositButtonIsVisible().depositInputIsVisible();
    }

    @Feature("Снятие и внесение депозита")
    @Description("Тест работоспособности открытия модального окна снятия депозита")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("XYZ-UI-customer-withdrawl")
    @DisplayName("T-003")
    public void testNavigationButtonOpenWithdrawl() {
        authUserAccount(driver, loginPage);
        accountPage.clickWithDrawlButton().submitWithdrawButtonIsVisible().withdrawInputIsVisible();
    }

    @Feature("Авторизация")
    @Description("Тестирование авторизации в личный кабинет менеджера")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("XYZ-UI-manager-login")
    @DisplayName("T-006")
    public void testManagerLogin() {
        driver.get(getProperty("loginPageUrl"));
        loginPage.clickManagerLoginButton();
        Assertions.assertTrue(bankManagerPage.getAddCustomerButton().isDisplayed());
        Assertions.assertTrue(bankManagerPage.getOpenAccountButton().isDisplayed());
        Assertions.assertTrue(bankManagerPage.getCustomersButton().isDisplayed());
    }

    @Feature("Добавление клиента")
    @Description("Тестирование добавления клиента в личном кабинете менеджера")
    @Severity(value = SeverityLevel.CRITICAL)
    @Test
    @Issue("XYZ-UI-manager-add-customer")
    @DisplayName("T-007")
    public void testAddNewCustomer() {
        authManagerAccount(driver, loginPage);
        bankManagerPage.clickAddCustomerButton().fillNewCustomerForm("testName", "testLastname", "123");
        Alert alert = bankManagerPage.waitAlert();
        Assertions.assertTrue(alert.getText().contains("Customer added successfully with customer id :"));
        alert.accept();

        bankManagerPage.clickCustomersButton();
        var customers = bankManagerPage.getCustomers();
        writeCustomersToCSV(customers);

        Assertions.assertTrue(customers.stream()
                .anyMatch(customer -> "testName".equals(customer.firstName)));

        Assertions.assertTrue(customers.stream()
                .anyMatch(customer -> "testLastname".equals(customer.lastName)));

        Assertions.assertTrue(customers.stream()
                .anyMatch(customer -> "123".equals(customer.postCode)));

    }

}
