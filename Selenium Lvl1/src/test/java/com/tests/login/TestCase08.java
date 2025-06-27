package com.tests.login;

import com.railway.dataobject.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.pages.RegisterPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCase08 extends TestBase {
    private static final Logger logger = LogManager.getLogger(TestCase08.class);

    @Test
    public void UserCannotLoginWithUnactivatedAccount() {
        BasePage basePage = new BasePage();
        RegisterPage registerPage = new RegisterPage();
        LoginPage loginPage = new LoginPage();
        Helpers helpers = new Helpers();

        logger.info("=== TestCase08: UserCannotLoginWithUnactivatedAccount ===");

        //1. Navigate to QA Railway Website
        logger.info("1. Navigate to QA Railway Website");

        //2. Click on "Login" tab
        logger.info("2. Click on 'Login' tab");

        basePage.clickTab("Login");

        //3. Enter username and password of account hasn't been activated.
        //4. Click on "Login" button
        logger.info("3. Enter username and password of account hasn't been activated.");
        logger.info("4. Click on 'Login' button");

        helpers.scrollToElement(By.xpath("//input[@value='login']"));
        loginPage.login(Account.UNACTIVED_ACCOUNT);

        String actualMessage = loginPage.getInvalidErrorMessageText();
        String expectedMessage = "Invalid username or password. Please try again.";

        Assert.assertEquals(actualMessage.trim(), expectedMessage, "Error message does not match");

        DriverManager.quitDriver();
    }
}
