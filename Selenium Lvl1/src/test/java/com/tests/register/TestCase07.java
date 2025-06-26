package com.tests.register;

import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.HomePage;
import com.railway.pages.RegisterPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCase07 extends TestBase {
    @Test
    public void UserCanCreateNewAcount() {
        BasePage basePage = new BasePage();
        RegisterPage registerPage = new RegisterPage();
        Helpers helpers = new Helpers();

        //1. Navigate to QA Railway Website
        //2. Click on "Register" tab

        basePage.clickTab("Register");

        //3. Enter valid information into all fields
        //4. Click on "Register" button

        String timestamp = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
        String dynamicEmail = "testuser" + timestamp + "@gmail.com";

        helpers.scrollToElement(By.xpath("//input[@value='Register']"));

        registerPage.register(dynamicEmail, "123456789", "123456789", "123456789");

        String actualMessage = registerPage.getThankYouMessageText();

        String expectedMessage = "Thank you for registering your account";

        Assert.assertEquals(actualMessage.trim(), expectedMessage, "Message does not match");

        DriverManager.quitDriver();
    }
}
