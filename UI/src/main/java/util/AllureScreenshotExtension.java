package util;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static util.BrowserInit.getWebdriver;

public class AllureScreenshotExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            WebDriver driver = getWebdriver();
            if (driver != null) {
                takeScreenshot(driver);
            }
        }
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] takeScreenshot(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.err.println("Ошибка при снятии скриншота: " + e.getMessage());
            return new byte[0];
        }
    }
}
