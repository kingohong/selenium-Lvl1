package com.tests.bookticket;

import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.LoginPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase04 extends TestBase {
    @Test
    public void LoginPageDisplaysWhenUnLoggedUserClicksOnBookTicketTab(){
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();

        //1. Navigate to QA Railway Website
        //2. Click on "Book ticket" tab
        basePage.clickTab("Book ticket");

        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form is not displayed");

        DriverManager.quitDriver();
    }
}
