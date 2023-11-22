import org.apache.commons.lang3.RandomStringUtils;
import site.stellarburgers.config.AppConfig;
import site.stellarburgers.config.BaseURI;
import site.stellarburgers.config.WebDriverConfig;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.stellarburgers.pages.LoginPage;
import site.stellarburgers.pages.MainPage;
import site.stellarburgers.pages.ProfilePage;
import site.stellarburgers.user.User;
import site.stellarburgers.user.UserOperations;
import site.stellarburgers.utils.Generator;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;

public class GoToProfileTests {

    private WebDriver driver;
    private final String password = RandomStringUtils.randomAlphabetic(10);
    private final String email = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";

    @Before
    public void setup() {
        RestAssured.baseURI = BaseURI.BASE_URI;
        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WebDriverConfig.WAIT_SEC_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(AppConfig.MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход в личный кабинет пользователем")
    public void goToProfileAuthUserGetProfile() {
        User user = Generator.generateUser();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        UserOperations.createUser(user);
        mainPage.clickProfileButton();

        loginPage.loginUser(user);

        Assert.assertTrue(profilePage.btnProfileTabIsEnabled());
    }

    @Test
    @DisplayName("Переход в личный кабинет неавторизованным пользователем")
    public void goToProfileUnauthUserGetLogin() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickProfileButton();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
    }

    @After
    public void tearDown() {
        String accessTokenUI = UserOperations.loginUser(new User(email, password)).then().extract().path("accessToken");
        if (accessTokenUI != null) {
            UserOperations.deleteUser(accessTokenUI);
        }
        driver.quit();
    }
}
