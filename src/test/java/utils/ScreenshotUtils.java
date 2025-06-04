package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtils {
    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = "screenshots/" + testName + ".png";
            Files.createDirectories(Paths.get("screenshots"));
            File dest = new File(path);
            Files.copy(src.toPath(), dest.toPath());
            return dest.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return "Screenshot failed";
        }
    }
}
