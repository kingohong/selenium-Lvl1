package com.tests.login;

import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase03 extends TestBase {
    @Test
    public void UserCannotLogIntoRailwayWithInvalidPassword(){
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        Helpers helpers = new Helpers();

        //1. Navigate to QA Railway Website
        //2. Click on "Login" tab
        basePage.clickTab("Login");

        //3. Enter valid Email and invalid Password
        //4. Click on "Login" button
        helpers.scrollToElement(By.xpath("//input[@value='login']"));

        loginPage.login("kingohong@gmail.com", "123456");

        String actualMessage = loginPage.getInvalidErrorMessageText();

        String expectedMessage = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMessage.trim(), expectedMessage, "Message does not match");

        DriverManager.quitDriver();
    }
}
