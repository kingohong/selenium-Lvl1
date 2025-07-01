package com.tests.register;

import com.railway.dataobject.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.RegisterPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import com.tests.utilities.ReportManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestCase11 extends TestBase {
    private static final Logger logger = LogManager.getLogger(TestCase11.class);

    @Test
    public void UserCannotCreateAccountWhilePasswordAndPIDFieldsAreEmpty() {
        BasePage basePage = new BasePage();
        RegisterPage registerPage = new RegisterPage();
        Helpers helpers = new Helpers();

        logger.info("=== TestCase11: UserCannotCreateAccountWhilePasswordAndPIDFieldsAreEmpty ===");
        ReportManager.info("=== TestCase11: UserCannotCreateAccountWhilePasswordAndPIDFieldsAreEmpty ===");

        // 1. Navigate to QA Railway Website
        logger.info("1. Navigate to QA Railway Website");
        ReportManager.info("1. Navigate to QA Railway Website");

        // 2. Click on "Register" tab
        logger.info("2. Click on 'Register' tab");
        ReportManager.info("2. Click on 'Register' tab");
        basePage.clickTab("Register");

        // 3. Enter valid email, leave password and PID empty
        logger.info("3. Enter valid email, leave password and PID empty");
        ReportManager.info("3. Enter valid email, leave password and PID empty");

        // 4. Click on "Register" button
        logger.info("4. Click on 'Register' button");
        ReportManager.info("4. Click on 'Register' button");

        String timestamp = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
        String dynamicEmail = "testuser" + timestamp + "@gmail.com";
        Account.BLANK_EXCEPT_EMAIL.setUsername(dynamicEmail);

        helpers.scrollToElement(By.xpath("//input[@value='Register']"));
        registerPage.register(Account.BLANK_EXCEPT_EMAIL);

        // Verify general error message
        logger.info("Verify general error message");
        ReportManager.info("Verify general error message");

        String actualHeaderError = registerPage.getErrorMessageText();
        String expectedHeaderError = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualHeaderError.trim(), expectedHeaderError, "General error message does not match");
        ReportManager.pass("General error message matched: " + actualHeaderError);

        // Verify field-level error messages
        logger.info("Verify field-level error messages");
        ReportManager.info("Verify field-level error messages");

        List<String> fieldErrors = registerPage.getAllFieldErrorMessages();

        Assert.assertTrue(fieldErrors.contains("Invalid password length."), "Missing: Invalid password length.");
        ReportManager.pass("Field error found: Invalid password length.");

        Assert.assertTrue(fieldErrors.contains("Invalid ID length."), "Missing: Invalid ID length.");
        ReportManager.pass("Field error found: Invalid ID length.");

        DriverManager.quitDriver();
    }
}
