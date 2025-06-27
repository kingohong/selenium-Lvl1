package com.railway.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static final ThreadLocal<WebDriver> _driver = new ThreadLocal<>();

    public static void createDriver() {
        if(_driver.get() == null) {
            WebDriver webDriver = new ChromeDriver();
            _driver.set(webDriver);
            _driver.get().manage().window().maximize();
        }
    }

    public static void quitDriver() {
        WebDriver driver = _driver.get();
        if (driver != null) {
            driver.quit();
            _driver.remove();
        }
    }

    public static WebDriver getDriver() {
        return _driver.get();
    }
}
