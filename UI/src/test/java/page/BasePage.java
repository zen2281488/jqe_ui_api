package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BasePage {
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
    protected void clickElement(WebElement element){
        wait.until(visibilityOf(element));
        element.click();
    }

    protected void fillElement(WebElement element,Integer num){
        wait.until(visibilityOf(element));
        element.clear();
        element.sendKeys(Integer.toString(num));
    }


}
