package ru.stellar.burgers.education.services.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.stellar.burgers.education.services.base.BaseTest;
import ru.stellar.burgers.education.services.pages.HomePage;
import ru.stellar.burgers.education.services.pages.LoginPage;
import ru.stellar.burgers.education.services.pages.RegisterPage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Регистрация с валидным паролем (>=6 символов) переводит на страницу входа")
    public void successfulRegistrationTest() {
        String email = "test-" + System.currentTimeMillis() + "@yandex.ru";
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
        loginPage.login(email, password);
        Assert.assertTrue("Кнопка 'Оформить заказ' должна быть видна", homePage.isUserLoggedIn());
    }

    @Test
    @DisplayName("Ошибка при пароле менее 6 символов")
    @Description("Регистрация с паролем 5 символов показывает сообщение 'Некорректный пароль'")
    public void incorrectPasswordRegistrationTest() {
        String email = "test-" + System.currentTimeMillis() + "@yandex.ru";

        HomePage homePage = new HomePage(driver);
        homePage.waitForLoad();
        homePage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Имя", email, "12345");

        assertThat(registerPage.getPasswordErrorText(), equalTo("Некорректный пароль"));
    }
}