package util;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverExtension implements ParameterResolver, AfterEachCallback {

    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(WebDriverExtension.class);

    @Override
    public boolean supportsParameter(ParameterContext pc, ExtensionContext ec) {
        return pc.getParameter().getType() == WebDriver.class;
    }

    @Override
    public Object resolveParameter(ParameterContext pc, ExtensionContext ec) {
        ExtensionContext.Store store = ec.getStore(NAMESPACE);
        return store.getOrComputeIfAbsent("driver", key -> {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            return new ChromeDriver(options);
        }, WebDriver.class);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        WebDriver driver = context.getStore(NAMESPACE).remove("driver", WebDriver.class);
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getStoredDriver(ExtensionContext context) {
        return context.getStore(NAMESPACE).get("driver", WebDriver.class);
    }
}
