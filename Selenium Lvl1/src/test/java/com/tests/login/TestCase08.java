package com.tests.login;

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
    @Test
    public void UserCannotLoginWithUnactivatedAccount() {
        BasePage basePage = new BasePage();
        RegisterPage registerPage = new RegisterPage();
        LoginPage loginPage = new LoginPage();
        Helpers helpers = new Helpers();
        //1. Navigate to QA Railway Website

        basePage.clickTab("Register");

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String dynamicEmail = "testuser" + timestamp + "@gmail.com";

        helpers.scrollToElement(By.xpath("//input[@value='Register']"));
        registerPage.register(dynamicEmail, "123456789", "123456789", "123456789");

        //2. Click on "Login" tab

        basePage.clickTab("Login");

        //3. Enter username and password of account hasn't been activated.
        //4. Click on "Login" button

        helpers.scrollToElement(By.xpath("//input[@value='login']"));
        loginPage.login(dynamicEmail, "123456789");

        String actualMessage = loginPage.getInvalidErrorMessageText();
        String expectedMessage = "Invalid username or password. Please try again.";

        Assert.assertEquals(actualMessage.trim(), expectedMessage, "Error message does not match");

        DriverManager.quitDriver();
    }
}
