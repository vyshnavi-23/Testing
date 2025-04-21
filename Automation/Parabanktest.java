package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.ParabankLogin;
import pages.ParabankFundTransfer;

public class Parabanktest {
    WebDriver driver;
    ParabankLogin loginPage;
    ParabankFundTransfer fundTransferPage;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void url() {
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test(priority = 1)
    public void testLogin() {
        loginPage = new ParabankLogin(driver);
        loginPage.login("absuser", "absuser12");  
    }

    @Test(priority = 2, dependsOnMethods = "testLogin")
    public void testFundTransfer() {
        fundTransferPage = new ParabankFundTransfer(driver);
        fundTransferPage.transferFunds("1000");
        fundTransferPage.findTransactionByAmount("1000");
    }
}
