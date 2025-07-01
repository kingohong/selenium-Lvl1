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

public class TestCase10 extends TestBase {
    private static final Logger logger = LogManager.getLogger(TestCase10.class);

    @Test
    public void UserCannotCreateAccountWithConfirmPasswordIsNotTheSameWithPassword() {
        BasePage basePage = new BasePage();
        RegisterPage registerPage = new RegisterPage();
        Helpers helpers = new Helpers();

        logger.info("=== TestCase10: UserCannotCreateAccountWithConfirmPasswordIsNotTheSameWithPassword ===");
        ReportManager.info("=== TestCase10: UserCannotCreateAccountWithConfirmPasswordIsNotTheSameWithPassword ===");

        // 1. Navigate to QA Railway Website
        logger.info("1. Navigate to QA Railway Website");
        ReportManager.info("1. Navigate to QA Railway Website");

        // 2. Click on "Register" tab
        logger.info("2. Click on 'Register' tab");
        ReportManager.info("2. Click on 'Register' tab");
        basePage.clickTab("Register");

        // 3. Enter valid information into all fields except Confirm Password ≠ Password
        logger.info("3. Enter valid information into all fields except 'Confirm password' is not the same with 'Password'");
        ReportManager.info("3. Enter valid information into all fields except 'Confirm password' is not the same with 'Password'");

        // 4. Click on "Register" button
        logger.info("4. Click on 'Register' button");
        ReportManager.info("4. Click on 'Register' button");

        String timestamp = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
        String dynamicEmail = "testuser" + timestamp + "@gmail.com";
        Account.DIFFERENT_PASSWORD.setUsername(dynamicEmail);

        helpers.scrollToElement(By.xpath("//input[@value='Register']"));
        registerPage.register(Account.DIFFERENT_PASSWORD);

        //Verify error message
        logger.info("Verify error message");
        ReportManager.info("Verify error message");

        String actualMessage = registerPage.getErrorMessageText();
        String expectedMessage = "There're errors in the form. Please correct the errors and try again.";

        try {
            Assert.assertEquals(actualMessage.trim(), expectedMessage, "Message does not match");
            ReportManager.pass("Error message matched: " + actualMessage);
        } catch (AssertionError e) {
            ReportManager.fail("Expected: '" + expectedMessage + "' but got: '" + actualMessage + "'");
            throw e;
        } finally {
            DriverManager.quitDriver();
        }
    }
}
