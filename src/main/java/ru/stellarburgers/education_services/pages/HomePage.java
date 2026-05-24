package ru.stellarburgers.education_services.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private By accountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private By loginMainButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By constructorTitle = By.xpath(".//h1[text()='Соберите бургер']");
    private By bunsTab = By.xpath(".//span[text()='Булки']/parent::div");
    private By saucesTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private By fillingsTab = By.xpath(".//span[text()='Начинки']/parent::div");
    private By bunsHeader = By.xpath(".//h2[text()='Булки']");
    private By saucesHeader = By.xpath(".//h2[text()='Соусы']");
    private By fillingsHeader = By.xpath(".//h2[text()='Начинки']");
    private By orderButton = By.xpath(".//button[text()='Оформить заказ']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку 'Личный Кабинет' в шапке")
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    @Step("Нажать кнопку 'Войти в аккаунт' в центре страницы")
    public void clickLoginMainButton() {
        driver.findElement(loginMainButton).click();
    }

    @Step("Нажать на раздел 'Булки'")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Нажать на раздел 'Соусы'")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Нажать на раздел 'Начинки'")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Дождаться загрузки главной страницы")
    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(constructorTitle));
    }

    @Step("Дождаться отображения раздела 'Булки'")
    public void waitForBunsSection() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsHeader));
    }

    @Step("Дождаться отображения раздела 'Соусы'")
    public void waitForSaucesSection() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(saucesHeader));
    }

    @Step("Дождаться отображения раздела 'Начинки'")
    public void waitForFillingsSection() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsHeader));
    }

    @Step("Проверить, что раздел 'Булки' активен")
    public boolean isBunsTabActive() {
        return driver.findElement(bunsTab).getAttribute("class").contains("current");
    }

    @Step("Проверить, что раздел 'Соусы' активен")
    public boolean isSaucesTabActive() {
        return driver.findElement(saucesTab).getAttribute("class").contains("current");
    }

    @Step("Проверить, что раздел 'Начинки' активен")
    public boolean isFillingsTabActive() {
        return driver.findElement(fillingsTab).getAttribute("class").contains("current");
    }

    @Step("Проверить, что пользователь вошёл (видна кнопка 'Оформить заказ')")
    public boolean isUserLoggedIn() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton))
                .isDisplayed();
    }
}
