package com.tests.changepassword;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.ChangePasswordPage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase09 extends TestBase {
    private static final Logger logger = LogManager.getLogger(TestCase09.class);

    @Test
    public void UserCanChangePassword(){
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        Helpers helpers = new Helpers();
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();

        logger.info("=== TestCase09: UserCanChangePassword ===");

        //1. Navigate to QA Railway Website
        //2. Login with valid account
        logger.info("1. Navigate to QA Railway Website");
        logger.info("2. Login with valid account");

        basePage.clickTab("Login");
        helpers.scrollToElement(By.xpath("//input[@value='login']"));
        loginPage.loginSuccess();
        //3. Click on "Change Password" tab
        logger.info("3. Click on 'Change Password' tab");

        basePage.clickTab("Change password");
        //4. Enter valid value into all fields.
        //5. Click on "Change Password" button
        logger.info("4. Enter valid value into all fields.");
        logger.info("5. Click on 'Change Password' button");

        changePasswordPage.changePassword("123456789", "123456789", "123456789");

        String actualMessage = changePasswordPage.getPasswordUpdatedMessageText();

        String expectedMessage = "Your password has been updated";

        Assert.assertEquals(actualMessage.trim(), expectedMessage, "Message does not match");

        DriverManager.quitDriver();
    }
}
