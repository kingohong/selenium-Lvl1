package com.tests.timetable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.railway.driver.DriverManager;
import com.railway.pages.*;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestCase15 extends TestBase {
    private static final Logger logger = LogManager.getLogger(TestCase15.class);

    @Test
    public void UserCanOpenBookTicketPageByClickingOnBookTicketLinkInTrainTimetablePage(){
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        Helpers helpers = new Helpers();
        BookTicketPage bookTicketPage = new BookTicketPage();
        TimetablePage timetablePage = new TimetablePage();

        logger.info("=== TestCase15: UserCanOpenBookTicketPageByClickingOnBookTicketLinkInTrainTimetablePage ===");

        String departStation = "Huế";
        String arriveStation = "Sài Gòn";

        //1. Navigate to QA Railway Website
        //2. Login with a valid account
        logger.info("1. Navigate to QA Railway Website");
        logger.info("2. Login with a valid account");

        basePage.clickTab("Login");

        helpers.scrollToElement(By.xpath("//input[@value='login']"));

        loginPage.loginSuccess();

        //3. Click on "Timetable" tab
        logger.info("3. Click on 'Timetable' tab");

        basePage.clickTab("Timetable");

        //4. Click on "book ticket" link of the route from "Huế" to "Sài Gòn"
        logger.info("4. Click on 'book ticket' link of the route from 'Huế' to 'Sài Gòn'");

        helpers.scrollToElement(By.xpath("//tr[td[normalize-space(.)='Huế']]"));

        timetablePage.clickBookTicketLink(departStation, arriveStation);

        WebElement departFromSelect = DriverManager.getDriver().findElement(By.name("DepartStation"));
        WebElement arriveAtSelect = DriverManager.getDriver().findElement(By.name("ArriveStation"));

        String selectedDepartFrom = departFromSelect.findElement(By.cssSelector("option[selected='selected']")).getText().trim();
        String selectedArriveAt = arriveAtSelect.findElement(By.cssSelector("option[selected='selected']")).getText().trim();

        Assert.assertEquals(selectedDepartFrom, departStation, "Depart station mismatch");
        Assert.assertEquals(selectedArriveAt, arriveStation, "Arrive station mismatch");

        DriverManager.quitDriver();
    }
}
