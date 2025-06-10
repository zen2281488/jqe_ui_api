package Util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.Semaphore;

import static Util.ConfProperties.getBoolProperty;

public class BrowserInit {
    private static final int poolSize = Integer.parseInt(
            System.getenv().getOrDefault("TEST_PARALLELISM", "4")
    );    private static final Semaphore semaphore = new Semaphore(poolSize);
    private static final ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();

    public static WebDriver getWebdriver() {
        if (webdriver.get() == null) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException("Could not acquire semaphore", e);
            }

            ChromeOptions chromeOptions = new ChromeOptions()
                    .addArguments("--no-sandbox", "--disable-dev-shm-usage", "window-size=1220,880");

            if (getBoolProperty("headlessMode")) {
                chromeOptions.addArguments("--headless=new");
            }

            webdriver.set(new ChromeDriver(chromeOptions));
        }
        return webdriver.get();
    }

    public static synchronized void closeWebdriver() {
        WebDriver driver = webdriver.get();
        if (driver != null) {
            driver.quit();
            webdriver.remove();
            semaphore.release();
        }
    }
}

