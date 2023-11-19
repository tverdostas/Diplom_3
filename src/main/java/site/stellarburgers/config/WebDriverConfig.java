package site.stellarburgers.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverConfig {
    public static final long WAIT_SEC_TIMEOUT = 10;

    public static WebDriver setDriver() {
        // Чтобы запустить тесты в Яндекс  - 1) закомменть запуск в Chrome 2) Раскомменть запуск в Yandex

/*        //Запуск тестов в Chrome Browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;*/

        //Запуск тестов в Yandex Browser
        System.setProperty("webdriver.yandex.driver", "src/main/resources/yandexdriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

}
