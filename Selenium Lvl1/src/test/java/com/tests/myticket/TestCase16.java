package com.tests.myticket;

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

public class TestCase16 extends TestBase {
    @Test
    public void UserCanBookOneTicketAtATime(){
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        Helpers helpers = new Helpers();
        BookTicketPage bookTicketPage = new BookTicketPage();
        MyTicketPage myTicketPage = new MyTicketPage();

        //1. Navigate to QA Railway Website
        //2. Login with a valid account

        basePage.clickTab("Login");

        helpers.scrollToElement(By.xpath("//input[@value='login']"));

        loginPage.login("kingohong@gmail.com", "123456789");

        //3. Book a ticket

        basePage.clickTab("Book ticket");

        String departFrom = "Sài Gòn";
        String arriveAt = "Nha Trang";
        String seatType = "Soft seat with air conditioner";
        String amount = "1";
        String expectedDepartDate = bookTicketPage.getSelectedDepartDate();

        bookTicketPage.bookTicket(null, departFrom, arriveAt, seatType, amount);

        //4. Click on "My ticket" tab

        basePage.clickTab("My ticket");

        //5. Click on "Cancel" button of ticket which user want to cancel.

        helpers.scrollToElement(By.xpath("//table[@class='MyTable']//tr[2]"));

        myTicketPage.clickCancelButton(departFrom, arriveAt);

        //6. Click on "OK" button on Confirmation message "Are you sure?"

        myTicketPage.confirmCancel();

        boolean ticketStillExists = myTicketPage.isTicketCanceled(departFrom, arriveAt, seatType, expectedDepartDate);
        Assert.assertFalse(ticketStillExists, "Ticket still exists");

        DriverManager.quitDriver();
    }
}
