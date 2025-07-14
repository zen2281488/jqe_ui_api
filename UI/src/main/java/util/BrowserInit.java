package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import static util.ConfProperties.getBoolProperty;

public class BrowserInit {
    private static final int poolSize = Integer.parseInt(System.getenv().getOrDefault("TEST_PARALLELISM", "4"));
    private static final Semaphore semaphore = new Semaphore(poolSize);
    private static final ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();
    private static final ThreadLocal<ChromeDriverService> chromeService = new ThreadLocal<>();

    public static WebDriver getWebdriver() {
        if (webdriver.get() == null) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException("Could not acquire semaphore", e);
            }

            ChromeDriverService service = new ChromeDriverService.Builder().usingAnyFreePort().build();

            try {
                service.start();
            } catch (IOException e) {
                throw new RuntimeException("Не удалось запустить ChromeDriverService", e);
            }
            chromeService.set(service);

            ChromeOptions chromeOptions = new ChromeOptions().addArguments("--no-sandbox").addArguments("--disable-dev-shm-usage").addArguments("--disable-gpu").addArguments("--disable-software-rasterizer").addArguments("--disable-extensions").addArguments("--disable-popup-blocking").addArguments("--disable-background-networking").addArguments("--disable-renderer-backgrounding").addArguments("--remote-allow-origins=*").addArguments("window-size=1220,880");

            if (getBoolProperty("headlessMode")) {
                chromeOptions.addArguments("--headless=new");
            }

            webdriver.set(new ChromeDriver(service, chromeOptions));
        }
        return webdriver.get();
    }

    public static synchronized void closeWebdriver() {
        WebDriver driver = webdriver.get();
        ChromeDriverService service = chromeService.get();
        System.out.println("Закрываю драйвер для потока: " + Thread.currentThread().getName());
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Ошибка при закрытии драйвера: " + e.getMessage());
            } finally {
                webdriver.remove();
            }
        }

        if (service != null) {
            try {
                service.stop();
            } catch (Exception e) {
                System.err.println("Ошибка при остановке ChromeDriverService: " + e.getMessage());
            } finally {
                chromeService.remove();
            }
        }

        semaphore.release();
    }
}
