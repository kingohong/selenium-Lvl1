package com.railway.pages;

import org.openqa.selenium.remote.RemoteWebElement;
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

//    public void bookTicket(String departDate, String departFrom, String arriveAt, String seatType, String ticketAmount) {
//        Select departDateSelect = new Select(getDepartDate());
//        if (departDate == null || departDate.isEmpty()) {
//            departDate = departDateSelect.getOptions().get(0).getText();
//        }
//        departDateSelect.selectByVisibleText(departDate);
//
//        selectOptionByText(getDepartFrom(), departFrom);
//        selectOptionByText(getArriveAt(), arriveAt);
//        selectOptionByText(getSeatType(), seatType);
//        selectOptionByText(getTicketAmount(), ticketAmount);
//
//        getBookTicketButton().click();
//    }
//
//    private void selectOptionByText(WebElement selectElement, String targetText) {
//        Select select = new Select(selectElement);
//        boolean found = false;
//
//        System.out.println("Looking for: [" + targetText + "] (length: " + targetText.trim().length() + ")");
//        for (WebElement option : select.getOptions()) {
//            String actualText = option.getText().trim();
//            System.out.println("OPTION FOUND: [" + actualText + "] (length: " + actualText.length() + ")");
//
//            if (actualText.equalsIgnoreCase(targetText.trim())) {
//                option.click();
//                found = true;
//                break;
//            }
//        }
//
//        if (!found) {
//            throw new RuntimeException("CANNOT FOUND OPTION: " + targetText);
//        }
//    }



    public boolean isBookingTableCorrect(String departFrom, String arriveAt, String seatType, String departDate, String amount) {
        WebElement row = DriverManager.getDriver().findElement(By.xpath("//table[@class='MyTable WideTable']//tr[td]"));
        List<WebElement> cells = row.findElements(By.tagName("td"));

        return cells.get(0).getText().equals(departFrom) &&
                cells.get(1).getText().equals(arriveAt) &&
                cells.get(2).getText().equals(seatType) &&
                cells.get(3).getText().equals(departDate) &&
                cells.get(6).getText().equals(amount);
    }
    public boolean isArriveStationReRenderedAfterDepartChange(String newDepartFrom) {
        RemoteWebElement before = (RemoteWebElement) getArriveAt();
        String idBefore = before.getId();

        Select departSelect = new Select(getDepartFrom());
        departSelect.selectByVisibleText(newDepartFrom);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        RemoteWebElement after = (RemoteWebElement) getArriveAt();
        String idAfter = after.getId();

        System.out.println("ArriveAt ID Before: " + idBefore);
        System.out.println("ArriveAt ID After : " + idAfter);

        return !idBefore.equals(idAfter);
    }

}
