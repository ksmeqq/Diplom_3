package ru.stellarburgers.education_services.base;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public abstract class BaseTest {
    protected WebDriver driver;
    protected static final String URL = "https://stellarburgers.education-services.ru/";

    @Parameterized.Parameter
    public String browser;

    @Parameterized.Parameters(name = "browser={0}")
    public static Object[][] data() {
        return new Object[][] {
                {"chrome"},
                {"yandex"},
        };
    }

    @Before
    public void setup() {
        driver = WebDriverFactory.create(browser);
        driver.get(URL);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
