package ru.stellarburgers.education_services.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.stellarburgers.education_services.base.BaseTest;
import ru.stellarburgers.education_services.pages.ForgotPasswordPage;
import ru.stellarburgers.education_services.pages.HomePage;
import ru.stellarburgers.education_services.pages.LoginPage;
import ru.stellarburgers.education_services.pages.RegisterPage;

public class LoginTest extends BaseTest {

    private String email;
    private final String password = "password";
    private final String name = "Тестовый";

    @Before
    public void registerUser() {
        email = "test-" + System.currentTimeMillis() + "@yandex.ru";

        new HomePage(driver).clickAccountButton();
        new LoginPage(driver).clickRegisterLink();
        new RegisterPage(driver).register(name, email, password);
        new LoginPage(driver).waitForLoad();
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
        Assert.assertTrue("Кнопка 'Оформить заказ' должна быть видна",
                new HomePage(driver).isUserLoggedIn());
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
}