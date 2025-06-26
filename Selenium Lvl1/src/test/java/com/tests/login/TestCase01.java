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

public class TestCase01 extends TestBase {
    @Test
    public void UserCanLogIntoRailwayWithValidUsernameAndPassword(){
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        HomePage homePage = new HomePage();
        Helpers helpers = new Helpers();

        //1. Navigate to QA Railway Website
        //2. Click on "Login" tab
        basePage.clickTab("Login");

        //3. Enter valid Email and Password
        //4. Click on "Login" button
        helpers.scrollToElement(By.xpath("//input[@value='login']"));

        loginPage.login("kingohong@gmail.com", "123456789");

        String actualMessage = homePage.getWelcomeMessageByEmail("kingohong@gmail.com").getText();

        String expectedMessage = "Welcome kingohong@gmail.com";

        Assert.assertEquals(actualMessage.trim(), expectedMessage, "Message does not match");

        DriverManager.quitDriver();
    }
}
