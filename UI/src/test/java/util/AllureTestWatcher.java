package util;


import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import xyzui.BaseTest;
import java.util.function.Supplier;

public class AllureTestWatcher implements TestWatcher {

    private final Supplier<WebDriver> driverSupplier;
    private final BaseTest testInstance;

    public AllureTestWatcher(Supplier<WebDriver> driverSupplier, BaseTest testInstance) {
        this.driverSupplier = driverSupplier;
        this.testInstance = testInstance;
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        testInstance.testFailed = true;
    }
}
