package com.tests.bookticket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.LoginPage;
import com.tests.base.TestBase;
import com.tests.utilities.ReportManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase04 extends TestBase {
    private static final Logger logger = LogManager.getLogger(TestCase04.class);

    @Test
    public void LoginPageDisplaysWhenUnLoggedUserClicksOnBookTicketTab() {
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();

        logger.info("=== TestCase04: LoginPageDisplaysWhenUnLoggedUserClicksOnBookTicketTab ===");
        ReportManager.info("=== TestCase04: LoginPageDisplaysWhenUnLoggedUserClicksOnBookTicketTab ===");

        // 1. Navigate to QA Railway Website
        logger.info("1. Navigate to QA Railway Website");
        ReportManager.info("1. Navigate to QA Railway Website");

        // 2. Click on "Book ticket" tab
        logger.info("2. Click on 'Book ticket' tab");
        ReportManager.info("2. Click on 'Book ticket' tab");
        basePage.clickTab("Book ticket");

        //Verify login form is displayed
        logger.info("Verify login form is displayed");
        ReportManager.info("Verify login form is displayed");

        boolean isLoginFormShown = loginPage.isLoginFormDisplayed();
        logger.debug("Login form displayed: " + isLoginFormShown);
        ReportManager.info("Login form displayed: " + isLoginFormShown);

        Assert.assertTrue(isLoginFormShown, "Login form is not displayed");
        ReportManager.pass("Test passed - Login form is correctly displayed when unauthenticated user clicks on Book Ticket");

        DriverManager.quitDriver();
    }
}
