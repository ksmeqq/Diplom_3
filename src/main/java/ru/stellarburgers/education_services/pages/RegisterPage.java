package ru.stellarburgers.education_services.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;

    private By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private By passwordField = By.xpath(".//input[@type='password']");
    private By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private By loginLink = By.xpath(".//a[text()='Войти']");
    private By passwordError = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести имя: {name}")
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Зарегистрировать пользователя: {name}, {email}")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Получить текст ошибки пароля")
    public String getPasswordErrorText() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordError))
                .getText();
    }
}
