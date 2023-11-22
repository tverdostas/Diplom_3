import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.stellarburgers.config.AppConfig;
import site.stellarburgers.config.BaseURI;
import site.stellarburgers.config.WebDriverConfig;
import site.stellarburgers.pages.LoginPage;
import site.stellarburgers.pages.ProfilePage;
import site.stellarburgers.user.User;
import site.stellarburgers.user.UserOperations;
import site.stellarburgers.utils.Generator;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;

public class LogoutTests {
    private WebDriver driver;
    User user;

    @Before
    public void setup() {
        RestAssured.baseURI = BaseURI.BASE_URI;
        user = Generator.generateUser();
        UserOperations.createUser(user);

        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WebDriverConfig.WAIT_SEC_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(AppConfig.MAIN_PAGE_URL);

    }

    @Test
    @DisplayName("Успешный разлогин с возвращением на страницу логина")
    public void logoutGetSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.clickProfileButton();
        loginPage.loginUser(user);
        profilePage.clickExitButton();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
    }

    @After
    public void teardown() {
        UserOperations.deleteUser(UserOperations.getAccessToken(user));
        driver.quit();
    }
}
