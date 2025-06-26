package com.railway.pages;

import com.railway.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BookTicketPage {
    private final By departDate = By.xpath("//select[@name='Date']");
    private final By departFrom = By.xpath("//select[@name='DepartStation']");
    private final By arriveAt = By.xpath("//select[@name='ArriveStation']");
    private final By seatType = By.xpath("//select[@name='SeatType']");
    private final By ticketAmount = By.xpath("//select[@name='TicketAmount']");
    private final By bookTicketButton = By.xpath("//input[@type='submit' and @value='Book ticket']");
    private final By bookTicketSuccessMessage = By.xpath("//h1[text()='Ticket booked successfully!']");
    private final By bookSuccessTable = By.xpath("//table[@class='MyTable WideTable']//tr[contains(@class,'OddRow')]");

    public WebElement getDepartDate() {
        return DriverManager.getDriver().findElement(departDate);
    }
    public WebElement getDepartFrom() {
        return DriverManager.getDriver().findElement(departFrom);
    }
    public WebElement getArriveAt() {
        return DriverManager.getDriver().findElement(arriveAt);
    }
    public WebElement getSeatType() {
        return DriverManager.getDriver().findElement(seatType);
    }
    public WebElement getTicketAmount() {
        return DriverManager.getDriver().findElement(ticketAmount);
    }
    public WebElement getBookTicketButton() {
        return DriverManager.getDriver().findElement(bookTicketButton);
    }

    public String getBookTicketSuccessMessageText() {
        return DriverManager.getDriver().findElement(bookTicketSuccessMessage).getText();
    }
    public WebElement getBookSuccessTable() {
        return DriverManager.getDriver().findElement(bookSuccessTable);
    }

    public String getSelectedDepartDate() {
        WebElement selectedOption = DriverManager.getDriver().findElement(
                By.xpath("//select[@name='Date']")
        );
        return selectedOption.getText().trim();
    }


    public void bookTicket(String departDate, String departFrom, String arriveAt, String seatType, String ticketAmount) {
        Select departDateSelect = new Select(getDepartDate());
        if (departDate == null || departDate.isEmpty()) {
            departDate = departDateSelect.getOptions().get(0).getText();
        }
        departDateSelect.selectByVisibleText(departDate);

        Select departFromSelect = new Select(getDepartFrom());
        departFromSelect.selectByVisibleText(departFrom);

        Select arriveAtSelect = new Select(getArriveAt());
        arriveAtSelect.selectByVisibleText(arriveAt);

        Select seatTypeSelect = new Select(getSeatType());
        seatTypeSelect.selectByVisibleText(seatType);

        Select ticketAmountSelect = new Select(getTicketAmount());
        ticketAmountSelect.selectByVisibleText(ticketAmount);

        getBookTicketButton().click();
    }

    public boolean isBookingTableCorrect(String departFrom, String arriveAt, String seatType, String departDate, String amount) {
        WebElement row = DriverManager.getDriver().findElement(By.xpath("//table[@class='MyTable WideTable']//tr[td]"));
        List<WebElement> cells = row.findElements(By.tagName("td"));

        return cells.get(0).getText().equals(departFrom) &&
                cells.get(1).getText().equals(arriveAt) &&
                cells.get(2).getText().equals(seatType) &&
                cells.get(3).getText().equals(departDate) &&
                cells.get(6).getText().equals(amount);
    }


}
