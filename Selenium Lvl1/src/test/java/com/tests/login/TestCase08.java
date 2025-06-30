package com.tests.login;

import com.railway.dataobject.Account;
import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.LoginPage;
import com.railway.pages.RegisterPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import com.tests.utilities.ReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase08 extends TestBase {
    private static final Logger logger = LogManager.getLogger(TestCase08.class);

    @Test
    public void UserCannotLoginWithUnactivatedAccount() {
        BasePage basePage = new BasePage();
        RegisterPage registerPage = new RegisterPage();
        LoginPage loginPage = new LoginPage();
        Helpers helpers = new Helpers();

        logger.info("=== TestCase08: UserCannotLoginWithUnactivatedAccount ===");
        ReportManager.info("=== TestCase08: UserCannotLoginWithUnactivatedAccount ===");

        // 1. Navigate to QA Railway Website
        logger.info("1. Navigate to QA Railway Website");
        ReportManager.info("1. Navigate to QA Railway Website");

        // 2. Click on "Login" tab
        logger.info("2. Click on 'Login' tab");
        ReportManager.info("2. Click on 'Login' tab");
        basePage.clickTab("Login");

        // 3. Enter username and password of account hasn't been activated.
        logger.info("3. Enter username and password of account hasn't been activated.");
        ReportManager.info("3. Enter username and password of account hasn't been activated.");

        // 4. Click on "Login" button
        logger.info("4. Click on 'Login' button");
        ReportManager.info("4. Click on 'Login' button");
        helpers.scrollToElement(By.xpath("//input[@value='login']"));
        loginPage.login(Account.UNACTIVED_ACCOUNT);

        //Verify error message
        logger.info("Verify error message");
        ReportManager.info("Verify error message");

        String actualMessage = loginPage.getInvalidErrorMessageText();
        String expectedMessage = "Invalid username or password. Please try again.";

        try {
            Assert.assertEquals(actualMessage.trim(), expectedMessage, "Error message does not match");
            ReportManager.pass("Error message matched: " + actualMessage);
        } catch (AssertionError e) {
            ReportManager.fail("Expected: '" + expectedMessage + "' but got: '" + actualMessage + "'");
            throw e;
        } finally {
            DriverManager.quitDriver();
        }
    }
}
