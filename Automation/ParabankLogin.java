package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParabankLogin {
    WebDriver driver;

    // Locators
    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginButton = By.cssSelector("input.button");

    // Constructor
    public ParabankLogin(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
