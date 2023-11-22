package site.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    WebDriver driver;

    // Вкладка "Профиль"
    private final By btnProfileTab = By.xpath(".//a[text()='Профиль']");
    //Вкладка "Выход"
    private final By btnExitTab = By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кнопка Профиля доступна")
    public boolean btnProfileTabIsEnabled() {
        return driver.findElement(btnProfileTab).isEnabled();
    }
    @Step("Кликнуть по кнопке Выход")
    public void clickExitButton() {
        driver.findElement(btnExitTab).click();
    }

}
