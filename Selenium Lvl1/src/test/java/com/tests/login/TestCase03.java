package com.tests.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.LoginPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import com.tests.utilities.ReportManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase03 extends TestBase {
    private static final Logger logger = LogManager.getLogger(TestCase03.class);

    @Test
    public void UserCannotLogIntoRailwayWithInvalidPassword(){
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        Helpers helpers = new Helpers();

        logger.info("=== TestCase03: UserCannotLogIntoRailwayWithInvalidPassword ===");
        ReportManager.info("=== TestCase03: UserCannotLogIntoRailwayWithInvalidPassword ===");

        //1. Navigate to QA Railway Website
        //2. Click on 'Login' tab
        logger.info("1. Navigate to QA Railway Website");
        logger.info("2. Click on 'Login' tab");
        ReportManager.info("1. Navigate to QA Railway Website");
        ReportManager.info("2. Click on 'Login' tab");
        basePage.clickTab("Login");

        //3. Enter valid Email and invalid Password
        //4. Click on 'Login' button
        logger.info("3. Enter valid Email and invalid Password");
        logger.info("4. Click on 'Login' button");
        ReportManager.info("3. Enter valid Email and invalid Password");
        ReportManager.info("4. Click on 'Login' button");
        helpers.scrollToElement(By.xpath("//input[@value='login']"));

        loginPage.loginError();

        String actualMessage = loginPage.getInvalidErrorMessageText();
        String expectedMessage = "There was a problem with your login and/or errors exist in your form.";

        //Verify error message
        logger.info("Verify error message");
        logger.debug("Actual message: " + actualMessage);
        ReportManager.info("5. Verify error message: " + actualMessage);

        Assert.assertEquals(actualMessage.trim(), expectedMessage, "Message does not match");
        ReportManager.pass("Test passed - Error message matched expected.");

        DriverManager.quitDriver();
    }
}
