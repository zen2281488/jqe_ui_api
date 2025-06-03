package xyzui;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import static Util.BrowserInit.closeWebdriver;
import static Util.BrowserInit.getWebdriver;

public class BaseTest {
    protected WebDriver driver;

    @AfterEach
    @Step("Завершение работы вебдрайвера")
    public void baseAfter() {
        closeWebdriver();
    }

    @BeforeEach
    @Step("Инициализация вебдрайвера")
    public void baseBefore() {
        driver = getWebdriver();
    }
}
