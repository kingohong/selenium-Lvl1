package com.tests.login;

import com.tests.utilities.ReportManager;
import com.railway.dataobject.Account;
import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase01 extends TestBase {

    private static final Logger logger = LogManager.getLogger(TestCase01.class);

    @Test
    public void UserCanLogIntoRailwayWithValidUsernameAndPassword() {
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        HomePage homePage = new HomePage();
        Helpers helpers = new Helpers();
        logger.info("=== TestCase01: UserCanLogIntoRailwayWithValidUsernameAndPassword ===");
        ReportManager.info("=== TestCase01: UserCanLogIntoRailwayWithValidUsernameAndPassword ===");

        //1. Navigate to QA Railway Website
        //2. Click on "Login" tab
        logger.info("1. Navigate to QA Railway Website");
        logger.info("2. Click on 'Login' tab");
        ReportManager.info("1. Navigate to QA Railway Website");
        ReportManager.info("2. Click on 'Login' tab");

        basePage.clickTab("Login");

        //3. Enter valid Email and Password
        //4. Click on "Login" button
        logger.info("3. Enter valid Email and Password");
        logger.info("4. Click on 'Login' button");
        ReportManager.info("3. Enter valid Email and Password");
        ReportManager.info("4. Click on 'Login' button");

        helpers.scrollToElement(By.xpath("//input[@value='login']"));

        loginPage.loginSuccess();

        String actualMessage = homePage.getWelcomeMessageByEmail("kingohong@gmail.com").getText();
        String expectedMessage = "Welcome kingohong@gmail.com";

        try {
            Assert.assertEquals(actualMessage.trim(), expectedMessage, "Message does not match");
            logger.info("Assertion passed: Login successful.");
            ReportManager.pass("Assertion passed: Login successful.");
        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            ReportManager.fail("Assertion failed: " + e.getMessage());
            throw e;
        }

        DriverManager.quitDriver();
    }
}
