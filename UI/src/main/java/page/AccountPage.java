package page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class AccountPage extends BasePage<AccountPage> {

    public AccountPage(WebDriver browser) {
        super(browser);
    }

    @FindBy(css = "[ng-click^=deposit]")
    private WebElement depositButton;
    @FindBy(css = "[ng-click^=withdrawl]")
    private WebElement withDrawlButton;
    @FindBy(css = "[ng-click^=transactions]")
    private WebElement transactionsButton;
    @FindBy(css = "[ng-submit^=deposit] input")
    private WebElement amountDepositInput;
    @FindBy(css = "[ng-submit^=withdrawl] input")
    private WebElement amountWithDrawInput;
    @FindBy(css = "[ng-submit^=withdrawl] button")
    private WebElement submitWithdrawButton;
    @FindBy(css = "[ng-submit^=deposit] button")
    private WebElement submitDepositButton;
    @FindBy(css = ".center strong:nth-child(2)")
    private WebElement balance;
    @FindBy(className = "error")
    private WebElement message;
    @FindBy(css = "#accountSelect")
    private WebElement accountSelector;
    @FindBy(css = "span.ng-binding")
    private WebElement accountName;
    @FindBy(css = ".center strong:nth-child(3)")
    private WebElement currency;

    @Step("Нажатие на кнопку 'Deposit'")
    public AccountPage clickDepositButton() {
        return clickElement(depositButton);
    }

    @Step("Нажатие на кнопку 'Withdrawl'")
    public AccountPage clickWithDrawlButton() {
        return clickElement(withDrawlButton);
    }

    @Step("Нажатие на кнопку 'Transactions'")
    public AccountPage clickTransactionsButton() {
        return clickElement(transactionsButton);
    }

    @Step("Нажатие на кнопку подтверждения 'Withdrawl'")
    public AccountPage clickSubmitWithdrawlButton() {
        return clickElement(submitWithdrawButton);
    }

    @Step("Нажатие на кнопку подтверждения 'Deposit'")
    public AccountPage clickSubmitDepositButton() {
        return clickElement(submitDepositButton);
    }

    @Step("Ввод суммы депозита: {num}")
    public AccountPage fillAmountDepositInput(int num) {
        return fillElement(amountDepositInput, num);
    }

    @Step("Ввод суммы депозита: {num}")
    public AccountPage fillAmountDepositInput(String num) {
        return fillElement(amountDepositInput, num);
    }

    @Step("Ввод суммы снятия: {num}")
    public AccountPage fillAmountWithDrawlInput(int num) {
        return fillElement(amountWithDrawInput, num);
    }

    @Step("Ввод суммы снятия: {num}")
    public AccountPage fillAmountWithDrawlInput(String num) {
        return fillElement(amountWithDrawInput, num);
    }

    @Step("Получение баланса")
    public String getBalance() {
        wait.until(visibilityOf(balance));
        return balance.getText();
    }

    @Step("Получение имени счёта из выпадающего списка")
    public String getAccountBillName() {
        wait.until(visibilityOf(accountSelector));
        return accountSelector.getText();
    }

    @Step("Выбор счёта: {accountName}")
    public String clickAccountSelector(String accountName) {
        wait.until(visibilityOf(accountSelector));
        new Select(accountSelector).selectByVisibleText(accountName);
        return accountName;
    }

    @Step("Получение имени счёта со страницы")
    public String getAccountNameFromPage() {
        wait.until(visibilityOf(accountName));
        return accountName.getText();
    }

    @Step("Проверка видимости кнопки подтверждения депозита")
    public AccountPage submitDepositButtonIsVisible() {
        wait.until(visibilityOf(submitDepositButton));
        return this;
    }

    @Step("Проверка видимости поля ввода суммы депозита")
    public AccountPage depositInputIsVisible() {
        wait.until(visibilityOf(amountDepositInput));
        return this;
    }

    @Step("Проверка видимости кнопки подтверждения снятия")
    public AccountPage submitWithdrawButtonIsVisible() {
        wait.until(visibilityOf(submitWithdrawButton));
        return this;
    }

    @Step("Проверка видимости поля ввода суммы снятия")
    public AccountPage withdrawInputIsVisible() {
        wait.until(visibilityOf(amountWithDrawInput));
        return this;
    }

    @Step("Получение валюты счёта")
    public String getCurrency() {
        wait.until(visibilityOf(currency));
        return currency.getText();
    }

    @Step("Внесение тестового депозита на сумму 100")
    public AccountPage sendTestDeposit() {
        return clickDepositButton()
                .fillAmountDepositInput("100")
                .clickSubmitDepositButton();
    }

}