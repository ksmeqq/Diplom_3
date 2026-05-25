package ru.stellar.burgers.education.services.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver driver;

    private By loginLink = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}