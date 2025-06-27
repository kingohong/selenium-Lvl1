package com.tests.utilities;

import com.railway.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[FAIL] Test case: " + result.getName());
        takeScreenshot(result.getName());
    }

    private void takeScreenshot(String testName) {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            System.out.println("WebDriver is null. Cannot take screenshot.");
            return;
        }

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = "screenshots/" + testName + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(screenshot, new File(filename));
            System.out.println("Screenshot saved: " + filename);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[START] Test case: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[PASS] Test case: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[SKIPPED] Test case: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== TEST SUITE STARTED ===");
        new File("screenshots").mkdirs(); // Ensure folder exists
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== TEST SUITE FINISHED ===");
    }
}
