package ru.stellarburgers.education_services.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import ru.stellarburgers.education_services.base.BaseTest;
import ru.stellarburgers.education_services.pages.HomePage;
import ru.stellarburgers.education_services.pages.LoginPage;
import ru.stellarburgers.education_services.pages.RegisterPage;

import static org.hamcrest.CoreMatchers.equalTo;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Регистрация с валидным паролем (>=6 символов) переводитна страницу входа")
    public void successfulRegistrationTest() {
        String email = "test-" + System.currentTimeMillis() +
                "@yandex.ru";
        String password = "password";
        String name = "Тестовый";

        HomePage homePage = new HomePage(driver);
        homePage.waitForLoad();
        homePage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(name, email, password);

        loginPage.waitForLoad();
    }

    @Test
    @DisplayName("Ошибка при пароле менее 6 символов")
    @Description("Регистрация с паролем 5 символов показывает сообщение 'Некорректный пароль'")
    public void incorrectPasswordRegistrationTest() {
        String email = "test-" + System.currentTimeMillis() +
                "@yandex.ru";

        HomePage homePage = new HomePage(driver);
        homePage.waitForLoad();
        homePage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Имя", email, "12345");

        MatcherAssert.assertThat(registerPage.getPasswordErrorText(), equalTo("Некорректный пароль"));
    }
}