package com.railway.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static WebDriver _driver;

    public static void createDriver() {
        if(_driver == null) {
            ChromeOptions options = new ChromeOptions();
            _driver = new ChromeDriver(options);
            _driver.manage().window().maximize();
        }
    }

    public static void quitDriver() {
        if (_driver != null) {
            _driver.quit();
            _driver = null;
        }
    }

    public static WebDriver getDriver() {
        return _driver;
    }
}
