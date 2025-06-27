package com.tests.bookticket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.BookTicketPage;
import com.railway.pages.LoginPage;
import com.railway.pages.MyTicketPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestCase14 extends TestBase {
    private static final Logger logger = LogManager.getLogger(TestCase14.class);

    @Test
    public void UserCanBookOneTicketAtATime(){
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        Helpers helpers = new Helpers();
        BookTicketPage bookTicketPage = new BookTicketPage();
        MyTicketPage myTicketPage = new MyTicketPage();

        logger.info("=== TestCase14: UserCanBookOneTicketAtATime ===");

        //1. Navigate to QA Railway Website
        //2. Login with a valid account
        logger.info("1. Navigate to QA Railway Website");
        logger.info("2. Login with a valid account");

        basePage.clickTab("Login");

        helpers.scrollToElement(By.xpath("//input[@value='login']"));

        loginPage.loginSuccess();

        //3. Click on "Book ticket" tab
        logger.info("3. Click on 'Book ticket' tab");

        basePage.clickTab("Book ticket");

        //4. Select a "Depart date" from the list
        //5. Select "Sài Gòn" for "Depart from" and "Nha Trang" for "Arrive at".
        //6. Select "Soft bed with air conditioner" for "Seat type"
        //7. Select "1" for "Ticket amount"
        //8. Click on "Book ticket" button
        logger.info("4. Select a 'Depart date' from the list");
        logger.info("5. Select 'Sài Gòn' for 'Depart from' and 'Nha Trang' for 'Arrive at'.");
        logger.info("6. Select 'Soft bed with air conditioner' for 'Seat type'");
        logger.info("7. Select '1' for 'Ticket amount'");
        logger.info("8. Click on 'Book ticket' button");

        bookTicketPage.bookTicket(null, "Sài Gòn", "Nha Trang", "Soft bed with air conditioner", "1");

        String actualSuccessMessage = bookTicketPage.getBookTicketSuccessMessageText();

        String expectedSuccessMessage = "Ticket booked successfully!";
        String expectedDepartFrom = "Sài Gòn";
        String expectedArriveAt = "Nha Trang";
        String expectedSeatType = "Soft bed with air conditioner";
        String expectedDepartDate = "6/29/2025";
        String expectedAmount = "1";

        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage, "Different Message");

        Assert.assertFalse(bookTicketPage.isBookingTableCorrect(expectedDepartFrom, expectedArriveAt, expectedSeatType, expectedDepartDate, expectedAmount), "Wrong info");

        basePage.clickTab("My ticket");

        Assert.assertFalse(myTicketPage.isManageTableCorrect(expectedDepartFrom, expectedArriveAt, expectedSeatType, expectedDepartDate, expectedAmount), "Wrong info");

        DriverManager.quitDriver();
    }
}
