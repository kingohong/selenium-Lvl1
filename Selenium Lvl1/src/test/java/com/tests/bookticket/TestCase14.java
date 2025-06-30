package com.tests.bookticket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.JsonNode;
import com.tests.utilities.JsonDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.BookTicketPage;
import com.railway.pages.LoginPage;
import com.railway.pages.MyTicketPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import com.tests.utilities.ReportManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase14 extends TestBase {
    private static final Logger logger = LogManager.getLogger(TestCase14.class);

    @Test(dataProvider = "JsonData", dataProviderClass = JsonDataProvider.class)
    public void UserCanBookOneTicketAtATime(JsonNode data) {
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        Helpers helpers = new Helpers();
        BookTicketPage bookTicketPage = new BookTicketPage();
        MyTicketPage myTicketPage = new MyTicketPage();

        String departFrom = data.get("departFrom").asText();
        String arriveAt = data.get("arriveAt").asText();
        String seatType = data.get("seatType").asText();
        String ticketAmount = data.get("ticketAmount").asText();
        int offsetDays = Integer.parseInt(data.get("departDate").asText());
        LocalDate date = LocalDate.now().plusDays(offsetDays + 3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        String departDate = date.format(formatter);


        logger.info("=== TestCase14: UserCanBookOneTicketAtATime ===");
        ReportManager.info("=== TestCase14: UserCanBookOneTicketAtATime ===");

        // 1. Navigate to QA Railway Website
        logger.info("1. Navigate to QA Railway Website");
        ReportManager.info("1. Navigate to QA Railway Website");

        // 2. Login with a valid account
        logger.info("2. Login with a valid account");
        ReportManager.info("2. Login with a valid account");
        basePage.clickTab("Login");
        helpers.scrollToElement(By.xpath("//input[@value='login']"));
        loginPage.loginSuccess();

        // 3. Click on "Book ticket" tab
        logger.info("3. Click on 'Book ticket' tab");
        ReportManager.info("3. Click on 'Book ticket' tab");
        basePage.clickTab("Book ticket");

        // 4-8. Fill form and book ticket
        logger.info("4. Select a 'Depart date' from the list");
        logger.info("5. Select 'Sài Gòn' for 'Depart from' and 'Nha Trang' for 'Arrive at'.");
        logger.info("6. Select 'Soft bed with air conditioner' for 'Seat type'");
        logger.info("7. Select '1' for 'Ticket amount'");
        logger.info("8. Click on 'Book ticket' button");
        ReportManager.info("4. Select a 'Depart date' from the list");
        ReportManager.info("5. Select 'Sài Gòn' for 'Depart from' and 'Nha Trang' for 'Arrive at'.");
        ReportManager.info("6. Select 'Soft bed with air conditioner' for 'Seat type'");
        ReportManager.info("7. Select '1' for 'Ticket amount'");
        ReportManager.info("8. Click on 'Book ticket' button");

        bookTicketPage.bookTicket(departDate, departFrom, arriveAt, seatType, ticketAmount);

        // Expected values
        String expectedSuccessMessage = "Ticket booked successfully!";
        String expectedDepartFrom = departFrom;
        String expectedArriveAt = arriveAt;
        String expectedSeatType = seatType;
        String expectedDepartDate = departDate;
        String expectedAmount = ticketAmount;


        // Verify success message
        logger.info("Verify success message");
        ReportManager.info("Verify success message");

        String actualSuccessMessage = bookTicketPage.getBookTicketSuccessMessageText();
        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage, "Different Message");
        ReportManager.pass("Success message displayed correctly: " + actualSuccessMessage);

        // Verify Book Ticket table info
        logger.info("Verify Book Ticket table info");
        ReportManager.info("Verify Book Ticket table info");

        boolean bookInfoCorrect = bookTicketPage.isBookingTableCorrect(expectedDepartFrom, expectedArriveAt, expectedSeatType, expectedDepartDate, expectedAmount);
        Assert.assertTrue(bookInfoCorrect, "Wrong info in Book Ticket table");
        ReportManager.pass("Book Ticket table info is correct");

        // Navigate to My Ticket tab
        logger.info("Navigate to My Ticket tab");
        ReportManager.info("Navigate to My Ticket tab");
        basePage.clickTab("My ticket");

        // Verify My Ticket table info
        logger.info("Verify My Ticket table info");
        ReportManager.info("Verify My Ticket table info");

        boolean myTicketInfoCorrect = myTicketPage.isManageTableCorrect(expectedDepartFrom, expectedArriveAt, expectedSeatType, expectedDepartDate, expectedAmount);
        Assert.assertTrue(myTicketInfoCorrect, "Wrong info in My Ticket table");
        ReportManager.pass("My Ticket table info is correct");

        DriverManager.quitDriver();
    }
}
