package Util;


import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static Util.ConfProperties.getBoolProperty;
import static Util.ConfProperties.getProperty;

public class BrowserInit {
    private static final ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();

    public static WebDriver getWebdriver() {
        if (webdriver.get() == null) {
                    switch (getProperty("browserName")) {
                        case "chrome":
                            ChromeOptions chromeOptions = new ChromeOptions()
                                    .addArguments("--no-sandbox", "--disable-dev-shm-usage", "window-size=1220,880");
                            if (getBoolProperty("headlessMode")) {
                                chromeOptions.addArguments("--headless=new");
                            }
                            webdriver.set(new ChromeDriver(chromeOptions));
                            break;
                        default:
                            throw new RuntimeException("123");
                    }
            }
        return webdriver.get();
    }


    public static synchronized void closeWebdriver() {
        if (webdriver.get() != null) {
            webdriver.get().quit();
            webdriver.remove();
        }
    }

}
