package base;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentReportManager;
import utils.ScreenshotUtils;

public class UiBase {
    protected WebDriver driver;

    @BeforeClass
    public void setupDriver() {
//        WebDriverManager.edgedriver().setup();
        System.setProperty("webdriver.edge.driver","C:\\Users\\imvsk\\IdeaProjects\\automationDemo\\src\\test\\resources\\msedgedriver.exe");

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=chrome");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new EdgeDriver(options);
        driver.manage().window().maximize(); // Optional, won't do much in headless
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
