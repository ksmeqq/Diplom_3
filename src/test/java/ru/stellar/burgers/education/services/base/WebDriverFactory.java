package ru.stellar.burgers.education.services.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class WebDriverFactory {

    private static final Properties config = loadConfig();

    private static Properties loadConfig() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Не найден config.properties в корне проекта. " + "Скопируй config.properties.example и заполни свои пути.", e);
        }
        return props;
    }

    public static WebDriver create() {
        WebDriver driver;
        if (config.getProperty("browser").equals("yandex")) {
            System.setProperty("webdriver.chrome.driver", config.getProperty("yandex.driver.path"));
            ChromeOptions options = new ChromeOptions();
            options.setBinary(config.getProperty("yandex.binary.path"));
            driver = new ChromeDriver(options);
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
    }
}