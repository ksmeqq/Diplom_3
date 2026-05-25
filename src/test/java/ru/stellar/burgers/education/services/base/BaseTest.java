package ru.stellar.burgers.education.services.base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
    protected WebDriver driver;
    protected static final String URL = "https://stellarburgers.education-services.ru/";

    @Before
    public void setup() {
        driver = WebDriverFactory.create();
        driver.get(URL);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
