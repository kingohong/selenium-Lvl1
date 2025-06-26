package com.tests.login;

import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.LoginPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase06 extends TestBase {

    @Test
    public void AdditionalPagesDisplayOnceUserLoggedIn() {
        LoginPage loginPage = new LoginPage();
        Helpers helpers = new Helpers();
        BasePage basePage = new BasePage();

        //1. Navigate to QA Railway Website
        //2. Click on "Login" tab
        basePage.clickTab("Login");

        //3. Login with valid account
        helpers.scrollToElement(By.xpath("//input[@value='login']"));
        loginPage.login("kingohong@gmail.com", "123456789");

        Assert.assertTrue(basePage.isTabDisplayed("My ticket"), "My ticket tab is not displayed");
        Assert.assertTrue(basePage.isTabDisplayed("Change password"), "Change password tab is not displayed");
        Assert.assertTrue(basePage.isTabDisplayed("Logout"), "Logout tab is not displayed");

        DriverManager.quitDriver();
    }
}
