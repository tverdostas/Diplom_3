package site.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {

    WebDriver driver;
    //Кнопка "Войти" под формой восстановления пароля
    private final By btnLoginUnderResetting = By.className("Auth_link__1fOlj");

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть на кнопку Логин")
    public void clickLoginButtonUnderResetting() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(btnLoginUnderResetting));
        driver.findElement(btnLoginUnderResetting).click();
    }


}
