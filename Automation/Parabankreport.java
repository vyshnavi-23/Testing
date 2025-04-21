package Project;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Parabankreport {

    WebDriver driver;
    ExtentSparkReporter reporter;
    ExtentReports extent;
    ExtentTest test;

    String baseurl = "https://parabank.parasoft.com/parabank/index.htm";

    @BeforeTest
    public void setupReport() 
    {
        reporter = new ExtentSparkReporter("./Report12/parabank_report.html");
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("ParaBank Functional Testing");
        reporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Host", "Localhost");
        extent.setSystemInfo("OS", "Windows 10");
        extent.setSystemInfo("Tester", "absuser");
        extent.setSystemInfo("Browser", "Chrome");

        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void openSite() 
    {
        driver.get(baseurl);
        driver.manage().window().maximize();
    }

    @Test
    public void verifyPageTitle() 
    {
        test = extent.createTest("Verify Page Title");
        String expected = "Title"; 
        //String expected = "ParaBank | Welcome | Online Banking";  Actual title
        String actual = driver.getTitle();
        Assert.assertEquals(actual, expected); 
    }


    @Test
    public void loginButtonTest() 
    {
        test = extent.createTest("Login Button Test");
        String buttonText = driver.findElement(By.xpath("//input[@value='Log In']")).getAttribute("value");
        Assert.assertEquals(buttonText, "Log In");
    }
    
    

    @Test
    public void logoDisplayTest() 
    {
        test = extent.createTest("Logo Display Test");
        boolean logoVisible = driver.findElement(By.className("logo")).isDisplayed();
        Assert.assertTrue(logoVisible);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException 
    {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case Failed: " + result.getName());
            test.log(Status.FAIL, "Reason: " + result.getThrowable());
            String screenshotPath = getScreenshot(driver, result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
        } 
        else if (result.getStatus() == ITestResult.SKIP) 
        {
            test.log(Status.SKIP, "Test Case Skipped: " + result.getName());
        } 
        else if (result.getStatus() == ITestResult.SUCCESS) 
        {
            test.log(Status.PASS, "Test Case Passed: " + result.getName());
        }
    }

    @AfterTest
    public void closeReport() 
    {
        driver.quit();
        extent.flush();
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException
    {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dest = "./Screenshot/" + screenshotName + ".png";
        FileHandler.copy(src, new File(dest));
        return dest;
    }
}
