package page;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BasePage<T extends BasePage<T>> {
    protected WebDriver browser;
    protected WebDriverWait wait;
    protected Actions action;
    protected JavascriptExecutor js;

    public BasePage(WebDriver browser) {
        this.browser = browser;
        this.wait = new WebDriverWait(browser, Duration.ofSeconds(15));
        this.action = new Actions(browser);
        this.js = (JavascriptExecutor) browser;
        PageFactory.initElements(browser, this);
    }
    protected T clickElement(WebElement element){
        wait.until(visibilityOf(element));
        element.click();
        return (T) this;
    }

    protected T fillElement(WebElement element,Integer num){
        wait.until(visibilityOf(element));
        element.clear();
        element.sendKeys(Integer.toString(num));
        return (T) this;
    }

    @Step("Ожидание элемента")
    public T waitElement(WebElement element) {
        wait.until(visibilityOf(element));
        return (T) this;
    }
}
