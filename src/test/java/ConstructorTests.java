import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.stellarburgers.config.AppConfig;
import site.stellarburgers.config.WebDriverConfig;
import site.stellarburgers.pages.MainPage;

import java.util.concurrent.TimeUnit;

public class ConstructorTests {
    private WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WebDriverConfig.WAIT_SEC_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(AppConfig.MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Перейти на таб с булками")
    public void SwitchToBunsTabGetSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsButton();
        mainPage.clickBunsButton();
        Assert.assertTrue(mainPage.btnBunsIsEnabled());
    }

    @Test
    @DisplayName("Перейти на таб с соусами")
    public void SwitchToSaucesTabGetSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesButton();
        Assert.assertTrue(mainPage.btnSaucesIsEnabled());
    }

    @Test
    @DisplayName("Перейти на таб с начинками")
    public void SwitchToFillingsTabGetSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsButton();
        Assert.assertTrue(mainPage.btnFillingsIsEnabled());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
