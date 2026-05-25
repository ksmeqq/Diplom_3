package ru.stellar.burgers.education.services.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.stellar.burgers.education.services.base.BaseTest;
import ru.stellar.burgers.education.services.pages.HomePage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("При клике на 'Булки' активируется соответствующий раздел конструктора")
    public void clickBunsTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoad();
        homePage.clickSaucesTab();
        homePage.clickBunsTab();
        assertTrue("Раздел 'Булки' должен быть активен", homePage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("При клике на 'Соусы' активируется соответствующий раздел конструктора")
    public void clickSaucesTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoad();
        homePage.clickSaucesTab();
        assertTrue("Раздел 'Соусы' должен быть активен", homePage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("При клике на 'Начинки' активируется соответствующий раздел конструктора")
    public void clickFillingsTabTest() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoad();
        homePage.clickFillingsTab();
        assertTrue("Раздел 'Начинки' должен быть активен", homePage.isFillingsTabActive());
    }
}