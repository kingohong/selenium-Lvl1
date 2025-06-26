package com.railway.pages;

import com.railway.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TimetablePage {
    private final By bookTicketLink = By.xpath("//tr[td[2]='%s' and td[3]='%s']//a[contains(@href,'BookTicketPage')]");

    public WebElement getBookTicketButton() {
        return DriverManager.getDriver().findElement(bookTicketLink);
    }

    public void clickBookTicketLink(String departStation, String arriveStation) {
        String dynamicXpath = String.format("//tr[td[2][normalize-space()='%s'] and td[3][normalize-space()='%s']]//a[contains(text(),'book ticket')]", departStation, arriveStation);
        WebElement bookTicketLink = DriverManager.getDriver().findElement(By.xpath(dynamicXpath));
        bookTicketLink.click();
    }
}
