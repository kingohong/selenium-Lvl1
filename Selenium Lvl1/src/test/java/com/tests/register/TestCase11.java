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

public class TestCase11 extends TestBase {
    @Test
    public void UserCannotCreateAccountWhilePasswordAndPIDFieldsAreEmpty () {
        BasePage basePage = new BasePage();
        RegisterPage registerPage = new RegisterPage();
        Helpers helpers = new Helpers();

        //1. Navigate to QA Railway Website
        //2. Click on "Register" tab

        basePage.clickTab("Register");

        //3. Enter valid information into all fields except "Confirm password" is not the same with "Password"
        //4. Click on "Register" button

        String timestamp = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
        String dynamicEmail = "testuser" + timestamp + "@gmail.com";

        helpers.scrollToElement(By.xpath("//input[@value='Register']"));

        registerPage.register(dynamicEmail, "", "", "");

        String actualMessage = registerPage.getLengthErrorMessageText();

        String expectedMessage1 = "There're errors in the form. Please correct the errors and try again.";
        String expectedMessage2 = "Invalid password length.";
        String expectedMessage3 = "Invalid ID length.";

        Assert.assertEquals(actualMessage.trim(), expectedMessage1, "Message does not match");
        Assert.assertEquals(actualMessage.trim(), expectedMessage2, "Message does not match");
        Assert.assertEquals(actualMessage.trim(), expectedMessage3, "Message does not match");

        DriverManager.quitDriver();
    }
}
