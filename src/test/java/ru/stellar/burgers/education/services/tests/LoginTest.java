package ru.stellar.burgers.education.services.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.stellar.burgers.education.services.User;
import ru.stellar.burgers.education.services.api.RegisterApi;
import ru.stellar.burgers.education.services.base.BaseTest;
import ru.stellar.burgers.education.services.pages.ForgotPasswordPage;
import ru.stellar.burgers.education.services.pages.HomePage;
import ru.stellar.burgers.education.services.pages.LoginPage;
import ru.stellar.burgers.education.services.pages.RegisterPage;

public class LoginTest extends BaseTest {

    private User user;
    private String email;
    private String password = "password";
    private String name = "name";
    private String accessToken;
    private RegisterApi registerApi;

    @Before
    public void setUp() {
        registerApi = new RegisterApi();
        email = "test-" + System.currentTimeMillis() + "@yandex.ru";
        user = new User(email, password, name);
        Response registerResponse = registerApi.sendPostRequestToCreateUser(user);
        accessToken = registerApi.getAccessToken(registerResponse);
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    @Description("Авторизация пользователя через кнопку 'Войти в аккаунт' в центре главной страницы")
    public void loginViaMainPageButtonTest() {
        driver.get(URL);
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoad();
        homePage.clickLoginMainButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        Assert.assertTrue("Кнопка 'Оформить заказ' должна быть видна", homePage.isUserLoggedIn());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный Кабинет'")
    @Description("Авторизация пользователя через кнопку 'Личный Кабинет' в шапке")
    public void loginViaAccountButtonTest() {
        driver.get(URL);
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoad();
        homePage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        Assert.assertTrue("Кнопка 'Оформить заказ' должна быть видна", homePage.isUserLoggedIn());
    }

    @Test
    @DisplayName("Вход через ссылку 'Войти' в форме регистрации")
    @Description("Авторизация через переход с формы регистрации по ссылке 'Войти'")
    public void loginViaRegisterFormTest() {
        driver.get(URL);
        new HomePage(driver).clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        loginPage.clickRegisterLink();
        new RegisterPage(driver).clickLoginLink();

        loginPage.login(email, password);
        Assert.assertTrue("Кнопка 'Оформить заказ' должна быть видна", new HomePage(driver).isUserLoggedIn());
    }

    @Test
    @DisplayName("Вход через ссылку 'Войти' в форме восстановления пароля")
    @Description("Авторизация через переход с формы восстановления пароля по ссылке 'Войти'")
    public void loginViaForgotPasswordTest() {
        driver.get(URL);
        new HomePage(driver).clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        loginPage.clickRestoreLink();
        new ForgotPasswordPage(driver).clickLoginLink();

        loginPage.login(email, password);
        Assert.assertTrue("Кнопка 'Оформить заказ' должна быть видна", new HomePage(driver).isUserLoggedIn());
    }

    @After
    public void tearDown() {
        registerApi.deleteUser(accessToken);
    }
}