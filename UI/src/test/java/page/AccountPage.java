package page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class AccountPage extends BasePage {

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

    @Step("Клик по кнопке 'Deposit'")
    public AccountPage clickDepositButton() {
        wait.until(visibilityOf(depositButton));
        depositButton.click();
        return this;
    }

    @Step("Клик по кнопке 'WithDrawl'")
    public AccountPage clickWithDrawlButton() {
        wait.until(visibilityOf(withDrawlButton));
        withDrawlButton.click();
        return this;
    }

    @Step("Клик по кнопке 'Transactions'")
    public AccountPage clickTransactionsButton() {
        wait.until(visibilityOf(transactionsButton));
        transactionsButton.click();
        return this;
    }

    @Step("Клик по кнопке отправки транзакции 'Withdrawl'")
    public AccountPage clickSubmitWithdrawlButton() {
        wait.until(visibilityOf(submitWithdrawButton));
        submitWithdrawButton.click();
        wait.until(visibilityOf(message));
        return this;
    }

    @Step("Клик по кнопке отправки транзакции 'Deposit'")
    public AccountPage clickSubmitDepositButton() {
        wait.until(visibilityOf(submitDepositButton));
        submitDepositButton.click();
        wait.until(visibilityOf(message));
        return this;
    }

    @Step("Заполнение поля Deposit числом: {num} ")
    public AccountPage fillAmountDepositInput(int num) {
        wait.until(visibilityOf(amountDepositInput));
        amountDepositInput.clear();
        amountDepositInput.sendKeys(Integer.toString(num));
        return this;
    }

    @Step("Заполнение поля WithDrawl числом: {num} ")
    public AccountPage fillAmountWithDrawlInput(int num) {
        wait.until(visibilityOf(amountWithDrawInput));
        amountWithDrawInput.clear();
        amountWithDrawInput.sendKeys(Integer.toString(num));
        return this;
    }

    @Step("Получение баланса")
    public String getBalance() {
        return balance.getText();
    }

    @Step("Получение названия банковских счетов")
    public String getAccountBillName() {
        wait.until(visibilityOf(accountSelector));
        return accountSelector.getText();
    }

    public String clickAccountSelector(String accountName) {
        wait.until(visibilityOf(accountSelector));
        new Select(accountSelector).selectByVisibleText(accountName);
        return accountName;
    }

    @Step("Получение названия аккаунта")
    public String getAccountNameFromPage() {
        wait.until(visibilityOf(accountName));
        return accountName.getText();
    }
}
