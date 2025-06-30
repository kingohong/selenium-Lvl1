package com.tests.login;

import com.railway.dataobject.Account;
import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import com.tests.utilities.ReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase02 extends TestBase {

    private static final Logger logger = LogManager.getLogger(TestCase02.class);

    @Test
    public void UserCannotLoginWithBlankUsernameTextbox() {
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        HomePage homePage = new HomePage();
        Helpers helpers = new Helpers();

        logger.info("=== TestCase02: UserCannotLoginWithBlankUsernameTextbox ===");
        ReportManager.info("=== TestCase02: UserCannotLoginWithBlankUsernameTextbox ===");

        // 1. Navigate to QA Railway Website
        // 2. Click on "Login" tab
        logger.info("1. Navigate to QA Railway Website");
        logger.info("2. Click on 'Login' tab");
        ReportManager.info("1. Navigate to QA Railway Website");
        ReportManager.info("2. Click on 'Login' tab");
        basePage.clickTab("Login");

        // 3. User doesn't type any words into "Username" textbox but enter valid information into "Password" textbox
        // 4. Click on "Login" button
        helpers.scrollToElement(By.xpath("//input[@value='login']"));

        logger.info("3. Leave 'Username' blank, enter valid password");
        logger.info("4. Click on 'Login' button");
        ReportManager.info("3. Leave 'Username' blank, enter valid password");
        ReportManager.info("4. Click on 'Login' button");
        loginPage.login(Account.BLANK_ACCOUNT);

        String actualMessage = loginPage.getBlankErrorMessageText();
        String expectedMessage = "There was a problem with your login and/or errors exist in your form.";

        //Verify error message
        logger.info("Verify error message");
        ReportManager.info("Verify error message");

        Assert.assertEquals(actualMessage.trim(), expectedMessage, "Message does not match");
        ReportManager.pass("Test passed - Error message matched expected");

        DriverManager.quitDriver();
    }
}
