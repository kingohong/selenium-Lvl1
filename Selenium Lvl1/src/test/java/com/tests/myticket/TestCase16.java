package com.tests.myticket;

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

public class TestCase16 extends TestBase {
    private static final Logger logger = LogManager.getLogger(TestCase16.class);

    @Test(dataProvider = "JsonData", dataProviderClass = JsonDataProvider.class)
    public void UserCanCancelATicket(JsonNode data) {
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        Helpers helpers = new Helpers();
        BookTicketPage bookTicketPage = new BookTicketPage();
        MyTicketPage myTicketPage = new MyTicketPage();

        logger.info("=== TestCase16: UserCanCancelATicket ===");
        ReportManager.info("=== TestCase16: UserCanCancelATicket ===");

        // 1. Navigate to QA Railway Website
        logger.info("1. Navigate to QA Railway Website");
        ReportManager.info("1. Navigate to QA Railway Website");

        // 2. Login with a valid account
        logger.info("2. Login with a valid account");
        ReportManager.info("2. Login with a valid account");

        basePage.clickTab("Login");
        helpers.scrollToElement(By.xpath("//input[@value='login']"));
        loginPage.loginSuccess();

        // 3. Book a ticket
        logger.info("3. Book a ticket");
        ReportManager.info("3. Book a ticket");

        basePage.clickTab("Book ticket");

        // Check if ArriveAt dropdown is re-rendered after changing DepartFrom
//        logger.info("Check if ArriveAt dropdown is re-rendered after changing DepartFrom");
//        ReportManager.info("Check if ArriveAt dropdown is re-rendered after changing DepartFrom");
//        boolean isArriveReRendered = bookTicketPage.isArriveStationReRenderedAfterDepartChange(departFrom);
//        Assert.assertTrue(isArriveReRendered, "The 'Arrive At' dropdown must be updated after selecting 'Depart From'");
//        ReportManager.pass("'Arrive At' dropdown updated successfully after selecting 'Depart From'");

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
        // 4. Click on "My ticket" tab
        logger.info("4. Click on'My ticket' tab");
        ReportManager.info("4. Click on'My ticket' tab");

        basePage.clickTab("My ticket");

        // 5. Click on "Cancel" button of ticket which user want to cancel.
        logger.info("5. Click on'Cancel' button of ticket which user want to cancel.");
        ReportManager.info("5. Click on'Cancel' button of ticket which user want to cancel.");

        helpers.scrollToElement(By.xpath("//table[@class='MyTable']//tr[2]"));
        myTicketPage.clickCancelButton(departFrom, arriveAt);

        // 6. Click on "OK" button on Confirmation message "Are you sure?"
        logger.info("6. Click on 'OK' button on Confirmation message 'Are you sure'");
        ReportManager.info("6. Click on 'OK' button on Confirmation message 'Are you sure'");

        myTicketPage.confirmCancel();

        boolean ticketStillExists = myTicketPage.isTicketCanceled(departFrom, arriveAt, seatType, departDate);

        Assert.assertTrue(ticketStillExists, "Ticket still exists");
        ReportManager.pass("Ticket was successfully cancelled: " + departFrom + " to " + arriveAt + ", " + seatType + ", " + departDate);

        DriverManager.quitDriver();
    }
}
