package site.stellarburgers.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverConfig {
    protected static WebDriver driver;
    public static final long WAIT_SEC_TIMEOUT = 10;

    public static WebDriver setDriver() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            return createChromeDriver();
        }
        switch (browser) {
            case "yandex":
                return createYandexDriver();
            case "chrome":
            default:
                return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        return driver;
    }

    private static WebDriver createYandexDriver() {
        System.setProperty("webdriver.yandex.driver", "src/main/resources/yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        return driver;
    }
}
