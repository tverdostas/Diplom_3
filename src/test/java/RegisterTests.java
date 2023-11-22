import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.stellarburgers.config.AppConfig;
import site.stellarburgers.config.WebDriverConfig;
import site.stellarburgers.pages.LoginPage;
import site.stellarburgers.pages.ProfilePage;
import site.stellarburgers.pages.RegisterPage;
import site.stellarburgers.user.User;
import site.stellarburgers.user.UserOperations;
import site.stellarburgers.utils.Generator;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;

public class RegisterTests {
    private WebDriver driver;
    User user;

    @Before
    public void setup() {
        user = Generator.generateUser();

        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WebDriverConfig.WAIT_SEC_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(AppConfig.REGISTER_PAGE_URL);
    }

    @Test
    @DisplayName("Получение ошибки при регистрации юзера с коротким паролем")
    public void registerNewUserWithShortPasswordGetError() {
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(Generator.generateWrongUserPassword());
        registerPage.clickRegisterButton();

        MatcherAssert.assertThat(registerPage.getInvalidPasswordText(), equalTo("Некорректный пароль"));
    }

    @Test
    @DisplayName("Успешная регистрация юзера с валидными данными")
    public void registerNewUserGetSuccess() {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(user.getPassword());
        registerPage.clickRegisterButton();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
        loginPage.loginUser(user);
        Assert.assertTrue(profilePage.btnProfileTabIsEnabled());

    }

    @After
    public void teardown() {
        UserOperations.deleteUser(UserOperations.getAccessToken(user));
        driver.quit();
    }
}
