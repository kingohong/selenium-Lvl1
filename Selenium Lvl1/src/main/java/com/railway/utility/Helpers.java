package com.railway.utility;

import com.railway.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Helpers {

    private final int DEFAULT_TIMEOUT = 10;

    public void scrollToElement(By locator) {
        WebElement element = DriverManager.getDriver().findElement(locator);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public WebElement waitForVisibility(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForPresence(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean waitForTextPresent(By locator, String expectedText, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
    }

    public boolean waitForInvisibility(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement waitForVisibility(By locator) {
        return waitForVisibility(locator, DEFAULT_TIMEOUT);
    }

    public WebElement waitForClickable(By locator) {
        return waitForClickable(locator, DEFAULT_TIMEOUT);
    }

    public WebElement waitForPresence(By locator) {
        return waitForPresence(locator, DEFAULT_TIMEOUT);
    }

    public boolean waitForTextPresent(By locator, String expectedText) {
        return waitForTextPresent(locator, expectedText, DEFAULT_TIMEOUT);
    }

    public boolean waitForInvisibility(By locator) {
        return waitForInvisibility(locator, DEFAULT_TIMEOUT);
    }

    public void waitForDropdownOptionsChanged(By locator, int oldCount) {
        try {
            new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                    .until(driver -> {
                        int newCount = new Select(driver.findElement(locator)).getOptions().size();
                        System.out.println("Old: " + oldCount + ", New: " + newCount);
                        return newCount != oldCount;
                    });
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for dropdown to change");
        }
    }
}
