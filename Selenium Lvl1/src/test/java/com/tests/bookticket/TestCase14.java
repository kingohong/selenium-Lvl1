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

        // Check if ArriveAt dropdown is re-rendered after changing DepartFrom
//        logger.info("Check if ArriveAt dropdown is re-rendered after changing DepartFrom");
//        ReportManager.info("Check if ArriveAt dropdown is re-rendered after changing DepartFrom");
//        boolean isArriveReRendered = bookTicketPage.isArriveStationReRenderedAfterDepartChange(departFrom);
//        Assert.assertFalse(isArriveReRendered, "The 'Arrive At' dropdown must be updated after selecting 'Depart From'");
//        ReportManager.pass("'Arrive At' dropdown updated successfully after selecting 'Depart From'");

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

        JsonNode tickets = data.get("ticketsToBook");

        String departFrom = "";
        String arriveAt = "";
        String seatType = "";
        String ticketAmount = "";
        String departDate = "";

        for (JsonNode ticket : tickets) {
            basePage.clickTab("Book ticket");

            departFrom = ticket.get("departFrom").asText();
            arriveAt = ticket.get("arriveAt").asText();
            seatType = ticket.get("seatType").asText();
            ticketAmount = ticket.get("ticketAmount").asText();
            int offsetDays = Integer.parseInt(ticket.get("departDate").asText());

            LocalDate date = LocalDate.now().plusDays(offsetDays + 3);
            departDate = date.format(DateTimeFormatter.ofPattern("M/d/yyyy"));

            helpers.scrollToElement(By.xpath("//select[@name='Date']"));

            bookTicketPage.bookTicket(departDate, departFrom, arriveAt, seatType, ticketAmount);
            ReportManager.pass("Booked ticket: " + departFrom + " → " + arriveAt + ", " + seatType + ", " + ticketAmount + " ticket(s) on " + departDate);
        }

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
