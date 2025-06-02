package page;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pojo.Customers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BankManagerPage extends BasePage<BankManagerPage> {

    public BankManagerPage(WebDriver browser) {
        super(browser);
    }

    @FindBy(css = "[ng-click^=addCust]")
    private WebElement addCustomerButton;

    @FindBy(css = "[ng-model^=fName]")
    private WebElement firstNameInput;

    @FindBy(css = "[ng-model^=lName]")
    private WebElement lastNameInput;

    @FindBy(css = "[ng-model^=postCd]")
    private WebElement postCodeInput;

    @FindBy(css = "[type^=submit]")
    private WebElement addCustomerSubmitButton;

    private final By tableRowsLocator = By.cssSelector("tr.ng-scope");

    @FindBy(css = "[ng-click^=openAccount]")
    private WebElement openAccountButton;

    @FindBy(css = "[ng-click^=showCust]")
    private WebElement customersButton;

    @Step("Получение списка клиентов")
    public List<Customers> getCustomers() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tableRowsLocator));
        return browser.findElements(tableRowsLocator).stream()
                .map(this::parseCustomersFromRow)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Customers parseCustomersFromRow(WebElement row) {
        List<WebElement> cells = row.findElements(By.tagName("td"));
        if (cells.size() == 5) {
            Customers customer = new Customers();
            customer.setPostCode(cells.get(2).getText());
            customer.setLastName(cells.get(1).getText());
            customer.setFirstName(cells.get(0).getText());
            customer.setAccountNumber(cells.get(3).getText());
            return customer;
        }
        return null;
    }

    @Step("Ожидание отображения кнопки 'Add Customer'")
    public WebElement getAddCustomerButton() {
        waitElement(addCustomerButton);
        return addCustomerButton;
    }

    @Step("Ожидание отображения кнопки 'Open Account'")
    public WebElement getOpenAccountButton() {
        waitElement(openAccountButton);
        return openAccountButton;
    }

    @Step("Ожидание отображения кнопки 'Customers'")
    public WebElement getCustomersButton() {
        waitElement(customersButton);
        return customersButton;
    }

    @Step("Клик по кнопке 'Add Customer'")
    public BankManagerPage clickAddCustomerButton() {
        return clickElement(addCustomerButton);
    }

    @Step("Клик по кнопке 'Customers'")
    public BankManagerPage clickCustomersButton() {
        return clickElement(customersButton);
    }

    @Step("Заполнение формы нового клиента: {firstName} {lastName}, индекс: {postCode}")
    public BankManagerPage fillNewCustomerForm(String firstName, String lastName, String postCode) {
        waitElement(firstNameInput);
        fillElement(firstNameInput, firstName);
        fillElement(lastNameInput, lastName);
        fillElement(postCodeInput, postCode);
        return clickElement(addCustomerSubmitButton);
    }

    @Step("Ожидание появления алерта")
    public Alert waitAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}
