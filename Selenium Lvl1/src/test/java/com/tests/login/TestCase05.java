package com.tests.login;

import com.railway.dataobject.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase05 extends TestBase {
    private static final Logger logger = LogManager.getLogger(TestCase05.class);

    @Test
    public void SystemShowsMessageWhenUserEntersWrongPasswordSeveralTimes(){
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        Helpers helpers = new Helpers();
        logger.info("=== TestCase05: SystemShowsMessageWhenUserEntersWrongPasswordSeveralTimes ===");

        //1. Navigate to QA Railway Website
        //2. Click on "Login" tab
        logger.info("1. Navigate to QA Railway Website");
        logger.info("2. Click on 'Login' tab");

        basePage.clickTab("Login");

        //3. Enter valid information into "Username" textbox except "Password" textbox.
        //4. Click on "Login" button
        logger.info("3. Enter valid information into 'Username' textbox except 'Password' textbox.");
        logger.info("4. Click on 'Login' button");

        helpers.scrollToElement(By.xpath("//input[@value='login']"));

        loginPage.login(Account.BLANK_PASSWORD);

        //5. Repeat step 3 three more times.
        logger.info("5. Repeat step 3 three more times.");

        for (int i = 1; i <= 4; i++) {
            loginPage.login(Account.BLANK_PASSWORD);
        }

        String actualMessage = loginPage.getInvalidErrorMessageText();

        String expectedMessage = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        Assert.assertEquals(actualMessage.trim(), expectedMessage, "Message does not match");

        DriverManager.quitDriver();
    }
}
