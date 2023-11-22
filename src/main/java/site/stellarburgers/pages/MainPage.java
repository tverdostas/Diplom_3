package site.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import site.stellarburgers.utils.BackToMainDetails;

public class MainPage {

    WebDriver driver;

    //Логотип
    private final By btnLogo = By.className("AppHeader_header__logo__2D0X2");
    //Раздел "Конструктор"
    private final By btnConstructor = By.xpath(".//p[text()='Конструктор']");
    //Заголовок "Соберите бургер"
    private final By headerCreateBurger = By.xpath(".//h1[text()='Соберите бургер']");
    //Кнопка "Войти в аккаунт"
    private final By btnLogin = By.xpath(".//button[text()='Войти в аккаунт']");
    //Кнопка "Булки"
    private final By btnBuns = By.xpath(".//span[text()='Булки']");
    //Кнопка Булки выбрана
    private final By btnBunsIsCurrent = By.xpath(".//div[(contains(span/text(),'Булки')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");
    //Кнопка "Соусы"
    private final By btnSauces = By.xpath(".//span[text()='Соусы']");
    //Кнопка "Соусы" выбрана
    private final By btnSaucesIsCurrent = By.xpath(".//div[(contains(span/text(),'Соусы')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");
    //Кнопка "Начинки"
    private final By btnFillings = By.xpath(".//span[text()='Начинки']");
    //Кнопка "Начинки" выбрана
    private final By btnFillingsIsCurrent = By.xpath(".//div[(contains(span/text(),'Начинки')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");
    //Кнопка "Личный кабинет"
    private final By btnProfile = By.xpath(".//p[text()='Личный Кабинет']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по Лого")
    public void clickLogoButton() {
        driver.findElement(btnLogo).click();
    }

    @Step("Клик по кнопке Конструктора")
    public void clickConstructorButton() {
        driver.findElement(btnConstructor).click();
    }

    @Step("Клик по кнопке Логин")
    public void clickLoginButton() {
        driver.findElement(btnLogin).click();
    }

    @Step("Клик по табе Булки")
    public void clickBunsButton() {
        driver.findElement(btnBuns).click();
    }

    @Step("Клик по табе Соусы")
    public void clickSaucesButton() {
        driver.findElement(btnSauces).click();
    }

    @Step("Клик по табе Начинки")
    public void clickFillingsButton() {
        driver.findElement(btnFillings).click();
    }

    @Step("Получение текста из хедера")
    public String getCreateBurgerTextFromHeader() {
        return driver.findElement(headerCreateBurger).getText();
    }

    @Step("Кнопка Булки доступна")
    public boolean btnBunsIsEnabled() {
        return driver.findElement(btnBunsIsCurrent).isEnabled();
    }

    @Step("Кнопка Соусы доступна")
    public boolean btnSaucesIsEnabled() {
        return driver.findElement(btnSaucesIsCurrent).isEnabled();
    }

    @Step("Кнопка Начинки доступна")
    public boolean btnFillingsIsEnabled() {
        return driver.findElement(btnFillingsIsCurrent).isEnabled();
    }

    @Step("Клик по кнопке Профиля")
    public void clickProfileButton() {
        driver.findElement(btnProfile).click();
    }

    @Step("Вернуться на главную страницу")
    public void backToMainPage(String button) {
        if (button.equals(BackToMainDetails.LOGO_BACK_TO_MAIN_PAGE)) {
            clickLogoButton();
        } else {
            clickConstructorButton();
        }
    }
}
