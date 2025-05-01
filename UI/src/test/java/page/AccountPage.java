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

    @Step("���� �� ������ 'Deposit'")
    public AccountPage clickDepositButton() {
        return clickElement(depositButton);
    }

    @Step("���� �� ������ 'WithDrawl'")
    public AccountPage clickWithDrawlButton() {
        return clickElement(withDrawlButton);
    }

    @Step("���� �� ������ 'Transactions'")
    public AccountPage clickTransactionsButton() {
        return clickElement(transactionsButton);
    }

    @Step("���� �� ������ �������� ���������� 'Withdrawl'")
    public AccountPage clickSubmitWithdrawlButton() {
        return clickElement(submitWithdrawButton);
    }

    @Step("���� �� ������ �������� ���������� 'Deposit'")
    public AccountPage clickSubmitDepositButton() {
        return clickElement(submitDepositButton);
    }

    @Step("���������� ���� Deposit ������: {num} ")
    public AccountPage fillAmountDepositInput(int num) {
        return fillElement(amountDepositInput,num);
    }

    @Step("���������� ���� WithDrawl ������: {num} ")
    public AccountPage fillAmountWithDrawlInput(int num) {
        return fillElement(amountWithDrawInput,num);
    }

    @Step("��������� �������")
    public String getBalance() {
        return balance.getText();
    }

    @Step("��������� �������� ���������� ������")
    public String getAccountBillName() {
        wait.until(visibilityOf(accountSelector));
        return accountSelector.getText();
    }

    public String clickAccountSelector(String accountName) {
        wait.until(visibilityOf(accountSelector));
        new Select(accountSelector).selectByVisibleText(accountName);
        return accountName;
    }

    @Step("��������� �������� ��������")
    public String getAccountNameFromPage() {
        wait.until(visibilityOf(accountName));
        return accountName.getText();
    }


}
