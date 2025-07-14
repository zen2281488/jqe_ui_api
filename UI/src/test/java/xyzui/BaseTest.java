package xyzui;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import util.AllureScreenshotExtension;

import static util.BrowserInit.closeWebdriver;
import static util.BrowserInit.getWebdriver;
@ExtendWith(AllureScreenshotExtension.class)
public class BaseTest {

    protected WebDriver driver;
    public boolean testFailed = false;


    @BeforeEach
    @Step("Инициализация вебдрайвера")
    public void baseBefore() {
        driver = getWebdriver();
    }

    @AfterEach
    @Step("Завершение работы вебдрайвера")
    public void baseAfter() {
        closeWebdriver();
    }
}
