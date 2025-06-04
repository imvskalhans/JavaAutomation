package base;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentReportManager;
import utils.ScreenshotUtils;

public class UiBase {
    protected WebDriver driver;

    @BeforeClass
    public void setupDriver() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void captureResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getName());
            ExtentReportManager.getTest().fail("Test Failed. Screenshot attached: " + screenshotPath);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        ExtentReportManager.endReport();
    }
}
