package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParabankFundTransfer {
    WebDriver driver;

    // Locators
    By transferFundsLink = By.xpath("//a[normalize-space()='Transfer Funds']");
    By amountField = By.xpath("//input[@id='amount']");
    By transferButton = By.xpath("//input[@value='Transfer']");
    By findTransactionsLink = By.xpath("//a[normalize-space()='Find Transactions']");
    By findByAmountField = By.xpath("//input[@id='amount']");
    By findByAmountButton = By.xpath("//button[@id='findByAmount']");

    // Constructor
    public ParabankFundTransfer(WebDriver driver) {
        this.driver = driver;
    }

    // Method to perform fund transfer
    public void transferFunds(String amount) {
        driver.findElement(transferFundsLink).click();
        driver.findElement(amountField).sendKeys(amount);
        driver.findElement(transferButton).click();
    }

    // Method to search for the transaction
    public void findTransactionByAmount(String amount) {
        driver.findElement(findTransactionsLink).click();
        driver.findElement(findByAmountField).sendKeys(amount);
        driver.findElement(findByAmountButton).click();
    }
}
